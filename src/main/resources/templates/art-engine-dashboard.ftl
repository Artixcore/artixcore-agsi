<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ART-Engine 1.0 - Core AI Processor Dashboard</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #0f0f23 0%, #1a1a2e 50%, #16213e 100%);
            color: #e8e8e8; 
            min-height: 100vh;
        }
        .container { max-width: 1400px; margin: 0 auto; padding: 20px; }
        .header { 
            text-align: center; 
            margin-bottom: 40px; 
            background: rgba(255, 255, 255, 0.05);
            border-radius: 20px;
            padding: 30px;
            border: 1px solid rgba(255, 255, 255, 0.1);
        }
        .header h1 { 
            color: #00ff88; 
            font-size: 3.5em; 
            margin-bottom: 10px; 
            text-shadow: 0 0 20px rgba(0, 255, 136, 0.5);
        }
        .header p { 
            font-size: 1.3em; 
            opacity: 0.9; 
            margin-bottom: 15px;
        }
        .subtitle { 
            font-style: italic; 
            color: #ffd700; 
            font-size: 1.1em;
        }
        .grid { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(350px, 1fr)); 
            gap: 25px; 
        }
        .card { 
            background: rgba(255, 255, 255, 0.08); 
            border-radius: 15px; 
            padding: 25px; 
            border: 1px solid rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 30px rgba(0, 255, 136, 0.2);
        }
        .card h3 { 
            color: #00ff88; 
            margin-bottom: 20px; 
            font-size: 1.4em;
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .input-group { margin-bottom: 20px; }
        .input-group label { 
            display: block; 
            margin-bottom: 8px; 
            color: #b0b0b0; 
            font-weight: 500;
        }
        .input-group input, .input-group textarea, .input-group select { 
            width: 100%; 
            padding: 12px; 
            background: rgba(0, 0, 0, 0.3); 
            border: 1px solid rgba(255, 255, 255, 0.2); 
            border-radius: 8px; 
            color: #e8e8e8; 
            font-size: 14px;
        }
        .input-group input:focus, .input-group textarea:focus, .input-group select:focus {
            outline: none;
            border-color: #00ff88;
            box-shadow: 0 0 10px rgba(0, 255, 136, 0.3);
        }
        .btn { 
            background: linear-gradient(135deg, #00ff88, #00cc6a); 
            color: #000; 
            border: none; 
            padding: 12px 24px; 
            border-radius: 8px; 
            cursor: pointer; 
            font-weight: bold; 
            font-size: 14px;
            transition: all 0.3s ease;
        }
        .btn:hover { 
            background: linear-gradient(135deg, #00cc6a, #00a085);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 255, 136, 0.4);
        }
        .response { 
            margin-top: 20px; 
            padding: 15px; 
            background: rgba(0, 0, 0, 0.4); 
            border-radius: 10px; 
            border-left: 4px solid #00ff88; 
            max-height: 300px;
            overflow-y: auto;
        }
        .metrics { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(120px, 1fr)); 
            gap: 15px; 
        }
        .metric { 
            text-align: center; 
            padding: 20px; 
            background: rgba(0, 0, 0, 0.3); 
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.1);
        }
        .metric-value { 
            font-size: 2.2em; 
            color: #00ff88; 
            font-weight: bold; 
            margin-bottom: 5px;
        }
        .metric-label { 
            font-size: 0.9em; 
            opacity: 0.7; 
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .status-indicator { 
            display: inline-block; 
            width: 12px; 
            height: 12px; 
            border-radius: 50%; 
            background: #00ff88; 
            margin-right: 10px;
            box-shadow: 0 0 10px rgba(0, 255, 136, 0.5);
        }
        .agent-list {
            max-height: 300px;
            overflow-y: auto;
        }
        .agent-item {
            background: rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 10px;
            border-left: 3px solid #00ff88;
        }
        .agent-name {
            font-weight: bold;
            color: #00ff88;
            margin-bottom: 5px;
        }
        .agent-type {
            color: #ffd700;
            font-size: 0.9em;
            margin-bottom: 5px;
        }
        .agent-status {
            display: inline-block;
            width: 8px;
            height: 8px;
            border-radius: 50%;
            margin-right: 5px;
        }
        .status-healthy { background: #00ff88; }
        .status-unhealthy { background: #ff4444; }
        .tabs {
            display: flex;
            margin-bottom: 20px;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }
        .tab {
            padding: 10px 20px;
            cursor: pointer;
            border-bottom: 2px solid transparent;
            transition: all 0.3s ease;
        }
        .tab.active {
            border-bottom-color: #00ff88;
            color: #00ff88;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>⚡ ART-Engine 1.0</h1>
            <p>Core AI Processor - The CPU for AI Agents</p>
            <p class="subtitle">"Like a CPU for AI - Connect your agents, we'll process them"</p>
            <p><span class="status-indicator"></span>Powering the next generation of AI applications</p>
        </div>

        <div class="grid">
            <!-- System Status -->
            <div class="card">
                <h3>📊 System Status</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="status">Active</div>
                        <div class="metric-label">Status</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="totalAgents">0</div>
                        <div class="metric-label">Agents</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="activeAgents">0</div>
                        <div class="metric-label">Active</div>
                    </div>
                </div>
            </div>

            <!-- Agent Registry -->
            <div class="card">
                <h3>🤖 Agent Registry</h3>
                <div class="tabs">
                    <div class="tab active" onclick="switchTab('register')">Register</div>
                    <div class="tab" onclick="switchTab('discover')">Discover</div>
                    <div class="tab" onclick="switchTab('execute')">Execute</div>
                </div>
                
                <div id="register" class="tab-content active">
                    <div class="input-group">
                        <label>Agent ID</label>
                        <input type="text" id="agentId" placeholder="unique-agent-id">
                    </div>
                    <div class="input-group">
                        <label>Agent Name</label>
                        <input type="text" id="agentName" placeholder="My AI Agent">
                    </div>
                    <div class="input-group">
                        <label>Agent Type</label>
                        <select id="agentType">
                            <option value="chatbot">Chatbot</option>
                            <option value="math_engine">Math Engine</option>
                            <option value="tutor">AI Tutor</option>
                            <option value="custom">Custom</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <label>Endpoint</label>
                        <input type="text" id="agentEndpoint" placeholder="https://my-agent.com/api">
                    </div>
                    <div class="input-group">
                        <label>Capabilities (comma-separated)</label>
                        <input type="text" id="agentCapabilities" placeholder="text_processing, math, reasoning">
                    </div>
                    <button class="btn" onclick="registerAgent()">Register Agent</button>
                </div>
                
                <div id="discover" class="tab-content">
                    <div class="input-group">
                        <label>Search Query</label>
                        <input type="text" id="searchQuery" placeholder="Find agents that can...">
                    </div>
                    <div class="input-group">
                        <label>Agent Type</label>
                        <select id="searchType">
                            <option value="">All Types</option>
                            <option value="chatbot">Chatbot</option>
                            <option value="math_engine">Math Engine</option>
                            <option value="tutor">AI Tutor</option>
                            <option value="custom">Custom</option>
                        </select>
                    </div>
                    <button class="btn" onclick="discoverAgents()">Search Agents</button>
                    <div id="discoverResults" class="agent-list" style="display:none;"></div>
                </div>
                
                <div id="execute" class="tab-content">
                    <div class="input-group">
                        <label>Agent ID</label>
                        <input type="text" id="executeAgentId" placeholder="agent-id-to-execute">
                    </div>
                    <div class="input-group">
                        <label>Operation</label>
                        <input type="text" id="operation" placeholder="process, analyze, generate">
                    </div>
                    <div class="input-group">
                        <label>Input (JSON)</label>
                        <textarea id="executeInput" rows="3" placeholder='{"message": "Hello world"}'></textarea>
                    </div>
                    <button class="btn" onclick="executeAgent()">Execute Agent</button>
                </div>
                
                <div id="agentResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Core AI Processing -->
            <div class="card">
                <h3>🧠 Core AI Processing</h3>
                <div class="tabs">
                    <div class="tab active" onclick="switchTab('chat')">Chat</div>
                    <div class="tab" onclick="switchTab('image')">Image</div>
                    <div class="tab" onclick="switchTab('analysis')">Analysis</div>
                </div>
                
                <div id="chat" class="tab-content active">
                    <div class="input-group">
                        <label>Message</label>
                        <textarea id="chatMessage" rows="3" placeholder="Enter your message..."></textarea>
                    </div>
                    <button class="btn" onclick="sendChatMessage()">Send Message</button>
                </div>
                
                <div id="image" class="tab-content">
                    <div class="input-group">
                        <label>Image Prompt</label>
                        <input type="text" id="imagePrompt" placeholder="Describe the image you want...">
                    </div>
                    <button class="btn" onclick="generateImage()">Generate Image</button>
                </div>
                
                <div id="analysis" class="tab-content">
                    <div class="input-group">
                        <label>Text to Analyze</label>
                        <textarea id="analysisText" rows="3" placeholder="Enter text to analyze..."></textarea>
                    </div>
                    <button class="btn" onclick="analyzeText()">Analyze Text</button>
                </div>
                
                <div id="coreResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Agent Management -->
            <div class="card">
                <h3>⚙️ Agent Management</h3>
                <button class="btn" onclick="loadAllAgents()" style="margin-bottom: 15px;">Load All Agents</button>
                <div id="agentsList" class="agent-list"></div>
            </div>

            <!-- Load Balancing -->
            <div class="card">
                <h3>⚖️ Load Balancing</h3>
                <div class="input-group">
                    <label>Agent Type</label>
                    <select id="lbAgentType">
                        <option value="chatbot">Chatbot</option>
                        <option value="math_engine">Math Engine</option>
                        <option value="tutor">AI Tutor</option>
                    </select>
                </div>
                <div class="input-group">
                    <label>Operation</label>
                    <input type="text" id="lbOperation" placeholder="process">
                </div>
                <div class="input-group">
                    <label>Input (JSON)</label>
                    <textarea id="lbInput" rows="2" placeholder='{"message": "Hello"}'></textarea>
                </div>
                <button class="btn" onclick="executeLoadBalanced()">Execute Load Balanced</button>
                <div id="lbResponse" class="response" style="display:none;"></div>
            </div>

            <!-- System Metrics -->
            <div class="card">
                <h3>📈 System Metrics</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="totalRequests">0</div>
                        <div class="metric-label">Total Requests</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="successRate">100%</div>
                        <div class="metric-label">Success Rate</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="avgResponseTime">0ms</div>
                        <div class="metric-label">Avg Response</div>
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

        // Tab switching
        function switchTab(tabName) {
            // Hide all tab contents
            document.querySelectorAll('.tab-content').forEach(content => {
                content.classList.remove('active');
            });
            document.querySelectorAll('.tab').forEach(tab => {
                tab.classList.remove('active');
            });
            
            // Show selected tab
            document.getElementById(tabName).classList.add('active');
            event.target.classList.add('active');
        }

        // Agent Management
        async function registerAgent() {
            const agentData = {
                agentId: document.getElementById('agentId').value,
                agentName: document.getElementById('agentName').value,
                agentType: document.getElementById('agentType').value,
                endpoint: document.getElementById('agentEndpoint').value,
                capabilities: document.getElementById('agentCapabilities').value.split(',').map(c => c.trim()),
                description: `AI Agent: ${document.getElementById('agentName').value}`,
                version: "1.0",
                isActive: true,
                maxConcurrentRequests: 10,
                authenticationMethod: "api_key"
            };

            try {
                const response = await fetch('/api/v1/art-engine/agents/register', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(agentData)
                });
                const data = await response.json();
                showResponse('agentResponse', data);
            } catch (error) {
                showResponse('agentResponse', { success: false, error: error.message });
            }
        }

        async function discoverAgents() {
            const searchData = {
                query: document.getElementById('searchQuery').value,
                agentType: document.getElementById('searchType').value,
                onlyActive: true,
                limit: 10
            };

            try {
                const response = await fetch('/api/v1/art-engine/agents/discover', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(searchData)
                });
                const data = await response.json();
                displayAgents('discoverResults', data.agents);
                showResponse('agentResponse', data);
            } catch (error) {
                showResponse('agentResponse', { success: false, error: error.message });
            }
        }

        async function executeAgent() {
            const executeData = {
                agentId: document.getElementById('executeAgentId').value,
                operation: document.getElementById('operation').value,
                input: JSON.parse(document.getElementById('executeInput').value || '{}'),
                sessionId: 'session-' + Date.now(),
                userId: 'user-' + Date.now(),
                streaming: false,
                timeout: 30000
            };

            try {
                const response = await fetch('/api/v1/art-engine/agents/execute', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(executeData)
                });
                const data = await response.json();
                showResponse('agentResponse', data);
            } catch (error) {
                showResponse('agentResponse', { success: false, error: error.message });
            }
        }

        async function executeLoadBalanced() {
            const lbData = {
                agentType: document.getElementById('lbAgentType').value,
                operation: document.getElementById('lbOperation').value,
                input: JSON.parse(document.getElementById('lbInput').value || '{}'),
                sessionId: 'session-' + Date.now(),
                userId: 'user-' + Date.now(),
                streaming: false,
                timeout: 30000
            };

            try {
                const response = await fetch('/api/v1/art-engine/agents/execute/load-balanced', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(lbData)
                });
                const data = await response.json();
                showResponse('lbResponse', data);
            } catch (error) {
                showResponse('lbResponse', { success: false, error: error.message });
            }
        }

        async function loadAllAgents() {
            try {
                const response = await fetch('/api/v1/art-engine/agents');
                const data = await response.json();
                displayAgents('agentsList', data.agents);
            } catch (error) {
                console.error('Error loading agents:', error);
            }
        }

        function displayAgents(containerId, agents) {
            const container = document.getElementById(containerId);
            container.style.display = 'block';
            
            if (!agents || agents.length === 0) {
                container.innerHTML = '<p>No agents found</p>';
                return;
            }

            container.innerHTML = agents.map(agent => `
                <div class="agent-item">
                    <div class="agent-name">${agent.agentName}</div>
                    <div class="agent-type">${agent.agentType}</div>
                    <div>
                        <span class="agent-status ${agent.isHealthy ? 'status-healthy' : 'status-unhealthy'}"></span>
                        ${agent.isHealthy ? 'Healthy' : 'Unhealthy'} | 
                        Success Rate: ${agent.successRate?.toFixed(1) || 0}% | 
                        Requests: ${agent.totalRequests || 0}
                    </div>
                </div>
            `).join('');
        }

        // Core AI Processing
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
                showResponse('coreResponse', data);
            } catch (error) {
                showResponse('coreResponse', { success: false, error: error.message });
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
                showResponse('coreResponse', data);
            } catch (error) {
                showResponse('coreResponse', { success: false, error: error.message });
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
                showResponse('coreResponse', data);
            } catch (error) {
                showResponse('coreResponse', { success: false, error: error.message });
            }
        }

        async function refreshMetrics() {
            try {
                const response = await fetch('/api/v1/art-engine/status');
                const data = await response.json();
                
                // Update metrics
                document.getElementById('totalAgents').textContent = data.agent_registry_stats?.total_agents || 0;
                document.getElementById('activeAgents').textContent = data.agent_registry_stats?.active_agents || 0;
                document.getElementById('totalRequests').textContent = data.agent_registry_stats?.total_requests || 0;
                document.getElementById('successRate').textContent = (data.agent_registry_stats?.average_success_rate || 0).toFixed(1) + '%';
                
                console.log('Updated metrics:', data);
            } catch (error) {
                console.error('Error fetching metrics:', error);
            }
        }

        function showResponse(containerId, data) {
            const container = document.getElementById(containerId);
            container.style.display = 'block';
            container.innerHTML = `<strong>Response:</strong><br>${JSON.stringify(data, null, 2)}`;
        }

        function connectWebSocket() {
            const protocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
            const wsUrl = `${protocol}//${window.location.host}/ws/art-engine`;
            
            ws = new WebSocket(wsUrl);
            
            ws.onopen = function() {
                document.getElementById('wsStatus').textContent = 'Connected';
                document.getElementById('wsMessages').style.display = 'block';
                document.getElementById('wsMessages').innerHTML = '<strong>ART-Engine WebSocket connected!</strong>';
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
        loadAllAgents();
    </script>
</body>
</html>