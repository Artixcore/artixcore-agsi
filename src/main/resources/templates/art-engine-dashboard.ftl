<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ART-Engine 1.0 Dashboard</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #0f0f23; color: #cccccc; }
        .container { max-width: 1200px; margin: 0 auto; padding: 20px; }
        .header { text-align: center; margin-bottom: 40px; }
        .header h1 { color: #00ff88; font-size: 3em; margin-bottom: 10px; }
        .header p { font-size: 1.2em; opacity: 0.8; }
        .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; }
        .card { background: #1a1a2e; border-radius: 10px; padding: 20px; border: 1px solid #333; }
        .card h3 { color: #00ff88; margin-bottom: 15px; }
        .input-group { margin-bottom: 15px; }
        .input-group label { display: block; margin-bottom: 5px; color: #aaa; }
        .input-group input, .input-group textarea { width: 100%; padding: 10px; background: #0f0f23; border: 1px solid #333; border-radius: 5px; color: #ccc; }
        .btn { background: #00ff88; color: #000; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; }
        .btn:hover { background: #00cc6a; }
        .response { margin-top: 15px; padding: 10px; background: #0a0a1a; border-radius: 5px; border-left: 3px solid #00ff88; }
        .metrics { display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 10px; }
        .metric { text-align: center; padding: 15px; background: #0a0a1a; border-radius: 5px; }
        .metric-value { font-size: 2em; color: #00ff88; font-weight: bold; }
        .metric-label { font-size: 0.9em; opacity: 0.7; }
        .status-indicator { display: inline-block; width: 10px; height: 10px; border-radius: 50%; background: #00ff88; margin-right: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🎨 ART-Engine 1.0</h1>
            <p><span class="status-indicator"></span>AI Backend as a Service - Like Supabase, but for AI</p>
        </div>

        <div class="grid">
            <!-- Status Card -->
            <div class="card">
                <h3>Engine Status</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="status">Active</div>
                        <div class="metric-label">Status</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="uptime">0s</div>
                        <div class="metric-label">Uptime</div>
                    </div>
                </div>
            </div>

            <!-- Chat Completion -->
            <div class="card">
                <h3>💬 Chat Completion</h3>
                <div class="input-group">
                    <label>Message</label>
                    <textarea id="chatMessage" rows="3" placeholder="Enter your message..."></textarea>
                </div>
                <button class="btn" onclick="sendChatMessage()">Send Message</button>
                <div id="chatResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Image Generation -->
            <div class="card">
                <h3>🎨 Image Generation</h3>
                <div class="input-group">
                    <label>Prompt</label>
                    <input type="text" id="imagePrompt" placeholder="Describe the image you want...">
                </div>
                <button class="btn" onclick="generateImage()">Generate Image</button>
                <div id="imageResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Text Analysis -->
            <div class="card">
                <h3>📊 Text Analysis</h3>
                <div class="input-group">
                    <label>Text to Analyze</label>
                    <textarea id="analysisText" rows="3" placeholder="Enter text to analyze..."></textarea>
                </div>
                <button class="btn" onclick="analyzeText()">Analyze Text</button>
                <div id="analysisResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Metrics -->
            <div class="card">
                <h3>📈 Engine Metrics</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="totalRequests">0</div>
                        <div class="metric-label">Total Requests</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="successRate">100%</div>
                        <div class="metric-label">Success Rate</div>
                    </div>
                </div>
                <button class="btn" onclick="refreshMetrics()" style="margin-top: 15px;">Refresh Metrics</button>
            </div>

            <!-- WebSocket Connection -->
            <div class="card">
                <h3>🔗 Real-time Connection</h3>
                <div class="input-group">
                    <label>WebSocket Status</label>
                    <div id="wsStatus">Disconnected</div>
                </div>
                <button class="btn" onclick="connectWebSocket()">Connect WebSocket</button>
                <div id="wsMessages" class="response" style="display:none;"></div>
            </div>
        </div>
    </div>

    <script>
        let ws = null;

        // API Functions
        async function sendChatMessage() {
            const message = document.getElementById('chatMessage').value;
            if (!message) return;

            try {
                const response = await fetch('/api/v1/art-engine/chat/completion', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ message })
                });
                const data = await response.json();
                document.getElementById('chatResponse').style.display = 'block';
                document.getElementById('chatResponse').innerHTML = `<strong>Response:</strong> ${data.response || data.error}`;
            } catch (error) {
                document.getElementById('chatResponse').style.display = 'block';
                document.getElementById('chatResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function generateImage() {
            const prompt = document.getElementById('imagePrompt').value;
            if (!prompt) return;

            try {
                const response = await fetch('/api/v1/art-engine/image/generate', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ prompt })
                });
                const data = await response.json();
                document.getElementById('imageResponse').style.display = 'block';
                document.getElementById('imageResponse').innerHTML = `<strong>Status:</strong> ${data.message || data.error}`;
            } catch (error) {
                document.getElementById('imageResponse').style.display = 'block';
                document.getElementById('imageResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function analyzeText() {
            const text = document.getElementById('analysisText').value;
            if (!text) return;

            try {
                const response = await fetch('/api/v1/art-engine/text/analyze', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ text })
                });
                const data = await response.json();
                document.getElementById('analysisResponse').style.display = 'block';
                document.getElementById('analysisResponse').innerHTML = `<strong>Analysis:</strong> ${data.analysis || data.error}`;
            } catch (error) {
                document.getElementById('analysisResponse').style.display = 'block';
                document.getElementById('analysisResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function refreshMetrics() {
            try {
                const response = await fetch('/api/v1/art-engine/status');
                const data = await response.json();
                // Update metrics display
                console.log('Metrics:', data);
            } catch (error) {
                console.error('Error fetching metrics:', error);
            }
        }

        function connectWebSocket() {
            const protocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
            const wsUrl = `${protocol}//${window.location.host}/ws/art-engine`;
            
            ws = new WebSocket(wsUrl);
            
            ws.onopen = function() {
                document.getElementById('wsStatus').textContent = 'Connected';
                document.getElementById('wsMessages').style.display = 'block';
                document.getElementById('wsMessages').innerHTML = '<strong>WebSocket connected!</strong>';
            };
            
            ws.onmessage = function(event) {
                const data = JSON.parse(event.data);
                document.getElementById('wsMessages').innerHTML += `<br><strong>${data.type}:</strong> ${JSON.stringify(data)}`;
            };
            
            ws.onclose = function() {
                document.getElementById('wsStatus').textContent = 'Disconnected';
            };
        }

        // Initialize
        refreshMetrics();
    </script>
</body>
</html>