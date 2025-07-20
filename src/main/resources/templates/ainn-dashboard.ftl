<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AINN 1.0 - AI Neural Network Dashboard</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #0a0a0a 0%, #1a1a2e 50%, #16213e 100%);
            color: #e8e8e8; 
            min-height: 100vh;
        }
        .container { max-width: 1600px; margin: 0 auto; padding: 20px; }
        .header { 
            text-align: center; 
            margin-bottom: 40px; 
            background: rgba(255, 255, 255, 0.05);
            border-radius: 20px;
            padding: 30px;
            border: 1px solid rgba(255, 255, 255, 0.1);
        }
        .header h1 { 
            color: #00ffff; 
            font-size: 4em; 
            margin-bottom: 10px; 
            text-shadow: 0 0 30px rgba(0, 255, 255, 0.7);
        }
        .header p { 
            font-size: 1.4em; 
            opacity: 0.9; 
            margin-bottom: 15px;
        }
        .subtitle { 
            font-style: italic; 
            color: #ffd700; 
            font-size: 1.2em;
        }
        .grid { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(400px, 1fr)); 
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
            box-shadow: 0 10px 30px rgba(0, 255, 255, 0.3);
        }
        .card h3 { 
            color: #00ffff; 
            margin-bottom: 20px; 
            font-size: 1.5em;
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
            border-color: #00ffff;
            box-shadow: 0 0 15px rgba(0, 255, 255, 0.4);
        }
        .btn { 
            background: linear-gradient(135deg, #00ffff, #0080ff); 
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
            background: linear-gradient(135deg, #0080ff, #0040ff);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 255, 255, 0.5);
        }
        .response { 
            margin-top: 20px; 
            padding: 15px; 
            background: rgba(0, 0, 0, 0.4); 
            border-radius: 10px; 
            border-left: 4px solid #00ffff; 
            max-height: 400px;
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
            color: #00ffff; 
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
            background: #00ffff; 
            margin-right: 10px;
            box-shadow: 0 0 10px rgba(0, 255, 255, 0.5);
        }
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
            border-bottom-color: #00ffff;
            color: #00ffff;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
        .neural-network {
            background: rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            padding: 20px;
            margin-top: 15px;
        }
        .node {
            display: inline-block;
            background: rgba(0, 255, 255, 0.2);
            border: 1px solid #00ffff;
            border-radius: 50%;
            width: 60px;
            height: 60px;
            margin: 10px;
            text-align: center;
            line-height: 60px;
            font-size: 12px;
            transition: all 0.3s ease;
        }
        .node:hover {
            background: rgba(0, 255, 255, 0.4);
            transform: scale(1.1);
        }
        .connection {
            position: relative;
            height: 2px;
            background: linear-gradient(90deg, #00ffff, #0080ff);
            margin: 5px 0;
            border-radius: 1px;
        }
        .pattern-item {
            background: rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 10px;
            border-left: 3px solid #00ffff;
        }
        .pattern-name {
            font-weight: bold;
            color: #00ffff;
            margin-bottom: 5px;
        }
        .pattern-confidence {
            color: #ffd700;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🧠 AINN 1.0</h1>
            <p>AI Neural Network - The Core of Every AI Application</p>
            <p class="subtitle">"The Foundation of Intelligence - Where Every AI Begins"</p>
            <p><span class="status-indicator"></span>Neural Processing Engine Active</p>
        </div>

        <div class="grid">
            <!-- Network Status -->
            <div class="card">
                <h3>📊 Network Status</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="totalNodes">0</div>
                        <div class="metric-label">Nodes</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="totalConnections">0</div>
                        <div class="metric-label">Connections</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="totalLayers">0</div>
                        <div class="metric-label">Layers</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="totalPatterns">0</div>
                        <div class="metric-label">Patterns</div>
                    </div>
                </div>
            </div>

            <!-- Signal Processing -->
            <div class="card">
                <h3>⚡ Signal Processing</h3>
                <div class="tabs">
                    <div class="tab active" onclick="switchTab('text')">Text</div>
                    <div class="tab" onclick="switchTab('numerical')">Numerical</div>
                    <div class="tab" onclick="switchTab('command')">Command</div>
                    <div class="tab" onclick="switchTab('learning')">Learning</div>
                </div>
                
                <div id="text" class="tab-content active">
                    <div class="input-group">
                        <label>Text Input</label>
                        <textarea id="textInput" rows="3" placeholder="Enter text to process through the neural network..."></textarea>
                    </div>
                    <button class="btn" onclick="processTextSignal()">Process Text Signal</button>
                </div>
                
                <div id="numerical" class="tab-content">
                    <div class="input-group">
                        <label>Numerical Data (comma-separated)</label>
                        <input type="text" id="numericalInput" placeholder="1.5, 2.3, 4.7, 8.1">
                    </div>
                    <button class="btn" onclick="processNumericalSignal()">Process Numerical Signal</button>
                </div>
                
                <div id="command" class="tab-content">
                    <div class="input-group">
                        <label>Command</label>
                        <input type="text" id="commandInput" placeholder="Enter neural network command...">
                    </div>
                    <button class="btn" onclick="processCommandSignal()">Process Command Signal</button>
                </div>
                
                <div id="learning" class="tab-content">
                    <div class="input-group">
                        <label>Learning Data (JSON)</label>
                        <textarea id="learningInput" rows="3" placeholder='{"pattern": "example", "action": "learn"}'></textarea>
                    </div>
                    <button class="btn" onclick="processLearningSignal()">Process Learning Signal</button>
                </div>
                
                <div id="signalResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Node Management -->
            <div class="card">
                <h3>🔧 Node Management</h3>
                <div class="tabs">
                    <div class="tab active" onclick="switchTab('create')">Create</div>
                    <div class="tab" onclick="switchTab('view')">View</div>
                </div>
                
                <div id="create" class="tab-content active">
                    <div class="input-group">
                        <label>Node ID</label>
                        <input type="text" id="nodeId" placeholder="unique-node-id">
                    </div>
                    <div class="input-group">
                        <label>Node Name</label>
                        <input type="text" id="nodeName" placeholder="My Neural Node">
                    </div>
                    <div class="input-group">
                        <label>Node Type</label>
                        <select id="nodeType">
                            <option value="PROCESSOR">Processor</option>
                            <option value="DECISION">Decision</option>
                            <option value="MEMORY">Memory</option>
                            <option value="LEARNING">Learning</option>
                            <option value="GATEWAY">Gateway</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <label>Layer</label>
                        <select id="nodeLayer">
                            <option value="input">Input Layer</option>
                            <option value="processing">Processing Layer</option>
                            <option value="output">Output Layer</option>
                            <option value="memory">Memory Layer</option>
                            <option value="learning">Learning Layer</option>
                        </select>
                    </div>
                    <button class="btn" onclick="createNode()">Create Node</button>
                </div>
                
                <div id="view" class="tab-content">
                    <button class="btn" onclick="loadAllNodes()" style="margin-bottom: 15px;">Load All Nodes</button>
                    <div id="nodesList" class="neural-network"></div>
                </div>
                
                <div id="nodeResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Pattern Management -->
            <div class="card">
                <h3>🔍 Pattern Management</h3>
                <div class="tabs">
                    <div class="tab active" onclick="switchTab('create-pattern')">Create</div>
                    <div class="tab" onclick="switchTab('search-pattern')">Search</div>
                </div>
                
                <div id="create-pattern" class="tab-content active">
                    <div class="input-group">
                        <label>Pattern ID</label>
                        <input type="text" id="patternId" placeholder="unique-pattern-id">
                    </div>
                    <div class="input-group">
                        <label>Pattern Name</label>
                        <input type="text" id="patternName" placeholder="My Neural Pattern">
                    </div>
                    <div class="input-group">
                        <label>Pattern Type</label>
                        <select id="patternType">
                            <option value="RECOGNITION">Recognition</option>
                            <option value="LEARNING">Learning</option>
                            <option value="MEMORY">Memory</option>
                            <option value="BEHAVIOR">Behavior</option>
                            <option value="SEQUENCE">Sequence</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <label>Pattern Data (JSON)</label>
                        <textarea id="patternData" rows="3" placeholder='{"key": "value", "feature": "data"}'></textarea>
                    </div>
                    <button class="btn" onclick="createPattern()">Create Pattern</button>
                </div>
                
                <div id="search-pattern" class="tab-content">
                    <div class="input-group">
                        <label>Search Criteria (JSON)</label>
                        <textarea id="searchCriteria" rows="3" placeholder='{"pattern_type": "RECOGNITION"}'></textarea>
                    </div>
                    <button class="btn" onclick="searchPatterns()">Search Patterns</button>
                    <div id="patternsList" class="neural-network"></div>
                </div>
                
                <div id="patternResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Memory Management -->
            <div class="card">
                <h3>💾 Memory Management</h3>
                <div class="tabs">
                    <div class="tab active" onclick="switchTab('short-term')">Short-term</div>
                    <div class="tab" onclick="switchTab('long-term')">Long-term</div>
                </div>
                
                <div id="short-term" class="tab-content active">
                    <div class="input-group">
                        <label>Memory Key</label>
                        <input type="text" id="stMemoryKey" placeholder="memory-key">
                    </div>
                    <div class="input-group">
                        <label>Memory Value</label>
                        <textarea id="stMemoryValue" rows="2" placeholder="Memory content..."></textarea>
                    </div>
                    <button class="btn" onclick="storeShortTermMemory()">Store</button>
                    <button class="btn" onclick="getShortTermMemory()">Retrieve</button>
                    <button class="btn" onclick="clearShortTermMemory()">Clear All</button>
                </div>
                
                <div id="long-term" class="tab-content">
                    <div class="input-group">
                        <label>Memory Key</label>
                        <input type="text" id="ltMemoryKey" placeholder="memory-key">
                    </div>
                    <div class="input-group">
                        <label>Memory Value</label>
                        <textarea id="ltMemoryValue" rows="2" placeholder="Long-term memory content..."></textarea>
                    </div>
                    <button class="btn" onclick="storeLongTermMemory()">Store</button>
                    <button class="btn" onclick="getLongTermMemory()">Retrieve</button>
                </div>
                
                <div id="memoryResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Network Analysis -->
            <div class="card">
                <h3>📈 Network Analysis</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="networkHealth">0%</div>
                        <div class="metric-label">Health</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="processingEfficiency">0%</div>
                        <div class="metric-label">Efficiency</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="learningProgress">0%</div>
                        <div class="metric-label">Learning</div>
                    </div>
                </div>
                <button class="btn" onclick="analyzeNetwork()" style="margin-top: 15px;">Analyze Network</button>
                <button class="btn" onclick="optimizeNetwork()" style="margin-top: 10px;">Optimize Network</button>
                <div id="analysisResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Configuration -->
            <div class="card">
                <h3>⚙️ Network Configuration</h3>
                <div class="input-group">
                    <label>Max Nodes</label>
                    <input type="number" id="maxNodes" placeholder="1000">
                </div>
                <div class="input-group">
                    <label>Max Connections</label>
                    <input type="number" id="maxConnections" placeholder="5000">
                </div>
                <div class="input-group">
                    <label>Learning Rate</label>
                    <input type="number" id="learningRate" step="0.01" placeholder="0.01">
                </div>
                <div class="input-group">
                    <label>Enable Learning</label>
                    <select id="enableLearning">
                        <option value="true">Enabled</option>
                        <option value="false">Disabled</option>
                    </select>
                </div>
                <button class="btn" onclick="updateConfiguration()">Update Configuration</button>
                <button class="btn" onclick="getConfiguration()">Get Configuration</button>
                <div id="configResponse" class="response" style="display:none;"></div>
            </div>
        </div>
    </div>

    <script>
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

        // Signal Processing
        async function processTextSignal() {
            const text = document.getElementById('textInput').value;
            if (!text) return;

            try {
                const response = await fetch('/api/v1/ainn/process', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        data: { text: text, type: 'text_processing' },
                        sourceNodeId: 'text_processor',
                        strength: 0.8,
                        significance: 0.6
                    })
                });
                const data = await response.json();
                showResponse('signalResponse', data);
            } catch (error) {
                showResponse('signalResponse', { success: false, error: error.message });
            }
        }

        async function processNumericalSignal() {
            const numbersStr = document.getElementById('numericalInput').value;
            if (!numbersStr) return;

            const numbers = numbersStr.split(',').map(n => parseFloat(n.trim())).filter(n => !isNaN(n));

            try {
                const response = await fetch('/api/v1/ainn/process', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        data: { numbers: numbers, type: 'numerical_processing' },
                        sourceNodeId: 'numerical_processor',
                        strength: 0.9,
                        significance: 0.7
                    })
                });
                const data = await response.json();
                showResponse('signalResponse', data);
            } catch (error) {
                showResponse('signalResponse', { success: false, error: error.message });
            }
        }

        async function processCommandSignal() {
            const command = document.getElementById('commandInput').value;
            if (!command) return;

            try {
                const response = await fetch('/api/v1/ainn/process', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        data: { command: command, type: 'command_processing' },
                        sourceNodeId: 'command_processor',
                        strength: 1.0,
                        significance: 0.8
                    })
                });
                const data = await response.json();
                showResponse('signalResponse', data);
            } catch (error) {
                showResponse('signalResponse', { success: false, error: error.message });
            }
        }

        async function processLearningSignal() {
            const learningDataStr = document.getElementById('learningInput').value;
            if (!learningDataStr) return;

            try {
                const learningData = JSON.parse(learningDataStr);
                const response = await fetch('/api/v1/ainn/process', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        data: { ...learningData, type: 'learning_processing' },
                        sourceNodeId: 'learning_processor',
                        strength: 0.7,
                        significance: 0.9
                    })
                });
                const data = await response.json();
                showResponse('signalResponse', data);
            } catch (error) {
                showResponse('signalResponse', { success: false, error: error.message });
            }
        }

        // Node Management
        async function createNode() {
            const nodeData = {
                nodeId: document.getElementById('nodeId').value,
                name: document.getElementById('nodeName').value,
                layerId: document.getElementById('nodeLayer').value,
                type: document.getElementById('nodeType').value
            };

            try {
                const response = await fetch('/api/v1/ainn/nodes', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(nodeData)
                });
                const data = await response.json();
                showResponse('nodeResponse', data);
            } catch (error) {
                showResponse('nodeResponse', { success: false, error: error.message });
            }
        }

        async function loadAllNodes() {
            try {
                const response = await fetch('/api/v1/ainn/nodes');
                const data = await response.json();
                displayNodes(data.nodes);
            } catch (error) {
                console.error('Error loading nodes:', error);
            }
        }

        function displayNodes(nodes) {
            const container = document.getElementById('nodesList');
            if (!nodes || nodes.length === 0) {
                container.innerHTML = '<p>No nodes found</p>';
                return;
            }

            container.innerHTML = nodes.map(node => `
                <div class="node" title="${node.name}">
                    ${node.nodeId.substring(0, 8)}
                </div>
            `).join('');
        }

        // Pattern Management
        async function createPattern() {
            const patternData = {
                patternId: document.getElementById('patternId').value,
                name: document.getElementById('patternName').value,
                data: JSON.parse(document.getElementById('patternData').value || '{}')
            };
            patternData.data.pattern_type = document.getElementById('patternType').value;

            try {
                const response = await fetch('/api/v1/ainn/patterns', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(patternData)
                });
                const data = await response.json();
                showResponse('patternResponse', data);
            } catch (error) {
                showResponse('patternResponse', { success: false, error: error.message });
            }
        }

        async function searchPatterns() {
            const criteria = JSON.parse(document.getElementById('searchCriteria').value || '{}');

            try {
                const response = await fetch('/api/v1/ainn/patterns/search', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(criteria)
                });
                const data = await response.json();
                displayPatterns(data.patterns);
                showResponse('patternResponse', data);
            } catch (error) {
                showResponse('patternResponse', { success: false, error: error.message });
            }
        }

        function displayPatterns(patterns) {
            const container = document.getElementById('patternsList');
            if (!patterns || patterns.length === 0) {
                container.innerHTML = '<p>No patterns found</p>';
                return;
            }

            container.innerHTML = patterns.map(pattern => `
                <div class="pattern-item">
                    <div class="pattern-name">${pattern.name}</div>
                    <div class="pattern-confidence">Confidence: ${(pattern.confidence * 100).toFixed(1)}% | Frequency: ${pattern.frequency}</div>
                </div>
            `).join('');
        }

        // Memory Management
        async function storeShortTermMemory() {
            const key = document.getElementById('stMemoryKey').value;
            const value = document.getElementById('stMemoryValue').value;

            try {
                const response = await fetch('/api/v1/ainn/memory/short-term', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ key, value })
                });
                const data = await response.json();
                showResponse('memoryResponse', data);
            } catch (error) {
                showResponse('memoryResponse', { success: false, error: error.message });
            }
        }

        async function getShortTermMemory() {
            const key = document.getElementById('stMemoryKey').value;

            try {
                const response = await fetch(`/api/v1/ainn/memory/short-term/${key}`);
                const data = await response.json();
                showResponse('memoryResponse', data);
            } catch (error) {
                showResponse('memoryResponse', { success: false, error: error.message });
            }
        }

        async function clearShortTermMemory() {
            try {
                const response = await fetch('/api/v1/ainn/memory/short-term', {
                    method: 'DELETE'
                });
                const data = await response.json();
                showResponse('memoryResponse', data);
            } catch (error) {
                showResponse('memoryResponse', { success: false, error: error.message });
            }
        }

        async function storeLongTermMemory() {
            const key = document.getElementById('ltMemoryKey').value;
            const value = document.getElementById('ltMemoryValue').value;

            try {
                const response = await fetch('/api/v1/ainn/memory/long-term', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ key, value })
                });
                const data = await response.json();
                showResponse('memoryResponse', data);
            } catch (error) {
                showResponse('memoryResponse', { success: false, error: error.message });
            }
        }

        async function getLongTermMemory() {
            const key = document.getElementById('ltMemoryKey').value;

            try {
                const response = await fetch(`/api/v1/ainn/memory/long-term/${key}`);
                const data = await response.json();
                showResponse('memoryResponse', data);
            } catch (error) {
                showResponse('memoryResponse', { success: false, error: error.message });
            }
        }

        // Network Analysis
        async function analyzeNetwork() {
            try {
                const response = await fetch('/api/v1/ainn/analysis');
                const data = await response.json();
                
                // Update metrics
                const analysis = data.analysis;
                document.getElementById('totalNodes').textContent = analysis.total_nodes || 0;
                document.getElementById('totalConnections').textContent = analysis.total_connections || 0;
                document.getElementById('totalLayers').textContent = analysis.total_layers || 0;
                document.getElementById('totalPatterns').textContent = analysis.total_patterns || 0;
                document.getElementById('networkHealth').textContent = Math.round((analysis.network_health || 0) * 100) + '%';
                document.getElementById('processingEfficiency').textContent = Math.round((analysis.processing_efficiency || 0) * 100) + '%';
                document.getElementById('learningProgress').textContent = Math.round((analysis.learning_progress || 0) * 100) + '%';
                
                showResponse('analysisResponse', data);
            } catch (error) {
                showResponse('analysisResponse', { success: false, error: error.message });
            }
        }

        async function optimizeNetwork() {
            try {
                const response = await fetch('/api/v1/ainn/optimize', {
                    method: 'POST'
                });
                const data = await response.json();
                showResponse('analysisResponse', data);
            } catch (error) {
                showResponse('analysisResponse', { success: false, error: error.message });
            }
        }

        // Configuration
        async function updateConfiguration() {
            const config = {
                maxNodes: parseInt(document.getElementById('maxNodes').value),
                maxConnections: parseInt(document.getElementById('maxConnections').value),
                learningRate: parseFloat(document.getElementById('learningRate').value),
                enableLearning: document.getElementById('enableLearning').value === 'true'
            };

            try {
                const response = await fetch('/api/v1/ainn/config', {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(config)
                });
                const data = await response.json();
                showResponse('configResponse', data);
            } catch (error) {
                showResponse('configResponse', { success: false, error: error.message });
            }
        }

        async function getConfiguration() {
            try {
                const response = await fetch('/api/v1/ainn/config');
                const data = await response.json();
                showResponse('configResponse', data);
            } catch (error) {
                showResponse('configResponse', { success: false, error: error.message });
            }
        }

        function showResponse(containerId, data) {
            const container = document.getElementById(containerId);
            container.style.display = 'block';
            container.innerHTML = `<strong>Response:</strong><br>${JSON.stringify(data, null, 2)}`;
        }

        // Initialize
        analyzeNetwork();
    </script>
</body>
</html> 