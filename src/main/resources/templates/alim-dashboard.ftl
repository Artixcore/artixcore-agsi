<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alim 1.0 - AI-powered Meta-Research Intelligence System</title>
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
            color: #00d4aa; 
            font-size: 3.5em; 
            margin-bottom: 10px; 
            text-shadow: 0 0 20px rgba(0, 212, 170, 0.5);
        }
        .header p { 
            font-size: 1.3em; 
            opacity: 0.9; 
            margin-bottom: 15px;
        }
        .inspiration { 
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
            box-shadow: 0 10px 30px rgba(0, 212, 170, 0.2);
        }
        .card h3 { 
            color: #00d4aa; 
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
            border-color: #00d4aa;
            box-shadow: 0 0 10px rgba(0, 212, 170, 0.3);
        }
        .btn { 
            background: linear-gradient(135deg, #00d4aa, #00b894); 
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
            background: linear-gradient(135deg, #00b894, #00a085);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 212, 170, 0.4);
        }
        .response { 
            margin-top: 20px; 
            padding: 15px; 
            background: rgba(0, 0, 0, 0.4); 
            border-radius: 10px; 
            border-left: 4px solid #00d4aa; 
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
            color: #00d4aa; 
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
            background: #00d4aa; 
            margin-right: 10px;
            box-shadow: 0 0 10px rgba(0, 212, 170, 0.5);
        }
        .capabilities { 
            display: flex; 
            flex-wrap: wrap; 
            gap: 10px; 
            margin-top: 15px;
        }
        .capability { 
            background: rgba(0, 212, 170, 0.2); 
            color: #00d4aa; 
            padding: 5px 12px; 
            border-radius: 20px; 
            font-size: 0.8em;
            border: 1px solid rgba(0, 212, 170, 0.3);
        }
        .progress-bar {
            width: 100%;
            height: 6px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 3px;
            overflow: hidden;
            margin-top: 10px;
        }
        .progress-fill {
            height: 100%;
            background: linear-gradient(90deg, #00d4aa, #00b894);
            transition: width 0.3s ease;
        }
        .streaming-content {
            font-family: 'Courier New', monospace;
            font-size: 0.9em;
            line-height: 1.4;
        }
        .islamic-mode {
            background: linear-gradient(135deg, rgba(255, 215, 0, 0.1), rgba(255, 215, 0, 0.05));
            border: 1px solid rgba(255, 215, 0, 0.3);
        }
        .islamic-mode h3 {
            color: #ffd700;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🧠 Alim 1.0</h1>
            <p>AI-powered Meta-Research Intelligence System</p>
            <p class="inspiration">"Inspired by the legendary polymath Al-Kindi"</p>
            <p><span class="status-indicator"></span>Elevating researchers into polymaths through divine machine intellect</p>
        </div>

        <div class="grid">
            <!-- Status Card -->
            <div class="card">
                <h3>📊 System Status</h3>
                <div class="metrics">
                    <div class="metric">
                        <div class="metric-value" id="status">Active</div>
                        <div class="metric-label">Status</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="uptime">0s</div>
                        <div class="metric-label">Uptime</div>
                    </div>
                    <div class="metric">
                        <div class="metric-value" id="capabilities">9</div>
                        <div class="metric-label">Modules</div>
                    </div>
                </div>
                <div class="capabilities" id="capabilitiesList">
                    <div class="capability">NeuroResearch</div>
                    <div class="capability">AutoReviewer</div>
                    <div class="capability">HypothesisForge</div>
                    <div class="capability">CitationMesh</div>
                    <div class="capability">IslamicKnowledge</div>
                </div>
            </div>

            <!-- NeuroResearch Engine -->
            <div class="card">
                <h3>🧬 NeuroResearch Engine</h3>
                <div class="input-group">
                    <label>Research Text</label>
                    <textarea id="neuroResearchText" rows="4" placeholder="Enter research text for cross-discipline analysis..."></textarea>
                </div>
                <div class="input-group">
                    <label>Disciplines</label>
                    <input type="text" id="neuroDisciplines" placeholder="physics, metaphysics, AI, ethics (comma-separated)">
                </div>
                <button class="btn" onclick="analyzeResearch()">Analyze Research</button>
                <div id="neuroResponse" class="response" style="display:none;"></div>
            </div>

            <!-- AutoReviewer -->
            <div class="card">
                <h3>📚 AutoReviewer</h3>
                <div class="input-group">
                    <label>Paper Content</label>
                    <textarea id="paperContent" rows="4" placeholder="Enter paper content for peer review..."></textarea>
                </div>
                <div class="input-group">
                    <label>Journal Standards</label>
                    <select id="journalStandards">
                        <option value="high_impact">High Impact</option>
                        <option value="standard">Standard</option>
                        <option value="preliminary">Preliminary</option>
                    </select>
                </div>
                <button class="btn" onclick="peerReview()">Conduct Review</button>
                <div id="reviewResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Hypothesis Forge -->
            <div class="card">
                <h3>🧪 Hypothesis Forge</h3>
                <div class="input-group">
                    <label>Problem Statement</label>
                    <textarea id="problemStatement" rows="3" placeholder="Describe the research problem..."></textarea>
                </div>
                <div class="input-group">
                    <label>Domain</label>
                    <input type="text" id="hypothesisDomain" placeholder="e.g., quantum physics, neuroscience">
                </div>
                <button class="btn" onclick="generateHypotheses()">Generate Hypotheses</button>
                <div id="hypothesisResponse" class="response" style="display:none;"></div>
            </div>

            <!-- DeepMemory CiteMesh -->
            <div class="card">
                <h3>🌙 DeepMemory CiteMesh™</h3>
                <div class="input-group">
                    <label>Research Paper</label>
                    <textarea id="researchPaper" rows="3" placeholder="Enter research paper for citation analysis..."></textarea>
                </div>
                <div class="input-group">
                    <label>Time Range</label>
                    <select id="timeRange">
                        <option value="5_years">5 Years</option>
                        <option value="10_years">10 Years</option>
                        <option value="20_years">20 Years</option>
                    </select>
                </div>
                <button class="btn" onclick="generateCitationMesh()">Generate Citation Mesh</button>
                <div id="citationResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Islamic Knowledge Mode -->
            <div class="card islamic-mode">
                <h3>🕌 Islamic Knowledge Mode</h3>
                <div class="input-group">
                    <label>Research Topic</label>
                    <input type="text" id="islamicTopic" placeholder="Enter research topic for ma'rifah analysis...">
                </div>
                <div class="input-group">
                    <label>Knowledge Depth</label>
                    <select id="knowledgeDepth">
                        <option value="comprehensive">Comprehensive</option>
                        <option value="spiritual">Spiritual Focus</option>
                        <option value="analytical">Analytical</option>
                    </select>
                </div>
                <button class="btn" onclick="deepKnowledgeAnalysis()">Deep Knowledge Analysis</button>
                <div id="islamicResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Cross-Discipline Linking -->
            <div class="card">
                <h3>🔗 Cross-Discipline Linking</h3>
                <div class="input-group">
                    <label>Primary Discipline</label>
                    <input type="text" id="primaryDiscipline" placeholder="e.g., physics">
                </div>
                <div class="input-group">
                    <label>Secondary Discipline</label>
                    <input type="text" id="secondaryDiscipline" placeholder="e.g., metaphysics">
                </div>
                <div class="input-group">
                    <label>Research Question</label>
                    <textarea id="crossDisciplineQuestion" rows="2" placeholder="What connects these disciplines?"></textarea>
                </div>
                <button class="btn" onclick="crossDisciplineLink()">Link Disciplines</button>
                <div id="crossDisciplineResponse" class="response" style="display:none;"></div>
            </div>

            <!-- Metrics -->
            <div class="card">
                <h3>📈 Research Metrics</h3>
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
                        <div class="metric-value" id="islamicRequests">0</div>
                        <div class="metric-label">Islamic Knowledge</div>
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
        async function analyzeResearch() {
            const researchText = document.getElementById('neuroResearchText').value;
            const disciplines = document.getElementById('neuroDisciplines').value.split(',').map(d => d.trim());
            
            if (!researchText) return;

            try {
                const response = await fetch('/api/v1/alim/neuro-research/analyze', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ 
                        researchText, 
                        disciplines,
                        analysisDepth: 'comprehensive',
                        includeCrossDiscipline: true,
                        includeEmergentPatterns: true
                    })
                });
                const data = await response.json();
                document.getElementById('neuroResponse').style.display = 'block';
                document.getElementById('neuroResponse').innerHTML = `
                    <strong>Analysis:</strong><br>
                    ${data.analysis || data.error}<br><br>
                    <strong>Cross-Discipline Insights:</strong> ${data.cross_discipline_insights}<br>
                    <strong>Emergent Patterns:</strong> ${data.emergent_patterns}
                `;
            } catch (error) {
                document.getElementById('neuroResponse').style.display = 'block';
                document.getElementById('neuroResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function peerReview() {
            const paperContent = document.getElementById('paperContent').value;
            const journalStandards = document.getElementById('journalStandards').value;
            
            if (!paperContent) return;

            try {
                const response = await fetch('/api/v1/alim/auto-reviewer/review', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ 
                        paperContent, 
                        journalStandards,
                        reviewFocus: ['logic', 'citations', 'methodology']
                    })
                });
                const data = await response.json();
                document.getElementById('reviewResponse').style.display = 'block';
                document.getElementById('reviewResponse').innerHTML = `
                    <strong>Review:</strong><br>
                    ${data.review || data.error}<br><br>
                    <strong>Logic Flaws:</strong> ${data.logic_flaws}<br>
                    <strong>Citation Issues:</strong> ${data.citation_issues}<br>
                    <strong>Bias Detection:</strong> ${data.bias_detection}
                `;
            } catch (error) {
                document.getElementById('reviewResponse').style.display = 'block';
                document.getElementById('reviewResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function generateHypotheses() {
            const problemStatement = document.getElementById('problemStatement').value;
            const domain = document.getElementById('hypothesisDomain').value;
            
            if (!problemStatement) return;

            try {
                const response = await fetch('/api/v1/alim/hypothesis-forge/generate', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ 
                        problemStatement, 
                        domain,
                        hypothesisCount: 5
                    })
                });
                const data = await response.json();
                document.getElementById('hypothesisResponse').style.display = 'block';
                document.getElementById('hypothesisResponse').innerHTML = `
                    <strong>Hypotheses:</strong><br>
                    ${data.hypotheses || data.error}<br><br>
                    <strong>Grant-Worthiness:</strong> ${data.grant_worthiness_scores}<br>
                    <strong>Feasibility:</strong> ${data.feasibility_scores}
                `;
            } catch (error) {
                document.getElementById('hypothesisResponse').style.display = 'block';
                document.getElementById('hypothesisResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function generateCitationMesh() {
            const researchPaper = document.getElementById('researchPaper').value;
            const timeRange = document.getElementById('timeRange').value;
            
            if (!researchPaper) return;

            try {
                const response = await fetch('/api/v1/alim/deep-memory/citation-mesh', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ 
                        researchPaper, 
                        timeRange,
                        includeInfluenceTracking: true,
                        includeImpactPrediction: true
                    })
                });
                const data = await response.json();
                document.getElementById('citationResponse').style.display = 'block';
                document.getElementById('citationResponse').innerHTML = `
                    <strong>Citation Mesh:</strong><br>
                    ${data.citation_mesh || data.error}<br><br>
                    <strong>Influence Tracking:</strong> ${data.influence_tracking}<br>
                    <strong>Impact Prediction:</strong> ${data.impact_prediction}
                `;
            } catch (error) {
                document.getElementById('citationResponse').style.display = 'block';
                document.getElementById('citationResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function deepKnowledgeAnalysis() {
            const researchTopic = document.getElementById('islamicTopic').value;
            const knowledgeDepth = document.getElementById('knowledgeDepth').value;
            
            if (!researchTopic) return;

            try {
                const response = await fetch('/api/v1/alim/islamic-knowledge/ma-rifah', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ 
                        researchTopic, 
                        knowledgeDepth,
                        includeSpiritual: true,
                        includeTawhidicWorldview: true
                    })
                });
                const data = await response.json();
                document.getElementById('islamicResponse').style.display = 'block';
                document.getElementById('islamicResponse').innerHTML = `
                    <strong>Deep Knowledge Analysis:</strong><br>
                    ${data.deep_knowledge_analysis || data.error}<br><br>
                    <strong>Spiritual Insights:</strong> ${data.spiritual_insights}<br>
                    <strong>Tawhidic Perspective:</strong> ${data.tawhidic_perspective}
                `;
            } catch (error) {
                document.getElementById('islamicResponse').style.display = 'block';
                document.getElementById('islamicResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function crossDisciplineLink() {
            const primaryDiscipline = document.getElementById('primaryDiscipline').value;
            const secondaryDiscipline = document.getElementById('secondaryDiscipline').value;
            const researchQuestion = document.getElementById('crossDisciplineQuestion').value;
            
            if (!primaryDiscipline || !secondaryDiscipline) return;

            try {
                const response = await fetch('/api/v1/alim/cross-discipline/link', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ 
                        primaryDiscipline, 
                        secondaryDiscipline,
                        researchQuestion
                    })
                });
                const data = await response.json();
                document.getElementById('crossDisciplineResponse').style.display = 'block';
                document.getElementById('crossDisciplineResponse').innerHTML = `
                    <strong>Cross-Discipline Analysis:</strong><br>
                    ${data.cross_discipline_analysis || data.error}<br><br>
                    <strong>Metaphysical Connections:</strong> ${data.metaphysical_connections}<br>
                    <strong>Practical Applications:</strong> ${data.practical_applications}
                `;
            } catch (error) {
                document.getElementById('crossDisciplineResponse').style.display = 'block';
                document.getElementById('crossDisciplineResponse').innerHTML = `<strong>Error:</strong> ${error.message}`;
            }
        }

        async function refreshMetrics() {
            try {
                const response = await fetch('/api/v1/alim/status');
                const data = await response.json();
                console.log('Alim Metrics:', data);
            } catch (error) {
                console.error('Error fetching metrics:', error);
            }
        }

        function connectWebSocket() {
            const protocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
            const wsUrl = `${protocol}//${window.location.host}/ws/alim`;
            
            ws = new WebSocket(wsUrl);
            
            ws.onopen = function() {
                document.getElementById('wsStatus').textContent = 'Connected';
                document.getElementById('wsMessages').style.display = 'block';
                document.getElementById('wsMessages').innerHTML = '<strong>Alim 1.0 WebSocket connected!</strong>';
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