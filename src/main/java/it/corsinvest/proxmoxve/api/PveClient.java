/*
 * SPDX-FileCopyrightText: 2022 Daniele Corsini <daniele.corsini@corsinvest.it>
 * SPDX-FileCopyrightText: Copyright Corsinvest Srl
 * SPDX-License-Identifier: GPL-3.0-only
 */
package it.corsinvest.proxmoxve.api;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/**
 * Proxmox VE Client
 */
public class PveClient extends PveClientBase {

    private final PveClient _client;

    public PveClient(String hostname, int port) {
        super(hostname, port);
        _client = this;
    }

    private PVECluster _cluster;

    public PVECluster getCluster() {
        return _cluster == null ? (_cluster = new PVECluster(_client)) : _cluster;
    }
    private PVENodes _nodes;

    public PVENodes getNodes() {
        return _nodes == null ? (_nodes = new PVENodes(_client)) : _nodes;
    }
    private PVEStorage _storage;

    public PVEStorage getStorage() {
        return _storage == null ? (_storage = new PVEStorage(_client)) : _storage;
    }
    private PVEAccess _access;

    public PVEAccess getAccess() {
        return _access == null ? (_access = new PVEAccess(_client)) : _access;
    }
    private PVEPools _pools;

    public PVEPools getPools() {
        return _pools == null ? (_pools = new PVEPools(_client)) : _pools;
    }
    private PVEVersion _version;

    public PVEVersion getVersion() {
        return _version == null ? (_version = new PVEVersion(_client)) : _version;
    }

    public class PVECluster {

        private final PveClient _client;

        protected PVECluster(PveClient client) {
            _client = client;

        }

        private PVEReplication _replication;

        public PVEReplication getReplication() {
            return _replication == null ? (_replication = new PVEReplication(_client)) : _replication;
        }
        private PVEMetrics _metrics;

        public PVEMetrics getMetrics() {
            return _metrics == null ? (_metrics = new PVEMetrics(_client)) : _metrics;
        }
        private PVEConfig _config;

        public PVEConfig getConfig() {
            return _config == null ? (_config = new PVEConfig(_client)) : _config;
        }
        private PVEFirewall _firewall;

        public PVEFirewall getFirewall() {
            return _firewall == null ? (_firewall = new PVEFirewall(_client)) : _firewall;
        }
        private PVEBackup _backup;

        public PVEBackup getBackup() {
            return _backup == null ? (_backup = new PVEBackup(_client)) : _backup;
        }
        private PVEBackupInfo _backupInfo;

        public PVEBackupInfo getBackupInfo() {
            return _backupInfo == null ? (_backupInfo = new PVEBackupInfo(_client)) : _backupInfo;
        }
        private PVEHa _ha;

        public PVEHa getHa() {
            return _ha == null ? (_ha = new PVEHa(_client)) : _ha;
        }
        private PVEAcme _acme;

        public PVEAcme getAcme() {
            return _acme == null ? (_acme = new PVEAcme(_client)) : _acme;
        }
        private PVECeph _ceph;

        public PVECeph getCeph() {
            return _ceph == null ? (_ceph = new PVECeph(_client)) : _ceph;
        }
        private PVEJobs _jobs;

        public PVEJobs getJobs() {
            return _jobs == null ? (_jobs = new PVEJobs(_client)) : _jobs;
        }
        private PVESdn _sdn;

        public PVESdn getSdn() {
            return _sdn == null ? (_sdn = new PVESdn(_client)) : _sdn;
        }
        private PVELog _log;

        public PVELog getLog() {
            return _log == null ? (_log = new PVELog(_client)) : _log;
        }
        private PVEResources _resources;

        public PVEResources getResources() {
            return _resources == null ? (_resources = new PVEResources(_client)) : _resources;
        }
        private PVETasks _tasks;

        public PVETasks getTasks() {
            return _tasks == null ? (_tasks = new PVETasks(_client)) : _tasks;
        }
        private PVEOptions _options;

        public PVEOptions getOptions() {
            return _options == null ? (_options = new PVEOptions(_client)) : _options;
        }
        private PVEStatus _status;

        public PVEStatus getStatus() {
            return _status == null ? (_status = new PVEStatus(_client)) : _status;
        }
        private PVENextid _nextid;

        public PVENextid getNextid() {
            return _nextid == null ? (_nextid = new PVENextid(_client)) : _nextid;
        }

        public class PVEReplication {

            private final PveClient _client;

            protected PVEReplication(PveClient client) {
                _client = client;

            }

            public PVEIdItem get(Object id) {
                return new PVEIdItem(_client, id);
            }

            public class PVEIdItem {

                private final PveClient _client;
                private final Object _id;

                protected PVEIdItem(PveClient client, Object id) {
                    _client = client;
                    _id = id;
                }

                /**
                 * Mark replication job for removal.
                 *
                 * @param force Will remove the jobconfig entry, but will not
                 * cleanup.
                 * @param keep Keep replicated data at target (do not remove).
                 * @return Result
                 * @throws JSONException
                 */
                public Result delete(Boolean force, Boolean keep) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("force", force);
                    parameters.put("keep", keep);
                    return _client.delete("/cluster/replication/" + _id + "", parameters);
                }

                /**
                 * Mark replication job for removal.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result delete() throws JSONException {
                    return _client.delete("/cluster/replication/" + _id + "", null);
                }

                /**
                 * Read replication job configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result read() throws JSONException {
                    return _client.get("/cluster/replication/" + _id + "", null);
                }

                /**
                 * Update replication job configuration.
                 *
                 * @param comment Description.
                 * @param delete A list of settings you want to delete.
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param disable Flag to disable/deactivate the entry.
                 * @param rate Rate limit in mbps (megabytes per second) as
                 * floating point number.
                 * @param remove_job Mark the replication job for removal. The
                 * job will remove all local replication snapshots. When set to
                 * 'full', it also tries to remove replicated volumes on the
                 * target. The job then removes itself from the configuration
                 * file. Enum: local,full
                 * @param schedule Storage replication schedule. The format is a
                 * subset of `systemd` calendar events.
                 * @param source For internal use, to detect if the guest was
                 * stolen.
                 * @return Result
                 * @throws JSONException
                 */

                public Result update(String comment, String delete, String digest, Boolean disable, Float rate, String remove_job, String schedule, String source) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("comment", comment);
                    parameters.put("delete", delete);
                    parameters.put("digest", digest);
                    parameters.put("disable", disable);
                    parameters.put("rate", rate);
                    parameters.put("remove_job", remove_job);
                    parameters.put("schedule", schedule);
                    parameters.put("source", source);
                    return _client.set("/cluster/replication/" + _id + "", parameters);
                }

                /**
                 * Update replication job configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result update() throws JSONException {
                    return _client.set("/cluster/replication/" + _id + "", null);
                }

            }

            /**
             * List replication jobs.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/replication", null);
            }

            /**
             * Create a new replication job
             *
             * @param id Replication Job ID. The ID is composed of a Guest ID
             * and a job number, separated by a hyphen, i.e.
             * '&amp;lt;GUEST&amp;gt;-&amp;lt;JOBNUM&amp;gt;'.
             * @param target Target node.
             * @param type Section type. Enum: local
             * @param comment Description.
             * @param disable Flag to disable/deactivate the entry.
             * @param rate Rate limit in mbps (megabytes per second) as floating
             * point number.
             * @param remove_job Mark the replication job for removal. The job
             * will remove all local replication snapshots. When set to 'full',
             * it also tries to remove replicated volumes on the target. The job
             * then removes itself from the configuration file. Enum: local,full
             * @param schedule Storage replication schedule. The format is a
             * subset of `systemd` calendar events.
             * @param source For internal use, to detect if the guest was
             * stolen.
             * @return Result
             * @throws JSONException
             */

            public Result create(String id, String target, String type, String comment, Boolean disable, Float rate, String remove_job, String schedule, String source) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id", id);
                parameters.put("target", target);
                parameters.put("type", type);
                parameters.put("comment", comment);
                parameters.put("disable", disable);
                parameters.put("rate", rate);
                parameters.put("remove_job", remove_job);
                parameters.put("schedule", schedule);
                parameters.put("source", source);
                return _client.create("/cluster/replication", parameters);
            }

            /**
             * Create a new replication job
             *
             * @param id Replication Job ID. The ID is composed of a Guest ID
             * and a job number, separated by a hyphen, i.e.
             * '&amp;lt;GUEST&amp;gt;-&amp;lt;JOBNUM&amp;gt;'.
             * @param target Target node.
             * @param type Section type. Enum: local
             * @return Result
             * @throws JSONException
             */

            public Result create(String id, String target, String type) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id", id);
                parameters.put("target", target);
                parameters.put("type", type);
                return _client.create("/cluster/replication", parameters);
            }

        }

        public class PVEMetrics {

            private final PveClient _client;

            protected PVEMetrics(PveClient client) {
                _client = client;

            }

            private PVEServer _server;

            public PVEServer getServer() {
                return _server == null ? (_server = new PVEServer(_client)) : _server;
            }

            public class PVEServer {

                private final PveClient _client;

                protected PVEServer(PveClient client) {
                    _client = client;

                }

                public PVEIdItem get(Object id) {
                    return new PVEIdItem(_client, id);
                }

                public class PVEIdItem {

                    private final PveClient _client;
                    private final Object _id;

                    protected PVEIdItem(PveClient client, Object id) {
                        _client = client;
                        _id = id;
                    }

                    /**
                     * Remove Metric server.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/metrics/server/" + _id + "", null);
                    }

                    /**
                     * Read metric server configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/metrics/server/" + _id + "", null);
                    }

                    /**
                     * Create a new external metric server config
                     *
                     * @param port server network port
                     * @param server server dns name or IP address
                     * @param type Plugin type. Enum: graphite,influxdb
                     * @param api_path_prefix An API path prefix inserted
                     * between '&amp;lt;host&amp;gt;:&amp;lt;port&amp;gt;/' and
                     * '/api2/'. Can be useful if the InfluxDB service runs
                     * behind a reverse proxy.
                     * @param bucket The InfluxDB bucket/db. Only necessary when
                     * using the http v2 api.
                     * @param disable Flag to disable the plugin.
                     * @param influxdbproto Enum: udp,http,https
                     * @param max_body_size InfluxDB max-body-size in bytes.
                     * Requests are batched up to this size.
                     * @param mtu MTU for metrics transmission over UDP
                     * @param organization The InfluxDB organization. Only
                     * necessary when using the http v2 api. Has no meaning when
                     * using v2 compatibility api.
                     * @param path root graphite path (ex:
                     * proxmox.mycluster.mykey)
                     * @param proto Protocol to send graphite data. TCP or UDP
                     * (default) Enum: udp,tcp
                     * @param timeout graphite TCP socket timeout (default=1)
                     * @param token The InfluxDB access token. Only necessary
                     * when using the http v2 api. If the v2 compatibility api
                     * is used, use 'user:password' instead.
                     * @param verify_certificate Set to 0 to disable certificate
                     * verification for https endpoints.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(int port, String server, String type, String api_path_prefix, String bucket, Boolean disable, String influxdbproto, Integer max_body_size, Integer mtu, String organization, String path, String proto, Integer timeout, String token, Boolean verify_certificate) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("port", port);
                        parameters.put("server", server);
                        parameters.put("type", type);
                        parameters.put("api-path-prefix", api_path_prefix);
                        parameters.put("bucket", bucket);
                        parameters.put("disable", disable);
                        parameters.put("influxdbproto", influxdbproto);
                        parameters.put("max-body-size", max_body_size);
                        parameters.put("mtu", mtu);
                        parameters.put("organization", organization);
                        parameters.put("path", path);
                        parameters.put("proto", proto);
                        parameters.put("timeout", timeout);
                        parameters.put("token", token);
                        parameters.put("verify-certificate", verify_certificate);
                        return _client.create("/cluster/metrics/server/" + _id + "", parameters);
                    }

                    /**
                     * Create a new external metric server config
                     *
                     * @param port server network port
                     * @param server server dns name or IP address
                     * @param type Plugin type. Enum: graphite,influxdb
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(int port, String server, String type) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("port", port);
                        parameters.put("server", server);
                        parameters.put("type", type);
                        return _client.create("/cluster/metrics/server/" + _id + "", parameters);
                    }

                    /**
                     * Update metric server configuration.
                     *
                     * @param port server network port
                     * @param server server dns name or IP address
                     * @param api_path_prefix An API path prefix inserted
                     * between '&amp;lt;host&amp;gt;:&amp;lt;port&amp;gt;/' and
                     * '/api2/'. Can be useful if the InfluxDB service runs
                     * behind a reverse proxy.
                     * @param bucket The InfluxDB bucket/db. Only necessary when
                     * using the http v2 api.
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param disable Flag to disable the plugin.
                     * @param influxdbproto Enum: udp,http,https
                     * @param max_body_size InfluxDB max-body-size in bytes.
                     * Requests are batched up to this size.
                     * @param mtu MTU for metrics transmission over UDP
                     * @param organization The InfluxDB organization. Only
                     * necessary when using the http v2 api. Has no meaning when
                     * using v2 compatibility api.
                     * @param path root graphite path (ex:
                     * proxmox.mycluster.mykey)
                     * @param proto Protocol to send graphite data. TCP or UDP
                     * (default) Enum: udp,tcp
                     * @param timeout graphite TCP socket timeout (default=1)
                     * @param token The InfluxDB access token. Only necessary
                     * when using the http v2 api. If the v2 compatibility api
                     * is used, use 'user:password' instead.
                     * @param verify_certificate Set to 0 to disable certificate
                     * verification for https endpoints.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(int port, String server, String api_path_prefix, String bucket, String delete, String digest, Boolean disable, String influxdbproto, Integer max_body_size, Integer mtu, String organization, String path, String proto, Integer timeout, String token, Boolean verify_certificate) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("port", port);
                        parameters.put("server", server);
                        parameters.put("api-path-prefix", api_path_prefix);
                        parameters.put("bucket", bucket);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("disable", disable);
                        parameters.put("influxdbproto", influxdbproto);
                        parameters.put("max-body-size", max_body_size);
                        parameters.put("mtu", mtu);
                        parameters.put("organization", organization);
                        parameters.put("path", path);
                        parameters.put("proto", proto);
                        parameters.put("timeout", timeout);
                        parameters.put("token", token);
                        parameters.put("verify-certificate", verify_certificate);
                        return _client.set("/cluster/metrics/server/" + _id + "", parameters);
                    }

                    /**
                     * Update metric server configuration.
                     *
                     * @param port server network port
                     * @param server server dns name or IP address
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(int port, String server) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("port", port);
                        parameters.put("server", server);
                        return _client.set("/cluster/metrics/server/" + _id + "", parameters);
                    }

                }

                /**
                 * List configured metric servers.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result serverIndex() throws JSONException {
                    return _client.get("/cluster/metrics/server", null);
                }

            }

            /**
             * Metrics index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/metrics", null);
            }

        }

        public class PVEConfig {

            private final PveClient _client;

            protected PVEConfig(PveClient client) {
                _client = client;

            }

            private PVEApiversion _apiversion;

            public PVEApiversion getApiversion() {
                return _apiversion == null ? (_apiversion = new PVEApiversion(_client)) : _apiversion;
            }
            private PVENodes _nodes;

            public PVENodes getNodes() {
                return _nodes == null ? (_nodes = new PVENodes(_client)) : _nodes;
            }
            private PVEJoin _join;

            public PVEJoin getJoin() {
                return _join == null ? (_join = new PVEJoin(_client)) : _join;
            }
            private PVETotem _totem;

            public PVETotem getTotem() {
                return _totem == null ? (_totem = new PVETotem(_client)) : _totem;
            }
            private PVEQdevice _qdevice;

            public PVEQdevice getQdevice() {
                return _qdevice == null ? (_qdevice = new PVEQdevice(_client)) : _qdevice;
            }

            public class PVEApiversion {

                private final PveClient _client;

                protected PVEApiversion(PveClient client) {
                    _client = client;

                }

                /**
                 * Return the version of the cluster join API available on this
                 * node.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result joinApiVersion() throws JSONException {
                    return _client.get("/cluster/config/apiversion", null);
                }

            }

            public class PVENodes {

                private final PveClient _client;

                protected PVENodes(PveClient client) {
                    _client = client;

                }

                public PVENodeItem get(Object node) {
                    return new PVENodeItem(_client, node);
                }

                public class PVENodeItem {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVENodeItem(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Removes a node from the cluster configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delnode() throws JSONException {
                        return _client.delete("/cluster/config/nodes/" + _node + "", null);
                    }

                    /**
                     * Adds a node to the cluster configuration. This call is
                     * for internal use.
                     *
                     * @param apiversion The JOIN_API_VERSION of the new node.
                     * @param force Do not throw error if node already exists.
                     * @param linkN Address and priority information of a single
                     * corosync link. (up to 8 links supported; link0..link7)
                     * @param new_node_ip IP Address of node to add. Used as
                     * fallback if no links are given.
                     * @param nodeid Node id for this node.
                     * @param votes Number of votes for this node
                     * @return Result
                     * @throws JSONException
                     */

                    public Result addnode(Integer apiversion, Boolean force, Map<Integer, String> linkN, String new_node_ip, Integer nodeid, Integer votes) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("apiversion", apiversion);
                        parameters.put("force", force);
                        parameters.put("new_node_ip", new_node_ip);
                        parameters.put("nodeid", nodeid);
                        parameters.put("votes", votes);
                        addIndexedParameter(parameters, "link", linkN);
                        return _client.create("/cluster/config/nodes/" + _node + "", parameters);
                    }

                    /**
                     * Adds a node to the cluster configuration. This call is
                     * for internal use.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result addnode() throws JSONException {
                        return _client.create("/cluster/config/nodes/" + _node + "", null);
                    }

                }

                /**
                 * Corosync node list.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result nodes() throws JSONException {
                    return _client.get("/cluster/config/nodes", null);
                }

            }

            public class PVEJoin {

                private final PveClient _client;

                protected PVEJoin(PveClient client) {
                    _client = client;

                }

                /**
                 * Get information needed to join this cluster over the
                 * connected node.
                 *
                 * @param node The node for which the joinee gets the nodeinfo.
                 * @return Result
                 * @throws JSONException
                 */
                public Result joinInfo(String node) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("node", node);
                    return _client.get("/cluster/config/join", parameters);
                }

                /**
                 * Get information needed to join this cluster over the
                 * connected node.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result joinInfo() throws JSONException {
                    return _client.get("/cluster/config/join", null);
                }

                /**
                 * Joins this node into an existing cluster. If no links are
                 * given, default to IP resolved by node's hostname on single
                 * link (fallback fails for clusters with multiple links).
                 *
                 * @param fingerprint Certificate SHA 256 fingerprint.
                 * @param hostname Hostname (or IP) of an existing cluster
                 * member.
                 * @param password Superuser (root) password of peer node.
                 * @param force Do not throw error if node already exists.
                 * @param linkN Address and priority information of a single
                 * corosync link. (up to 8 links supported; link0..link7)
                 * @param nodeid Node id for this node.
                 * @param votes Number of votes for this node
                 * @return Result
                 * @throws JSONException
                 */

                public Result join(String fingerprint, String hostname, String password, Boolean force, Map<Integer, String> linkN, Integer nodeid, Integer votes) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("fingerprint", fingerprint);
                    parameters.put("hostname", hostname);
                    parameters.put("password", password);
                    parameters.put("force", force);
                    parameters.put("nodeid", nodeid);
                    parameters.put("votes", votes);
                    addIndexedParameter(parameters, "link", linkN);
                    return _client.create("/cluster/config/join", parameters);
                }

                /**
                 * Joins this node into an existing cluster. If no links are
                 * given, default to IP resolved by node's hostname on single
                 * link (fallback fails for clusters with multiple links).
                 *
                 * @param fingerprint Certificate SHA 256 fingerprint.
                 * @param hostname Hostname (or IP) of an existing cluster
                 * member.
                 * @param password Superuser (root) password of peer node.
                 * @return Result
                 * @throws JSONException
                 */

                public Result join(String fingerprint, String hostname, String password) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("fingerprint", fingerprint);
                    parameters.put("hostname", hostname);
                    parameters.put("password", password);
                    return _client.create("/cluster/config/join", parameters);
                }

            }

            public class PVETotem {

                private final PveClient _client;

                protected PVETotem(PveClient client) {
                    _client = client;

                }

                /**
                 * Get corosync totem protocol settings.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result totem() throws JSONException {
                    return _client.get("/cluster/config/totem", null);
                }

            }

            public class PVEQdevice {

                private final PveClient _client;

                protected PVEQdevice(PveClient client) {
                    _client = client;

                }

                /**
                 * Get QDevice status
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result status() throws JSONException {
                    return _client.get("/cluster/config/qdevice", null);
                }

            }

            /**
             * Directory index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/config", null);
            }

            /**
             * Generate new cluster configuration. If no links given, default to
             * local IP address as link0.
             *
             * @param clustername The name of the cluster.
             * @param linkN Address and priority information of a single
             * corosync link. (up to 8 links supported; link0..link7)
             * @param nodeid Node id for this node.
             * @param votes Number of votes for this node.
             * @return Result
             * @throws JSONException
             */

            public Result create(String clustername, Map<Integer, String> linkN, Integer nodeid, Integer votes) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("clustername", clustername);
                parameters.put("nodeid", nodeid);
                parameters.put("votes", votes);
                addIndexedParameter(parameters, "link", linkN);
                return _client.create("/cluster/config", parameters);
            }

            /**
             * Generate new cluster configuration. If no links given, default to
             * local IP address as link0.
             *
             * @param clustername The name of the cluster.
             * @return Result
             * @throws JSONException
             */

            public Result create(String clustername) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("clustername", clustername);
                return _client.create("/cluster/config", parameters);
            }

        }

        public class PVEFirewall {

            private final PveClient _client;

            protected PVEFirewall(PveClient client) {
                _client = client;

            }

            private PVEGroups _groups;

            public PVEGroups getGroups() {
                return _groups == null ? (_groups = new PVEGroups(_client)) : _groups;
            }
            private PVERules _rules;

            public PVERules getRules() {
                return _rules == null ? (_rules = new PVERules(_client)) : _rules;
            }
            private PVEIpset _ipset;

            public PVEIpset getIpset() {
                return _ipset == null ? (_ipset = new PVEIpset(_client)) : _ipset;
            }
            private PVEAliases _aliases;

            public PVEAliases getAliases() {
                return _aliases == null ? (_aliases = new PVEAliases(_client)) : _aliases;
            }
            private PVEOptions _options;

            public PVEOptions getOptions() {
                return _options == null ? (_options = new PVEOptions(_client)) : _options;
            }
            private PVEMacros _macros;

            public PVEMacros getMacros() {
                return _macros == null ? (_macros = new PVEMacros(_client)) : _macros;
            }
            private PVERefs _refs;

            public PVERefs getRefs() {
                return _refs == null ? (_refs = new PVERefs(_client)) : _refs;
            }

            public class PVEGroups {

                private final PveClient _client;

                protected PVEGroups(PveClient client) {
                    _client = client;

                }

                public PVEGroupItem get(Object group) {
                    return new PVEGroupItem(_client, group);
                }

                public class PVEGroupItem {

                    private final PveClient _client;
                    private final Object _group;

                    protected PVEGroupItem(PveClient client, Object group) {
                        _client = client;
                        _group = group;
                    }

                    public PVEPosItem get(Object pos) {
                        return new PVEPosItem(_client, _group, pos);
                    }

                    public class PVEPosItem {

                        private final PveClient _client;
                        private final Object _group;
                        private final Object _pos;

                        protected PVEPosItem(PveClient client, Object group, Object pos) {
                            _client = client;
                            _group = group;
                            _pos = pos;
                        }

                        /**
                         * Delete rule.
                         *
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result deleteRule(String digest) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("digest", digest);
                            return _client.delete("/cluster/firewall/groups/" + _group + "/" + _pos + "", parameters);
                        }

                        /**
                         * Delete rule.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result deleteRule() throws JSONException {
                            return _client.delete("/cluster/firewall/groups/" + _group + "/" + _pos + "", null);
                        }

                        /**
                         * Get single rule data.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result getRule() throws JSONException {
                            return _client.get("/cluster/firewall/groups/" + _group + "/" + _pos + "", null);
                        }

                        /**
                         * Modify rule data.
                         *
                         * @param action Rule action ('ACCEPT', 'DROP',
                         * 'REJECT') or security group name.
                         * @param comment Descriptive comment.
                         * @param delete A list of settings you want to delete.
                         * @param dest Restrict packet destination address. This
                         * can refer to a single IP address, an IP set
                         * ('+ipsetname') or an IP alias definition. You can
                         * also specify an address range like
                         * '20.34.101.207-201.3.9.99', or a list of IP addresses
                         * and networks (entries are separated by comma). Please
                         * do not mix IPv4 and IPv6 addresses inside such lists.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param dport Restrict TCP/UDP destination port. You
                         * can use service names or simple numbers (0-65535), as
                         * defined in '/etc/services'. Port ranges can be
                         * specified with '\d+:\d+', for example '80:85', and
                         * you can use comma separated list to match several
                         * ports or ranges.
                         * @param enable Flag to enable/disable a rule.
                         * @param icmp_type Specify icmp-type. Only valid if
                         * proto equals 'icmp'.
                         * @param iface Network interface name. You have to use
                         * network configuration key names for VMs and
                         * containers ('net\d+'). Host related rules can use
                         * arbitrary strings.
                         * @param log Log level for firewall rule. Enum:
                         * emerg,alert,crit,err,warning,notice,info,debug,nolog
                         * @param macro Use predefined standard macro.
                         * @param moveto Move rule to new position
                         * &amp;lt;moveto&amp;gt;. Other arguments are ignored.
                         * @param proto IP protocol. You can use protocol names
                         * ('tcp'/'udp') or simple numbers, as defined in
                         * '/etc/protocols'.
                         * @param source Restrict packet source address. This
                         * can refer to a single IP address, an IP set
                         * ('+ipsetname') or an IP alias definition. You can
                         * also specify an address range like
                         * '20.34.101.207-201.3.9.99', or a list of IP addresses
                         * and networks (entries are separated by comma). Please
                         * do not mix IPv4 and IPv6 addresses inside such lists.
                         * @param sport Restrict TCP/UDP source port. You can
                         * use service names or simple numbers (0-65535), as
                         * defined in '/etc/services'. Port ranges can be
                         * specified with '\d+:\d+', for example '80:85', and
                         * you can use comma separated list to match several
                         * ports or ranges.
                         * @param type Rule type. Enum: in,out,group
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateRule(String action, String comment, String delete, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer moveto, String proto, String source, String sport, String type) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("action", action);
                            parameters.put("comment", comment);
                            parameters.put("delete", delete);
                            parameters.put("dest", dest);
                            parameters.put("digest", digest);
                            parameters.put("dport", dport);
                            parameters.put("enable", enable);
                            parameters.put("icmp-type", icmp_type);
                            parameters.put("iface", iface);
                            parameters.put("log", log);
                            parameters.put("macro", macro);
                            parameters.put("moveto", moveto);
                            parameters.put("proto", proto);
                            parameters.put("source", source);
                            parameters.put("sport", sport);
                            parameters.put("type", type);
                            return _client.set("/cluster/firewall/groups/" + _group + "/" + _pos + "", parameters);
                        }

                        /**
                         * Modify rule data.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateRule() throws JSONException {
                            return _client.set("/cluster/firewall/groups/" + _group + "/" + _pos + "", null);
                        }

                    }

                    /**
                     * Delete security group.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deleteSecurityGroup() throws JSONException {
                        return _client.delete("/cluster/firewall/groups/" + _group + "", null);
                    }

                    /**
                     * List rules.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result getRules() throws JSONException {
                        return _client.get("/cluster/firewall/groups/" + _group + "", null);
                    }

                    /**
                     * Create new rule.
                     *
                     * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                     * security group name.
                     * @param type Rule type. Enum: in,out,group
                     * @param comment Descriptive comment.
                     * @param dest Restrict packet destination address. This can
                     * refer to a single IP address, an IP set ('+ipsetname') or
                     * an IP alias definition. You can also specify an address
                     * range like '20.34.101.207-201.3.9.99', or a list of IP
                     * addresses and networks (entries are separated by comma).
                     * Please do not mix IPv4 and IPv6 addresses inside such
                     * lists.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param dport Restrict TCP/UDP destination port. You can
                     * use service names or simple numbers (0-65535), as defined
                     * in '/etc/services'. Port ranges can be specified with
                     * '\d+:\d+', for example '80:85', and you can use comma
                     * separated list to match several ports or ranges.
                     * @param enable Flag to enable/disable a rule.
                     * @param icmp_type Specify icmp-type. Only valid if proto
                     * equals 'icmp'.
                     * @param iface Network interface name. You have to use
                     * network configuration key names for VMs and containers
                     * ('net\d+'). Host related rules can use arbitrary strings.
                     * @param log Log level for firewall rule. Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param macro Use predefined standard macro.
                     * @param pos Update rule at position &amp;lt;pos&amp;gt;.
                     * @param proto IP protocol. You can use protocol names
                     * ('tcp'/'udp') or simple numbers, as defined in
                     * '/etc/protocols'.
                     * @param source Restrict packet source address. This can
                     * refer to a single IP address, an IP set ('+ipsetname') or
                     * an IP alias definition. You can also specify an address
                     * range like '20.34.101.207-201.3.9.99', or a list of IP
                     * addresses and networks (entries are separated by comma).
                     * Please do not mix IPv4 and IPv6 addresses inside such
                     * lists.
                     * @param sport Restrict TCP/UDP source port. You can use
                     * service names or simple numbers (0-65535), as defined in
                     * '/etc/services'. Port ranges can be specified with
                     * '\d+:\d+', for example '80:85', and you can use comma
                     * separated list to match several ports or ranges.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createRule(String action, String type, String comment, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer pos, String proto, String source, String sport) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("action", action);
                        parameters.put("type", type);
                        parameters.put("comment", comment);
                        parameters.put("dest", dest);
                        parameters.put("digest", digest);
                        parameters.put("dport", dport);
                        parameters.put("enable", enable);
                        parameters.put("icmp-type", icmp_type);
                        parameters.put("iface", iface);
                        parameters.put("log", log);
                        parameters.put("macro", macro);
                        parameters.put("pos", pos);
                        parameters.put("proto", proto);
                        parameters.put("source", source);
                        parameters.put("sport", sport);
                        return _client.create("/cluster/firewall/groups/" + _group + "", parameters);
                    }

                    /**
                     * Create new rule.
                     *
                     * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                     * security group name.
                     * @param type Rule type. Enum: in,out,group
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createRule(String action, String type) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("action", action);
                        parameters.put("type", type);
                        return _client.create("/cluster/firewall/groups/" + _group + "", parameters);
                    }

                }

                /**
                 * List security groups.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result listSecurityGroups() throws JSONException {
                    return _client.get("/cluster/firewall/groups", null);
                }

                /**
                 * Create new security group.
                 *
                 * @param group Security Group name.
                 * @param comment
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param rename Rename/update an existing security group. You
                 * can set 'rename' to the same value as 'name' to update the
                 * 'comment' of an existing group.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createSecurityGroup(String group, String comment, String digest, String rename) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("group", group);
                    parameters.put("comment", comment);
                    parameters.put("digest", digest);
                    parameters.put("rename", rename);
                    return _client.create("/cluster/firewall/groups", parameters);
                }

                /**
                 * Create new security group.
                 *
                 * @param group Security Group name.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createSecurityGroup(String group) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("group", group);
                    return _client.create("/cluster/firewall/groups", parameters);
                }

            }

            public class PVERules {

                private final PveClient _client;

                protected PVERules(PveClient client) {
                    _client = client;

                }

                public PVEPosItem get(Object pos) {
                    return new PVEPosItem(_client, pos);
                }

                public class PVEPosItem {

                    private final PveClient _client;
                    private final Object _pos;

                    protected PVEPosItem(PveClient client, Object pos) {
                        _client = client;
                        _pos = pos;
                    }

                    /**
                     * Delete rule.
                     *
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deleteRule(String digest) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("digest", digest);
                        return _client.delete("/cluster/firewall/rules/" + _pos + "", parameters);
                    }

                    /**
                     * Delete rule.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result deleteRule() throws JSONException {
                        return _client.delete("/cluster/firewall/rules/" + _pos + "", null);
                    }

                    /**
                     * Get single rule data.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result getRule() throws JSONException {
                        return _client.get("/cluster/firewall/rules/" + _pos + "", null);
                    }

                    /**
                     * Modify rule data.
                     *
                     * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                     * security group name.
                     * @param comment Descriptive comment.
                     * @param delete A list of settings you want to delete.
                     * @param dest Restrict packet destination address. This can
                     * refer to a single IP address, an IP set ('+ipsetname') or
                     * an IP alias definition. You can also specify an address
                     * range like '20.34.101.207-201.3.9.99', or a list of IP
                     * addresses and networks (entries are separated by comma).
                     * Please do not mix IPv4 and IPv6 addresses inside such
                     * lists.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param dport Restrict TCP/UDP destination port. You can
                     * use service names or simple numbers (0-65535), as defined
                     * in '/etc/services'. Port ranges can be specified with
                     * '\d+:\d+', for example '80:85', and you can use comma
                     * separated list to match several ports or ranges.
                     * @param enable Flag to enable/disable a rule.
                     * @param icmp_type Specify icmp-type. Only valid if proto
                     * equals 'icmp'.
                     * @param iface Network interface name. You have to use
                     * network configuration key names for VMs and containers
                     * ('net\d+'). Host related rules can use arbitrary strings.
                     * @param log Log level for firewall rule. Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param macro Use predefined standard macro.
                     * @param moveto Move rule to new position
                     * &amp;lt;moveto&amp;gt;. Other arguments are ignored.
                     * @param proto IP protocol. You can use protocol names
                     * ('tcp'/'udp') or simple numbers, as defined in
                     * '/etc/protocols'.
                     * @param source Restrict packet source address. This can
                     * refer to a single IP address, an IP set ('+ipsetname') or
                     * an IP alias definition. You can also specify an address
                     * range like '20.34.101.207-201.3.9.99', or a list of IP
                     * addresses and networks (entries are separated by comma).
                     * Please do not mix IPv4 and IPv6 addresses inside such
                     * lists.
                     * @param sport Restrict TCP/UDP source port. You can use
                     * service names or simple numbers (0-65535), as defined in
                     * '/etc/services'. Port ranges can be specified with
                     * '\d+:\d+', for example '80:85', and you can use comma
                     * separated list to match several ports or ranges.
                     * @param type Rule type. Enum: in,out,group
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateRule(String action, String comment, String delete, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer moveto, String proto, String source, String sport, String type) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("action", action);
                        parameters.put("comment", comment);
                        parameters.put("delete", delete);
                        parameters.put("dest", dest);
                        parameters.put("digest", digest);
                        parameters.put("dport", dport);
                        parameters.put("enable", enable);
                        parameters.put("icmp-type", icmp_type);
                        parameters.put("iface", iface);
                        parameters.put("log", log);
                        parameters.put("macro", macro);
                        parameters.put("moveto", moveto);
                        parameters.put("proto", proto);
                        parameters.put("source", source);
                        parameters.put("sport", sport);
                        parameters.put("type", type);
                        return _client.set("/cluster/firewall/rules/" + _pos + "", parameters);
                    }

                    /**
                     * Modify rule data.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateRule() throws JSONException {
                        return _client.set("/cluster/firewall/rules/" + _pos + "", null);
                    }

                }

                /**
                 * List rules.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getRules() throws JSONException {
                    return _client.get("/cluster/firewall/rules", null);
                }

                /**
                 * Create new rule.
                 *
                 * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                 * security group name.
                 * @param type Rule type. Enum: in,out,group
                 * @param comment Descriptive comment.
                 * @param dest Restrict packet destination address. This can
                 * refer to a single IP address, an IP set ('+ipsetname') or an
                 * IP alias definition. You can also specify an address range
                 * like '20.34.101.207-201.3.9.99', or a list of IP addresses
                 * and networks (entries are separated by comma). Please do not
                 * mix IPv4 and IPv6 addresses inside such lists.
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param dport Restrict TCP/UDP destination port. You can use
                 * service names or simple numbers (0-65535), as defined in
                 * '/etc/services'. Port ranges can be specified with '\d+:\d+',
                 * for example '80:85', and you can use comma separated list to
                 * match several ports or ranges.
                 * @param enable Flag to enable/disable a rule.
                 * @param icmp_type Specify icmp-type. Only valid if proto
                 * equals 'icmp'.
                 * @param iface Network interface name. You have to use network
                 * configuration key names for VMs and containers ('net\d+').
                 * Host related rules can use arbitrary strings.
                 * @param log Log level for firewall rule. Enum:
                 * emerg,alert,crit,err,warning,notice,info,debug,nolog
                 * @param macro Use predefined standard macro.
                 * @param pos Update rule at position &amp;lt;pos&amp;gt;.
                 * @param proto IP protocol. You can use protocol names
                 * ('tcp'/'udp') or simple numbers, as defined in
                 * '/etc/protocols'.
                 * @param source Restrict packet source address. This can refer
                 * to a single IP address, an IP set ('+ipsetname') or an IP
                 * alias definition. You can also specify an address range like
                 * '20.34.101.207-201.3.9.99', or a list of IP addresses and
                 * networks (entries are separated by comma). Please do not mix
                 * IPv4 and IPv6 addresses inside such lists.
                 * @param sport Restrict TCP/UDP source port. You can use
                 * service names or simple numbers (0-65535), as defined in
                 * '/etc/services'. Port ranges can be specified with '\d+:\d+',
                 * for example '80:85', and you can use comma separated list to
                 * match several ports or ranges.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createRule(String action, String type, String comment, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer pos, String proto, String source, String sport) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("action", action);
                    parameters.put("type", type);
                    parameters.put("comment", comment);
                    parameters.put("dest", dest);
                    parameters.put("digest", digest);
                    parameters.put("dport", dport);
                    parameters.put("enable", enable);
                    parameters.put("icmp-type", icmp_type);
                    parameters.put("iface", iface);
                    parameters.put("log", log);
                    parameters.put("macro", macro);
                    parameters.put("pos", pos);
                    parameters.put("proto", proto);
                    parameters.put("source", source);
                    parameters.put("sport", sport);
                    return _client.create("/cluster/firewall/rules", parameters);
                }

                /**
                 * Create new rule.
                 *
                 * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                 * security group name.
                 * @param type Rule type. Enum: in,out,group
                 * @return Result
                 * @throws JSONException
                 */

                public Result createRule(String action, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("action", action);
                    parameters.put("type", type);
                    return _client.create("/cluster/firewall/rules", parameters);
                }

            }

            public class PVEIpset {

                private final PveClient _client;

                protected PVEIpset(PveClient client) {
                    _client = client;

                }

                public PVENameItem get(Object name) {
                    return new PVENameItem(_client, name);
                }

                public class PVENameItem {

                    private final PveClient _client;
                    private final Object _name;

                    protected PVENameItem(PveClient client, Object name) {
                        _client = client;
                        _name = name;
                    }

                    public PVECidrItem get(Object cidr) {
                        return new PVECidrItem(_client, _name, cidr);
                    }

                    public class PVECidrItem {

                        private final PveClient _client;
                        private final Object _name;
                        private final Object _cidr;

                        protected PVECidrItem(PveClient client, Object name, Object cidr) {
                            _client = client;
                            _name = name;
                            _cidr = cidr;
                        }

                        /**
                         * Remove IP or Network from IPSet.
                         *
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result removeIp(String digest) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("digest", digest);
                            return _client.delete("/cluster/firewall/ipset/" + _name + "/" + _cidr + "", parameters);
                        }

                        /**
                         * Remove IP or Network from IPSet.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result removeIp() throws JSONException {
                            return _client.delete("/cluster/firewall/ipset/" + _name + "/" + _cidr + "", null);
                        }

                        /**
                         * Read IP or Network settings from IPSet.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result readIp() throws JSONException {
                            return _client.get("/cluster/firewall/ipset/" + _name + "/" + _cidr + "", null);
                        }

                        /**
                         * Update IP or Network settings
                         *
                         * @param comment
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param nomatch
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateIp(String comment, String digest, Boolean nomatch) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("comment", comment);
                            parameters.put("digest", digest);
                            parameters.put("nomatch", nomatch);
                            return _client.set("/cluster/firewall/ipset/" + _name + "/" + _cidr + "", parameters);
                        }

                        /**
                         * Update IP or Network settings
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateIp() throws JSONException {
                            return _client.set("/cluster/firewall/ipset/" + _name + "/" + _cidr + "", null);
                        }

                    }

                    /**
                     * Delete IPSet
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deleteIpset() throws JSONException {
                        return _client.delete("/cluster/firewall/ipset/" + _name + "", null);
                    }

                    /**
                     * List IPSet content
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result getIpset() throws JSONException {
                        return _client.get("/cluster/firewall/ipset/" + _name + "", null);
                    }

                    /**
                     * Add IP or Network to IPSet.
                     *
                     * @param cidr Network/IP specification in CIDR format.
                     * @param comment
                     * @param nomatch
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createIp(String cidr, String comment, Boolean nomatch) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("cidr", cidr);
                        parameters.put("comment", comment);
                        parameters.put("nomatch", nomatch);
                        return _client.create("/cluster/firewall/ipset/" + _name + "", parameters);
                    }

                    /**
                     * Add IP or Network to IPSet.
                     *
                     * @param cidr Network/IP specification in CIDR format.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createIp(String cidr) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("cidr", cidr);
                        return _client.create("/cluster/firewall/ipset/" + _name + "", parameters);
                    }

                }

                /**
                 * List IPSets
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result ipsetIndex() throws JSONException {
                    return _client.get("/cluster/firewall/ipset", null);
                }

                /**
                 * Create new IPSet
                 *
                 * @param name IP set name.
                 * @param comment
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param rename Rename an existing IPSet. You can set 'rename'
                 * to the same value as 'name' to update the 'comment' of an
                 * existing IPSet.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createIpset(String name, String comment, String digest, String rename) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("name", name);
                    parameters.put("comment", comment);
                    parameters.put("digest", digest);
                    parameters.put("rename", rename);
                    return _client.create("/cluster/firewall/ipset", parameters);
                }

                /**
                 * Create new IPSet
                 *
                 * @param name IP set name.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createIpset(String name) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("name", name);
                    return _client.create("/cluster/firewall/ipset", parameters);
                }

            }

            public class PVEAliases {

                private final PveClient _client;

                protected PVEAliases(PveClient client) {
                    _client = client;

                }

                public PVENameItem get(Object name) {
                    return new PVENameItem(_client, name);
                }

                public class PVENameItem {

                    private final PveClient _client;
                    private final Object _name;

                    protected PVENameItem(PveClient client, Object name) {
                        _client = client;
                        _name = name;
                    }

                    /**
                     * Remove IP or Network alias.
                     *
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result removeAlias(String digest) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("digest", digest);
                        return _client.delete("/cluster/firewall/aliases/" + _name + "", parameters);
                    }

                    /**
                     * Remove IP or Network alias.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result removeAlias() throws JSONException {
                        return _client.delete("/cluster/firewall/aliases/" + _name + "", null);
                    }

                    /**
                     * Read alias.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result readAlias() throws JSONException {
                        return _client.get("/cluster/firewall/aliases/" + _name + "", null);
                    }

                    /**
                     * Update IP or Network alias.
                     *
                     * @param cidr Network/IP specification in CIDR format.
                     * @param comment
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param rename Rename an existing alias.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateAlias(String cidr, String comment, String digest, String rename) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("cidr", cidr);
                        parameters.put("comment", comment);
                        parameters.put("digest", digest);
                        parameters.put("rename", rename);
                        return _client.set("/cluster/firewall/aliases/" + _name + "", parameters);
                    }

                    /**
                     * Update IP or Network alias.
                     *
                     * @param cidr Network/IP specification in CIDR format.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateAlias(String cidr) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("cidr", cidr);
                        return _client.set("/cluster/firewall/aliases/" + _name + "", parameters);
                    }

                }

                /**
                 * List aliases
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getAliases() throws JSONException {
                    return _client.get("/cluster/firewall/aliases", null);
                }

                /**
                 * Create IP or Network Alias.
                 *
                 * @param cidr Network/IP specification in CIDR format.
                 * @param name Alias name.
                 * @param comment
                 * @return Result
                 * @throws JSONException
                 */

                public Result createAlias(String cidr, String name, String comment) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("cidr", cidr);
                    parameters.put("name", name);
                    parameters.put("comment", comment);
                    return _client.create("/cluster/firewall/aliases", parameters);
                }

                /**
                 * Create IP or Network Alias.
                 *
                 * @param cidr Network/IP specification in CIDR format.
                 * @param name Alias name.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createAlias(String cidr, String name) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("cidr", cidr);
                    parameters.put("name", name);
                    return _client.create("/cluster/firewall/aliases", parameters);
                }

            }

            public class PVEOptions {

                private final PveClient _client;

                protected PVEOptions(PveClient client) {
                    _client = client;

                }

                /**
                 * Get Firewall options.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getOptions() throws JSONException {
                    return _client.get("/cluster/firewall/options", null);
                }

                /**
                 * Set Firewall options.
                 *
                 * @param delete A list of settings you want to delete.
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param ebtables Enable ebtables rules cluster wide.
                 * @param enable Enable or disable the firewall cluster wide.
                 * @param log_ratelimit Log ratelimiting settings
                 * @param policy_in Input policy. Enum: ACCEPT,REJECT,DROP
                 * @param policy_out Output policy. Enum: ACCEPT,REJECT,DROP
                 * @return Result
                 * @throws JSONException
                 */

                public Result setOptions(String delete, String digest, Boolean ebtables, Integer enable, String log_ratelimit, String policy_in, String policy_out) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("delete", delete);
                    parameters.put("digest", digest);
                    parameters.put("ebtables", ebtables);
                    parameters.put("enable", enable);
                    parameters.put("log_ratelimit", log_ratelimit);
                    parameters.put("policy_in", policy_in);
                    parameters.put("policy_out", policy_out);
                    return _client.set("/cluster/firewall/options", parameters);
                }

                /**
                 * Set Firewall options.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result setOptions() throws JSONException {
                    return _client.set("/cluster/firewall/options", null);
                }

            }

            public class PVEMacros {

                private final PveClient _client;

                protected PVEMacros(PveClient client) {
                    _client = client;

                }

                /**
                 * List available macros
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getMacros() throws JSONException {
                    return _client.get("/cluster/firewall/macros", null);
                }

            }

            public class PVERefs {

                private final PveClient _client;

                protected PVERefs(PveClient client) {
                    _client = client;

                }

                /**
                 * Lists possible IPSet/Alias reference which are allowed in
                 * source/dest properties.
                 *
                 * @param type Only list references of specified type. Enum:
                 * alias,ipset
                 * @return Result
                 * @throws JSONException
                 */
                public Result refs(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.get("/cluster/firewall/refs", parameters);
                }

                /**
                 * Lists possible IPSet/Alias reference which are allowed in
                 * source/dest properties.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result refs() throws JSONException {
                    return _client.get("/cluster/firewall/refs", null);
                }

            }

            /**
             * Directory index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/firewall", null);
            }

        }

        public class PVEBackup {

            private final PveClient _client;

            protected PVEBackup(PveClient client) {
                _client = client;

            }

            public PVEIdItem get(Object id) {
                return new PVEIdItem(_client, id);
            }

            public class PVEIdItem {

                private final PveClient _client;
                private final Object _id;

                protected PVEIdItem(PveClient client, Object id) {
                    _client = client;
                    _id = id;
                }

                private PVEIncludedVolumes _includedVolumes;

                public PVEIncludedVolumes getIncludedVolumes() {
                    return _includedVolumes == null ? (_includedVolumes = new PVEIncludedVolumes(_client, _id)) : _includedVolumes;
                }

                public class PVEIncludedVolumes {

                    private final PveClient _client;
                    private final Object _id;

                    protected PVEIncludedVolumes(PveClient client, Object id) {
                        _client = client;
                        _id = id;
                    }

                    /**
                     * Returns included guests and the backup status of their
                     * disks. Optimized to be used in ExtJS tree views.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result getVolumeBackupIncluded() throws JSONException {
                        return _client.get("/cluster/backup/" + _id + "/included_volumes", null);
                    }

                }

                /**
                 * Delete vzdump backup job definition.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result deleteJob() throws JSONException {
                    return _client.delete("/cluster/backup/" + _id + "", null);
                }

                /**
                 * Read vzdump backup job definition.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result readJob() throws JSONException {
                    return _client.get("/cluster/backup/" + _id + "", null);
                }

                /**
                 * Update vzdump backup job definition.
                 *
                 * @param all Backup all known guest systems on this host.
                 * @param bwlimit Limit I/O bandwidth (KBytes per second).
                 * @param comment Description for the Job.
                 * @param compress Compress dump file. Enum: 0,1,gzip,lzo,zstd
                 * @param delete A list of settings you want to delete.
                 * @param dow Day of week selection.
                 * @param dumpdir Store resulting files to specified directory.
                 * @param enabled Enable or disable the job.
                 * @param exclude Exclude specified guest systems (assumes
                 * --all)
                 * @param exclude_path Exclude certain files/directories (shell
                 * globs). Paths starting with '/' are anchored to the
                 * container's root, other paths match relative to each
                 * subdirectory.
                 * @param ionice Set CFQ ionice priority.
                 * @param lockwait Maximal time to wait for the global lock
                 * (minutes).
                 * @param mailnotification Specify when to send an email Enum:
                 * always,failure
                 * @param mailto Comma-separated list of email addresses or
                 * users that should receive email notifications.
                 * @param maxfiles Deprecated: use 'prune-backups' instead.
                 * Maximal number of backup files per guest system.
                 * @param mode Backup mode. Enum: snapshot,suspend,stop
                 * @param node Only run if executed on this node.
                 * @param pigz Use pigz instead of gzip when N&amp;gt;0. N=1
                 * uses half of cores, N&amp;gt;1 uses N as thread count.
                 * @param pool Backup all known guest systems included in the
                 * specified pool.
                 * @param prune_backups Use these retention options instead of
                 * those from the storage configuration.
                 * @param quiet Be quiet.
                 * @param remove Prune older backups according to
                 * 'prune-backups'.
                 * @param schedule Backup schedule. The format is a subset of
                 * `systemd` calendar events.
                 * @param script Use specified hook script.
                 * @param starttime Job Start time.
                 * @param stdexcludes Exclude temporary files and logs.
                 * @param stop Stop running backup jobs on this host.
                 * @param stopwait Maximal time to wait until a guest system is
                 * stopped (minutes).
                 * @param storage Store resulting file to this storage.
                 * @param tmpdir Store temporary files to specified directory.
                 * @param vmid The ID of the guest system you want to backup.
                 * @param zstd Zstd threads. N=0 uses half of the available
                 * cores, N&amp;gt;0 uses N as thread count.
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateJob(Boolean all, Integer bwlimit, String comment, String compress, String delete, String dow, String dumpdir, Boolean enabled, String exclude, String exclude_path, Integer ionice, Integer lockwait, String mailnotification, String mailto, Integer maxfiles, String mode, String node, Integer pigz, String pool, String prune_backups, Boolean quiet, Boolean remove, String schedule, String script, String starttime, Boolean stdexcludes, Boolean stop, Integer stopwait, String storage, String tmpdir, String vmid, Integer zstd) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("all", all);
                    parameters.put("bwlimit", bwlimit);
                    parameters.put("comment", comment);
                    parameters.put("compress", compress);
                    parameters.put("delete", delete);
                    parameters.put("dow", dow);
                    parameters.put("dumpdir", dumpdir);
                    parameters.put("enabled", enabled);
                    parameters.put("exclude", exclude);
                    parameters.put("exclude-path", exclude_path);
                    parameters.put("ionice", ionice);
                    parameters.put("lockwait", lockwait);
                    parameters.put("mailnotification", mailnotification);
                    parameters.put("mailto", mailto);
                    parameters.put("maxfiles", maxfiles);
                    parameters.put("mode", mode);
                    parameters.put("node", node);
                    parameters.put("pigz", pigz);
                    parameters.put("pool", pool);
                    parameters.put("prune-backups", prune_backups);
                    parameters.put("quiet", quiet);
                    parameters.put("remove", remove);
                    parameters.put("schedule", schedule);
                    parameters.put("script", script);
                    parameters.put("starttime", starttime);
                    parameters.put("stdexcludes", stdexcludes);
                    parameters.put("stop", stop);
                    parameters.put("stopwait", stopwait);
                    parameters.put("storage", storage);
                    parameters.put("tmpdir", tmpdir);
                    parameters.put("vmid", vmid);
                    parameters.put("zstd", zstd);
                    return _client.set("/cluster/backup/" + _id + "", parameters);
                }

                /**
                 * Update vzdump backup job definition.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateJob() throws JSONException {
                    return _client.set("/cluster/backup/" + _id + "", null);
                }

            }

            /**
             * List vzdump backup schedule.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/backup", null);
            }

            /**
             * Create new vzdump backup job.
             *
             * @param all Backup all known guest systems on this host.
             * @param bwlimit Limit I/O bandwidth (KBytes per second).
             * @param comment Description for the Job.
             * @param compress Compress dump file. Enum: 0,1,gzip,lzo,zstd
             * @param dow Day of week selection.
             * @param dumpdir Store resulting files to specified directory.
             * @param enabled Enable or disable the job.
             * @param exclude Exclude specified guest systems (assumes --all)
             * @param exclude_path Exclude certain files/directories (shell
             * globs). Paths starting with '/' are anchored to the container's
             * root, other paths match relative to each subdirectory.
             * @param id Job ID (will be autogenerated).
             * @param ionice Set CFQ ionice priority.
             * @param lockwait Maximal time to wait for the global lock
             * (minutes).
             * @param mailnotification Specify when to send an email Enum:
             * always,failure
             * @param mailto Comma-separated list of email addresses or users
             * that should receive email notifications.
             * @param maxfiles Deprecated: use 'prune-backups' instead. Maximal
             * number of backup files per guest system.
             * @param mode Backup mode. Enum: snapshot,suspend,stop
             * @param node Only run if executed on this node.
             * @param pigz Use pigz instead of gzip when N&amp;gt;0. N=1 uses
             * half of cores, N&amp;gt;1 uses N as thread count.
             * @param pool Backup all known guest systems included in the
             * specified pool.
             * @param prune_backups Use these retention options instead of those
             * from the storage configuration.
             * @param quiet Be quiet.
             * @param remove Prune older backups according to 'prune-backups'.
             * @param schedule Backup schedule. The format is a subset of
             * `systemd` calendar events.
             * @param script Use specified hook script.
             * @param starttime Job Start time.
             * @param stdexcludes Exclude temporary files and logs.
             * @param stop Stop running backup jobs on this host.
             * @param stopwait Maximal time to wait until a guest system is
             * stopped (minutes).
             * @param storage Store resulting file to this storage.
             * @param tmpdir Store temporary files to specified directory.
             * @param vmid The ID of the guest system you want to backup.
             * @param zstd Zstd threads. N=0 uses half of the available cores,
             * N&amp;gt;0 uses N as thread count.
             * @return Result
             * @throws JSONException
             */

            public Result createJob(Boolean all, Integer bwlimit, String comment, String compress, String dow, String dumpdir, Boolean enabled, String exclude, String exclude_path, String id, Integer ionice, Integer lockwait, String mailnotification, String mailto, Integer maxfiles, String mode, String node, Integer pigz, String pool, String prune_backups, Boolean quiet, Boolean remove, String schedule, String script, String starttime, Boolean stdexcludes, Boolean stop, Integer stopwait, String storage, String tmpdir, String vmid, Integer zstd) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("all", all);
                parameters.put("bwlimit", bwlimit);
                parameters.put("comment", comment);
                parameters.put("compress", compress);
                parameters.put("dow", dow);
                parameters.put("dumpdir", dumpdir);
                parameters.put("enabled", enabled);
                parameters.put("exclude", exclude);
                parameters.put("exclude-path", exclude_path);
                parameters.put("id", id);
                parameters.put("ionice", ionice);
                parameters.put("lockwait", lockwait);
                parameters.put("mailnotification", mailnotification);
                parameters.put("mailto", mailto);
                parameters.put("maxfiles", maxfiles);
                parameters.put("mode", mode);
                parameters.put("node", node);
                parameters.put("pigz", pigz);
                parameters.put("pool", pool);
                parameters.put("prune-backups", prune_backups);
                parameters.put("quiet", quiet);
                parameters.put("remove", remove);
                parameters.put("schedule", schedule);
                parameters.put("script", script);
                parameters.put("starttime", starttime);
                parameters.put("stdexcludes", stdexcludes);
                parameters.put("stop", stop);
                parameters.put("stopwait", stopwait);
                parameters.put("storage", storage);
                parameters.put("tmpdir", tmpdir);
                parameters.put("vmid", vmid);
                parameters.put("zstd", zstd);
                return _client.create("/cluster/backup", parameters);
            }

            /**
             * Create new vzdump backup job.
             *
             * @return Result
             * @throws JSONException
             */

            public Result createJob() throws JSONException {
                return _client.create("/cluster/backup", null);
            }

        }

        public class PVEBackupInfo {

            private final PveClient _client;

            protected PVEBackupInfo(PveClient client) {
                _client = client;

            }

            private PVENotBackedUp _notBackedUp;

            public PVENotBackedUp getNotBackedUp() {
                return _notBackedUp == null ? (_notBackedUp = new PVENotBackedUp(_client)) : _notBackedUp;
            }

            public class PVENotBackedUp {

                private final PveClient _client;

                protected PVENotBackedUp(PveClient client) {
                    _client = client;

                }

                /**
                 * Shows all guests which are not covered by any backup job.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getGuestsNotInBackup() throws JSONException {
                    return _client.get("/cluster/backup-info/not-backed-up", null);
                }

            }

            /**
             * Index for backup info related endpoints
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/backup-info", null);
            }

        }

        public class PVEHa {

            private final PveClient _client;

            protected PVEHa(PveClient client) {
                _client = client;

            }

            private PVEResources _resources;

            public PVEResources getResources() {
                return _resources == null ? (_resources = new PVEResources(_client)) : _resources;
            }
            private PVEGroups _groups;

            public PVEGroups getGroups() {
                return _groups == null ? (_groups = new PVEGroups(_client)) : _groups;
            }
            private PVEStatus _status;

            public PVEStatus getStatus() {
                return _status == null ? (_status = new PVEStatus(_client)) : _status;
            }

            public class PVEResources {

                private final PveClient _client;

                protected PVEResources(PveClient client) {
                    _client = client;

                }

                public PVESidItem get(Object sid) {
                    return new PVESidItem(_client, sid);
                }

                public class PVESidItem {

                    private final PveClient _client;
                    private final Object _sid;

                    protected PVESidItem(PveClient client, Object sid) {
                        _client = client;
                        _sid = sid;
                    }

                    private PVEMigrate _migrate;

                    public PVEMigrate getMigrate() {
                        return _migrate == null ? (_migrate = new PVEMigrate(_client, _sid)) : _migrate;
                    }
                    private PVERelocate _relocate;

                    public PVERelocate getRelocate() {
                        return _relocate == null ? (_relocate = new PVERelocate(_client, _sid)) : _relocate;
                    }

                    public class PVEMigrate {

                        private final PveClient _client;
                        private final Object _sid;

                        protected PVEMigrate(PveClient client, Object sid) {
                            _client = client;
                            _sid = sid;
                        }

                        /**
                         * Request resource migration (online) to another node.
                         *
                         * @param node Target node.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result migrate(String node) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("node", node);
                            return _client.create("/cluster/ha/resources/" + _sid + "/migrate", parameters);
                        }

                    }

                    public class PVERelocate {

                        private final PveClient _client;
                        private final Object _sid;

                        protected PVERelocate(PveClient client, Object sid) {
                            _client = client;
                            _sid = sid;
                        }

                        /**
                         * Request resource relocatzion to another node. This
                         * stops the service on the old node, and restarts it on
                         * the target node.
                         *
                         * @param node Target node.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result relocate(String node) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("node", node);
                            return _client.create("/cluster/ha/resources/" + _sid + "/relocate", parameters);
                        }

                    }

                    /**
                     * Delete resource configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/ha/resources/" + _sid + "", null);
                    }

                    /**
                     * Read resource configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/ha/resources/" + _sid + "", null);
                    }

                    /**
                     * Update resource configuration.
                     *
                     * @param comment Description.
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param group The HA group identifier.
                     * @param max_relocate Maximal number of service relocate
                     * tries when a service failes to start.
                     * @param max_restart Maximal number of tries to restart the
                     * service on a node after its start failed.
                     * @param state Requested resource state. Enum:
                     * started,stopped,enabled,disabled,ignored
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(String comment, String delete, String digest, String group, Integer max_relocate, Integer max_restart, String state) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("comment", comment);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("group", group);
                        parameters.put("max_relocate", max_relocate);
                        parameters.put("max_restart", max_restart);
                        parameters.put("state", state);
                        return _client.set("/cluster/ha/resources/" + _sid + "", parameters);
                    }

                    /**
                     * Update resource configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/ha/resources/" + _sid + "", null);
                    }

                }

                /**
                 * List HA resources.
                 *
                 * @param type Only list resources of specific type Enum: ct,vm
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.get("/cluster/ha/resources", parameters);
                }

                /**
                 * List HA resources.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/ha/resources", null);
                }

                /**
                 * Create a new HA resource.
                 *
                 * @param sid HA resource ID. This consists of a resource type
                 * followed by a resource specific name, separated with colon
                 * (example: vm:100 / ct:100). For virtual machines and
                 * containers, you can simply use the VM or CT id as a shortcut
                 * (example: 100).
                 * @param comment Description.
                 * @param group The HA group identifier.
                 * @param max_relocate Maximal number of service relocate tries
                 * when a service failes to start.
                 * @param max_restart Maximal number of tries to restart the
                 * service on a node after its start failed.
                 * @param state Requested resource state. Enum:
                 * started,stopped,enabled,disabled,ignored
                 * @param type Resource type. Enum: ct,vm
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String sid, String comment, String group, Integer max_relocate, Integer max_restart, String state, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("sid", sid);
                    parameters.put("comment", comment);
                    parameters.put("group", group);
                    parameters.put("max_relocate", max_relocate);
                    parameters.put("max_restart", max_restart);
                    parameters.put("state", state);
                    parameters.put("type", type);
                    return _client.create("/cluster/ha/resources", parameters);
                }

                /**
                 * Create a new HA resource.
                 *
                 * @param sid HA resource ID. This consists of a resource type
                 * followed by a resource specific name, separated with colon
                 * (example: vm:100 / ct:100). For virtual machines and
                 * containers, you can simply use the VM or CT id as a shortcut
                 * (example: 100).
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String sid) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("sid", sid);
                    return _client.create("/cluster/ha/resources", parameters);
                }

            }

            public class PVEGroups {

                private final PveClient _client;

                protected PVEGroups(PveClient client) {
                    _client = client;

                }

                public PVEGroupItem get(Object group) {
                    return new PVEGroupItem(_client, group);
                }

                public class PVEGroupItem {

                    private final PveClient _client;
                    private final Object _group;

                    protected PVEGroupItem(PveClient client, Object group) {
                        _client = client;
                        _group = group;
                    }

                    /**
                     * Delete ha group configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/ha/groups/" + _group + "", null);
                    }

                    /**
                     * Read ha group configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/ha/groups/" + _group + "", null);
                    }

                    /**
                     * Update ha group configuration.
                     *
                     * @param comment Description.
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param nodes List of cluster node names with optional
                     * priority.
                     * @param nofailback The CRM tries to run services on the
                     * node with the highest priority. If a node with higher
                     * priority comes online, the CRM migrates the service to
                     * that node. Enabling nofailback prevents that behavior.
                     * @param restricted Resources bound to restricted groups
                     * may only run on nodes defined by the group.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(String comment, String delete, String digest, String nodes, Boolean nofailback, Boolean restricted) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("comment", comment);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("nodes", nodes);
                        parameters.put("nofailback", nofailback);
                        parameters.put("restricted", restricted);
                        return _client.set("/cluster/ha/groups/" + _group + "", parameters);
                    }

                    /**
                     * Update ha group configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/ha/groups/" + _group + "", null);
                    }

                }

                /**
                 * Get HA groups.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/cluster/ha/groups", null);
                }

                /**
                 * Create a new HA group.
                 *
                 * @param group The HA group identifier.
                 * @param nodes List of cluster node names with optional
                 * priority.
                 * @param comment Description.
                 * @param nofailback The CRM tries to run services on the node
                 * with the highest priority. If a node with higher priority
                 * comes online, the CRM migrates the service to that node.
                 * Enabling nofailback prevents that behavior.
                 * @param restricted Resources bound to restricted groups may
                 * only run on nodes defined by the group.
                 * @param type Group type. Enum: group
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String group, String nodes, String comment, Boolean nofailback, Boolean restricted, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("group", group);
                    parameters.put("nodes", nodes);
                    parameters.put("comment", comment);
                    parameters.put("nofailback", nofailback);
                    parameters.put("restricted", restricted);
                    parameters.put("type", type);
                    return _client.create("/cluster/ha/groups", parameters);
                }

                /**
                 * Create a new HA group.
                 *
                 * @param group The HA group identifier.
                 * @param nodes List of cluster node names with optional
                 * priority.
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String group, String nodes) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("group", group);
                    parameters.put("nodes", nodes);
                    return _client.create("/cluster/ha/groups", parameters);
                }

            }

            public class PVEStatus {

                private final PveClient _client;

                protected PVEStatus(PveClient client) {
                    _client = client;

                }

                private PVECurrent _current;

                public PVECurrent getCurrent() {
                    return _current == null ? (_current = new PVECurrent(_client)) : _current;
                }
                private PVEManagerStatus _managerStatus;

                public PVEManagerStatus getManagerStatus() {
                    return _managerStatus == null ? (_managerStatus = new PVEManagerStatus(_client)) : _managerStatus;
                }

                public class PVECurrent {

                    private final PveClient _client;

                    protected PVECurrent(PveClient client) {
                        _client = client;

                    }

                    /**
                     * Get HA manger status.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result status() throws JSONException {
                        return _client.get("/cluster/ha/status/current", null);
                    }

                }

                public class PVEManagerStatus {

                    private final PveClient _client;

                    protected PVEManagerStatus(PveClient client) {
                        _client = client;

                    }

                    /**
                     * Get full HA manger status, including LRM status.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result managerStatus() throws JSONException {
                        return _client.get("/cluster/ha/status/manager_status", null);
                    }

                }

                /**
                 * Directory index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/cluster/ha/status", null);
                }

            }

            /**
             * Directory index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/ha", null);
            }

        }

        public class PVEAcme {

            private final PveClient _client;

            protected PVEAcme(PveClient client) {
                _client = client;

            }

            private PVEPlugins _plugins;

            public PVEPlugins getPlugins() {
                return _plugins == null ? (_plugins = new PVEPlugins(_client)) : _plugins;
            }
            private PVEAccount _account;

            public PVEAccount getAccount() {
                return _account == null ? (_account = new PVEAccount(_client)) : _account;
            }
            private PVETos _tos;

            public PVETos getTos() {
                return _tos == null ? (_tos = new PVETos(_client)) : _tos;
            }
            private PVEDirectories _directories;

            public PVEDirectories getDirectories() {
                return _directories == null ? (_directories = new PVEDirectories(_client)) : _directories;
            }
            private PVEChallengeSchema _challengeSchema;

            public PVEChallengeSchema getChallengeSchema() {
                return _challengeSchema == null ? (_challengeSchema = new PVEChallengeSchema(_client)) : _challengeSchema;
            }

            public class PVEPlugins {

                private final PveClient _client;

                protected PVEPlugins(PveClient client) {
                    _client = client;

                }

                public PVEIdItem get(Object id) {
                    return new PVEIdItem(_client, id);
                }

                public class PVEIdItem {

                    private final PveClient _client;
                    private final Object _id;

                    protected PVEIdItem(PveClient client, Object id) {
                        _client = client;
                        _id = id;
                    }

                    /**
                     * Delete ACME plugin configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deletePlugin() throws JSONException {
                        return _client.delete("/cluster/acme/plugins/" + _id + "", null);
                    }

                    /**
                     * Get ACME plugin configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result getPluginConfig() throws JSONException {
                        return _client.get("/cluster/acme/plugins/" + _id + "", null);
                    }

                    /**
                     * Update ACME plugin configuration.
                     *
                     * @param api API plugin name Enum:
                     * 1984hosting,acmedns,acmeproxy,active24,ad,ali,anx,arvan,aurora,autodns,aws,azion,azure,cf,clouddns,cloudns,cn,conoha,constellix,cx,cyon,da,ddnss,desec,df,dgon,dnsimple,do,doapi,domeneshop,dp,dpi,dreamhost,duckdns,durabledns,dyn,dynu,dynv6,easydns,edgedns,euserv,exoscale,freedns,gandi_livedns,gcloud,gd,gdnsdk,he,hetzner,hexonet,hostingde,huaweicloud,infoblox,infomaniak,internetbs,inwx,ionos,ispconfig,jd,joker,kappernet,kas,kinghost,knot,leaseweb,lexicon,linode,linode_v4,loopia,lua,maradns,me,miab,misaka,myapi,mydevil,mydnsjp,namecheap,namecom,namesilo,nederhost,neodigit,netcup,netlify,nic,njalla,nm,nsd,nsone,nsupdate,nw,oci,one,online,openprovider,openstack,opnsense,ovh,pdns,pleskxml,pointhq,porkbun,rackcorp,rackspace,rcode0,regru,scaleway,schlundtech,selectel,servercow,simply,tele3,transip,ultra,unoeuro,variomedia,veesp,vscale,vultr,websupport,world4you,yandex,zilore,zone,zonomi
                     * @param data DNS plugin data. (base64 encoded)
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param disable Flag to disable the config.
                     * @param nodes List of cluster node names.
                     * @param validation_delay Extra delay in seconds to wait
                     * before requesting validation. Allows to cope with a long
                     * TTL of DNS records.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updatePlugin(String api, String data, String delete, String digest, Boolean disable, String nodes, Integer validation_delay) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("api", api);
                        parameters.put("data", data);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("disable", disable);
                        parameters.put("nodes", nodes);
                        parameters.put("validation-delay", validation_delay);
                        return _client.set("/cluster/acme/plugins/" + _id + "", parameters);
                    }

                    /**
                     * Update ACME plugin configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updatePlugin() throws JSONException {
                        return _client.set("/cluster/acme/plugins/" + _id + "", null);
                    }

                }

                /**
                 * ACME plugin index.
                 *
                 * @param type Only list ACME plugins of a specific type Enum:
                 * dns,standalone
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.get("/cluster/acme/plugins", parameters);
                }

                /**
                 * ACME plugin index.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/acme/plugins", null);
                }

                /**
                 * Add ACME plugin configuration.
                 *
                 * @param id ACME Plugin ID name
                 * @param type ACME challenge type. Enum: dns,standalone
                 * @param api API plugin name Enum:
                 * 1984hosting,acmedns,acmeproxy,active24,ad,ali,anx,arvan,aurora,autodns,aws,azion,azure,cf,clouddns,cloudns,cn,conoha,constellix,cx,cyon,da,ddnss,desec,df,dgon,dnsimple,do,doapi,domeneshop,dp,dpi,dreamhost,duckdns,durabledns,dyn,dynu,dynv6,easydns,edgedns,euserv,exoscale,freedns,gandi_livedns,gcloud,gd,gdnsdk,he,hetzner,hexonet,hostingde,huaweicloud,infoblox,infomaniak,internetbs,inwx,ionos,ispconfig,jd,joker,kappernet,kas,kinghost,knot,leaseweb,lexicon,linode,linode_v4,loopia,lua,maradns,me,miab,misaka,myapi,mydevil,mydnsjp,namecheap,namecom,namesilo,nederhost,neodigit,netcup,netlify,nic,njalla,nm,nsd,nsone,nsupdate,nw,oci,one,online,openprovider,openstack,opnsense,ovh,pdns,pleskxml,pointhq,porkbun,rackcorp,rackspace,rcode0,regru,scaleway,schlundtech,selectel,servercow,simply,tele3,transip,ultra,unoeuro,variomedia,veesp,vscale,vultr,websupport,world4you,yandex,zilore,zone,zonomi
                 * @param data DNS plugin data. (base64 encoded)
                 * @param disable Flag to disable the config.
                 * @param nodes List of cluster node names.
                 * @param validation_delay Extra delay in seconds to wait before
                 * requesting validation. Allows to cope with a long TTL of DNS
                 * records.
                 * @return Result
                 * @throws JSONException
                 */

                public Result addPlugin(String id, String type, String api, String data, Boolean disable, String nodes, Integer validation_delay) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("id", id);
                    parameters.put("type", type);
                    parameters.put("api", api);
                    parameters.put("data", data);
                    parameters.put("disable", disable);
                    parameters.put("nodes", nodes);
                    parameters.put("validation-delay", validation_delay);
                    return _client.create("/cluster/acme/plugins", parameters);
                }

                /**
                 * Add ACME plugin configuration.
                 *
                 * @param id ACME Plugin ID name
                 * @param type ACME challenge type. Enum: dns,standalone
                 * @return Result
                 * @throws JSONException
                 */

                public Result addPlugin(String id, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("id", id);
                    parameters.put("type", type);
                    return _client.create("/cluster/acme/plugins", parameters);
                }

            }

            public class PVEAccount {

                private final PveClient _client;

                protected PVEAccount(PveClient client) {
                    _client = client;

                }

                public PVENameItem get(Object name) {
                    return new PVENameItem(_client, name);
                }

                public class PVENameItem {

                    private final PveClient _client;
                    private final Object _name;

                    protected PVENameItem(PveClient client, Object name) {
                        _client = client;
                        _name = name;
                    }

                    /**
                     * Deactivate existing ACME account at CA.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deactivateAccount() throws JSONException {
                        return _client.delete("/cluster/acme/account/" + _name + "", null);
                    }

                    /**
                     * Return existing ACME account information.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result getAccount() throws JSONException {
                        return _client.get("/cluster/acme/account/" + _name + "", null);
                    }

                    /**
                     * Update existing ACME account information with CA. Note:
                     * not specifying any new account information triggers a
                     * refresh.
                     *
                     * @param contact Contact email addresses.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateAccount(String contact) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("contact", contact);
                        return _client.set("/cluster/acme/account/" + _name + "", parameters);
                    }

                    /**
                     * Update existing ACME account information with CA. Note:
                     * not specifying any new account information triggers a
                     * refresh.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateAccount() throws JSONException {
                        return _client.set("/cluster/acme/account/" + _name + "", null);
                    }

                }

                /**
                 * ACMEAccount index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result accountIndex() throws JSONException {
                    return _client.get("/cluster/acme/account", null);
                }

                /**
                 * Register a new ACME account with CA.
                 *
                 * @param contact Contact email addresses.
                 * @param directory URL of ACME CA directory endpoint.
                 * @param name ACME account config file name.
                 * @param tos_url URL of CA TermsOfService - setting this
                 * indicates agreement.
                 * @return Result
                 * @throws JSONException
                 */

                public Result registerAccount(String contact, String directory, String name, String tos_url) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("contact", contact);
                    parameters.put("directory", directory);
                    parameters.put("name", name);
                    parameters.put("tos_url", tos_url);
                    return _client.create("/cluster/acme/account", parameters);
                }

                /**
                 * Register a new ACME account with CA.
                 *
                 * @param contact Contact email addresses.
                 * @return Result
                 * @throws JSONException
                 */

                public Result registerAccount(String contact) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("contact", contact);
                    return _client.create("/cluster/acme/account", parameters);
                }

            }

            public class PVETos {

                private final PveClient _client;

                protected PVETos(PveClient client) {
                    _client = client;

                }

                /**
                 * Retrieve ACME TermsOfService URL from CA.
                 *
                 * @param directory URL of ACME CA directory endpoint.
                 * @return Result
                 * @throws JSONException
                 */
                public Result getTos(String directory) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("directory", directory);
                    return _client.get("/cluster/acme/tos", parameters);
                }

                /**
                 * Retrieve ACME TermsOfService URL from CA.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result getTos() throws JSONException {
                    return _client.get("/cluster/acme/tos", null);
                }

            }

            public class PVEDirectories {

                private final PveClient _client;

                protected PVEDirectories(PveClient client) {
                    _client = client;

                }

                /**
                 * Get named known ACME directory endpoints.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getDirectories() throws JSONException {
                    return _client.get("/cluster/acme/directories", null);
                }

            }

            public class PVEChallengeSchema {

                private final PveClient _client;

                protected PVEChallengeSchema(PveClient client) {
                    _client = client;

                }

                /**
                 * Get schema of ACME challenge types.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result challengeschema() throws JSONException {
                    return _client.get("/cluster/acme/challenge-schema", null);
                }

            }

            /**
             * ACMEAccount index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/acme", null);
            }

        }

        public class PVECeph {

            private final PveClient _client;

            protected PVECeph(PveClient client) {
                _client = client;

            }

            private PVEMetadata _metadata;

            public PVEMetadata getMetadata() {
                return _metadata == null ? (_metadata = new PVEMetadata(_client)) : _metadata;
            }
            private PVEStatus _status;

            public PVEStatus getStatus() {
                return _status == null ? (_status = new PVEStatus(_client)) : _status;
            }
            private PVEFlags _flags;

            public PVEFlags getFlags() {
                return _flags == null ? (_flags = new PVEFlags(_client)) : _flags;
            }

            public class PVEMetadata {

                private final PveClient _client;

                protected PVEMetadata(PveClient client) {
                    _client = client;

                }

                /**
                 * Get ceph metadata.
                 *
                 * @param scope Enum: all,versions
                 * @return Result
                 * @throws JSONException
                 */
                public Result metadata(String scope) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("scope", scope);
                    return _client.get("/cluster/ceph/metadata", parameters);
                }

                /**
                 * Get ceph metadata.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result metadata() throws JSONException {
                    return _client.get("/cluster/ceph/metadata", null);
                }

            }

            public class PVEStatus {

                private final PveClient _client;

                protected PVEStatus(PveClient client) {
                    _client = client;

                }

                /**
                 * Get ceph status.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result status() throws JSONException {
                    return _client.get("/cluster/ceph/status", null);
                }

            }

            public class PVEFlags {

                private final PveClient _client;

                protected PVEFlags(PveClient client) {
                    _client = client;

                }

                public PVEFlagItem get(Object flag) {
                    return new PVEFlagItem(_client, flag);
                }

                public class PVEFlagItem {

                    private final PveClient _client;
                    private final Object _flag;

                    protected PVEFlagItem(PveClient client, Object flag) {
                        _client = client;
                        _flag = flag;
                    }

                    /**
                     * Get the status of a specific ceph flag.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result getFlag() throws JSONException {
                        return _client.get("/cluster/ceph/flags/" + _flag + "", null);
                    }

                    /**
                     * Set or clear (unset) a specific ceph flag
                     *
                     * @param value The new value of the flag
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateFlag(boolean value) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("value", value);
                        return _client.set("/cluster/ceph/flags/" + _flag + "", parameters);
                    }

                }

                /**
                 * get the status of all ceph flags
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getAllFlags() throws JSONException {
                    return _client.get("/cluster/ceph/flags", null);
                }

                /**
                 * Set/Unset multiple ceph flags at once.
                 *
                 * @param nobackfill Backfilling of PGs is suspended.
                 * @param nodeep_scrub Deep Scrubbing is disabled.
                 * @param nodown OSD failure reports are being ignored, such
                 * that the monitors will not mark OSDs down.
                 * @param noin OSDs that were previously marked out will not be
                 * marked back in when they start.
                 * @param noout OSDs will not automatically be marked out after
                 * the configured interval.
                 * @param norebalance Rebalancing of PGs is suspended.
                 * @param norecover Recovery of PGs is suspended.
                 * @param noscrub Scrubbing is disabled.
                 * @param notieragent Cache tiering activity is suspended.
                 * @param noup OSDs are not allowed to start.
                 * @param pause Pauses read and writes.
                 * @return Result
                 * @throws JSONException
                 */

                public Result setFlags(Boolean nobackfill, Boolean nodeep_scrub, Boolean nodown, Boolean noin, Boolean noout, Boolean norebalance, Boolean norecover, Boolean noscrub, Boolean notieragent, Boolean noup, Boolean pause) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("nobackfill", nobackfill);
                    parameters.put("nodeep-scrub", nodeep_scrub);
                    parameters.put("nodown", nodown);
                    parameters.put("noin", noin);
                    parameters.put("noout", noout);
                    parameters.put("norebalance", norebalance);
                    parameters.put("norecover", norecover);
                    parameters.put("noscrub", noscrub);
                    parameters.put("notieragent", notieragent);
                    parameters.put("noup", noup);
                    parameters.put("pause", pause);
                    return _client.set("/cluster/ceph/flags", parameters);
                }

                /**
                 * Set/Unset multiple ceph flags at once.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result setFlags() throws JSONException {
                    return _client.set("/cluster/ceph/flags", null);
                }

            }

            /**
             * Cluster ceph index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result cephindex() throws JSONException {
                return _client.get("/cluster/ceph", null);
            }

        }

        public class PVEJobs {

            private final PveClient _client;

            protected PVEJobs(PveClient client) {
                _client = client;

            }

            private PVEScheduleAnalyze _scheduleAnalyze;

            public PVEScheduleAnalyze getScheduleAnalyze() {
                return _scheduleAnalyze == null ? (_scheduleAnalyze = new PVEScheduleAnalyze(_client)) : _scheduleAnalyze;
            }

            public class PVEScheduleAnalyze {

                private final PveClient _client;

                protected PVEScheduleAnalyze(PveClient client) {
                    _client = client;

                }

                /**
                 * Returns a list of future schedule runtimes.
                 *
                 * @param schedule Job schedule. The format is a subset of
                 * `systemd` calendar events.
                 * @param iterations Number of event-iteration to simulate and
                 * return.
                 * @param starttime UNIX timestamp to start the calculation
                 * from. Defaults to the current time.
                 * @return Result
                 * @throws JSONException
                 */
                public Result scheduleAnalyze(String schedule, Integer iterations, Integer starttime) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("schedule", schedule);
                    parameters.put("iterations", iterations);
                    parameters.put("starttime", starttime);
                    return _client.get("/cluster/jobs/schedule-analyze", parameters);
                }

                /**
                 * Returns a list of future schedule runtimes.
                 *
                 * @param schedule Job schedule. The format is a subset of
                 * `systemd` calendar events.
                 * @return Result
                 * @throws JSONException
                 */

                public Result scheduleAnalyze(String schedule) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("schedule", schedule);
                    return _client.get("/cluster/jobs/schedule-analyze", parameters);
                }

            }

            /**
             * Index for jobs related endpoints.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/jobs", null);
            }

        }

        public class PVESdn {

            private final PveClient _client;

            protected PVESdn(PveClient client) {
                _client = client;

            }

            private PVEVnets _vnets;

            public PVEVnets getVnets() {
                return _vnets == null ? (_vnets = new PVEVnets(_client)) : _vnets;
            }
            private PVEZones _zones;

            public PVEZones getZones() {
                return _zones == null ? (_zones = new PVEZones(_client)) : _zones;
            }
            private PVEControllers _controllers;

            public PVEControllers getControllers() {
                return _controllers == null ? (_controllers = new PVEControllers(_client)) : _controllers;
            }
            private PVEIpams _ipams;

            public PVEIpams getIpams() {
                return _ipams == null ? (_ipams = new PVEIpams(_client)) : _ipams;
            }
            private PVEDns _dns;

            public PVEDns getDns() {
                return _dns == null ? (_dns = new PVEDns(_client)) : _dns;
            }

            public class PVEVnets {

                private final PveClient _client;

                protected PVEVnets(PveClient client) {
                    _client = client;

                }

                public PVEVnetItem get(Object vnet) {
                    return new PVEVnetItem(_client, vnet);
                }

                public class PVEVnetItem {

                    private final PveClient _client;
                    private final Object _vnet;

                    protected PVEVnetItem(PveClient client, Object vnet) {
                        _client = client;
                        _vnet = vnet;
                    }

                    private PVESubnets _subnets;

                    public PVESubnets getSubnets() {
                        return _subnets == null ? (_subnets = new PVESubnets(_client, _vnet)) : _subnets;
                    }

                    public class PVESubnets {

                        private final PveClient _client;
                        private final Object _vnet;

                        protected PVESubnets(PveClient client, Object vnet) {
                            _client = client;
                            _vnet = vnet;
                        }

                        public PVESubnetItem get(Object subnet) {
                            return new PVESubnetItem(_client, _vnet, subnet);
                        }

                        public class PVESubnetItem {

                            private final PveClient _client;
                            private final Object _vnet;
                            private final Object _subnet;

                            protected PVESubnetItem(PveClient client, Object vnet, Object subnet) {
                                _client = client;
                                _vnet = vnet;
                                _subnet = subnet;
                            }

                            /**
                             * Delete sdn subnet object configuration.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result delete() throws JSONException {
                                return _client.delete("/cluster/sdn/vnets/" + _vnet + "/subnets/" + _subnet + "", null);
                            }

                            /**
                             * Read sdn subnet configuration.
                             *
                             * @param pending Display pending config.
                             * @param running Display running config.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result read(Boolean pending, Boolean running) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("pending", pending);
                                parameters.put("running", running);
                                return _client.get("/cluster/sdn/vnets/" + _vnet + "/subnets/" + _subnet + "", parameters);
                            }

                            /**
                             * Read sdn subnet configuration.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result read() throws JSONException {
                                return _client.get("/cluster/sdn/vnets/" + _vnet + "/subnets/" + _subnet + "", null);
                            }

                            /**
                             * Update sdn subnet object configuration.
                             *
                             * @param delete A list of settings you want to
                             * delete.
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param dnszoneprefix dns domain zone prefix ex:
                             * 'adm' -&amp;gt;
                             * &amp;lt;hostname&amp;gt;.adm.mydomain.com
                             * @param gateway Subnet Gateway: Will be assign on
                             * vnet for layer3 zones
                             * @param snat enable masquerade for this subnet if
                             * pve-firewall
                             * @return Result
                             * @throws JSONException
                             */

                            public Result update(String delete, String digest, String dnszoneprefix, String gateway, Boolean snat) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("delete", delete);
                                parameters.put("digest", digest);
                                parameters.put("dnszoneprefix", dnszoneprefix);
                                parameters.put("gateway", gateway);
                                parameters.put("snat", snat);
                                return _client.set("/cluster/sdn/vnets/" + _vnet + "/subnets/" + _subnet + "", parameters);
                            }

                            /**
                             * Update sdn subnet object configuration.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result update() throws JSONException {
                                return _client.set("/cluster/sdn/vnets/" + _vnet + "/subnets/" + _subnet + "", null);
                            }

                        }

                        /**
                         * SDN subnets index.
                         *
                         * @param pending Display pending config.
                         * @param running Display running config.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result index(Boolean pending, Boolean running) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("pending", pending);
                            parameters.put("running", running);
                            return _client.get("/cluster/sdn/vnets/" + _vnet + "/subnets", parameters);
                        }

                        /**
                         * SDN subnets index.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result index() throws JSONException {
                            return _client.get("/cluster/sdn/vnets/" + _vnet + "/subnets", null);
                        }

                        /**
                         * Create a new sdn subnet object.
                         *
                         * @param subnet The SDN subnet object identifier.
                         * @param type Enum: subnet
                         * @param dnszoneprefix dns domain zone prefix ex: 'adm'
                         * -&amp;gt; &amp;lt;hostname&amp;gt;.adm.mydomain.com
                         * @param gateway Subnet Gateway: Will be assign on vnet
                         * for layer3 zones
                         * @param snat enable masquerade for this subnet if
                         * pve-firewall
                         * @return Result
                         * @throws JSONException
                         */

                        public Result create(String subnet, String type, String dnszoneprefix, String gateway, Boolean snat) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("subnet", subnet);
                            parameters.put("type", type);
                            parameters.put("dnszoneprefix", dnszoneprefix);
                            parameters.put("gateway", gateway);
                            parameters.put("snat", snat);
                            return _client.create("/cluster/sdn/vnets/" + _vnet + "/subnets", parameters);
                        }

                        /**
                         * Create a new sdn subnet object.
                         *
                         * @param subnet The SDN subnet object identifier.
                         * @param type Enum: subnet
                         * @return Result
                         * @throws JSONException
                         */

                        public Result create(String subnet, String type) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("subnet", subnet);
                            parameters.put("type", type);
                            return _client.create("/cluster/sdn/vnets/" + _vnet + "/subnets", parameters);
                        }

                    }

                    /**
                     * Delete sdn vnet object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/sdn/vnets/" + _vnet + "", null);
                    }

                    /**
                     * Read sdn vnet configuration.
                     *
                     * @param pending Display pending config.
                     * @param running Display running config.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read(Boolean pending, Boolean running) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("pending", pending);
                        parameters.put("running", running);
                        return _client.get("/cluster/sdn/vnets/" + _vnet + "", parameters);
                    }

                    /**
                     * Read sdn vnet configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/sdn/vnets/" + _vnet + "", null);
                    }

                    /**
                     * Update sdn vnet object configuration.
                     *
                     * @param alias alias name of the vnet
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param tag vlan or vxlan id
                     * @param vlanaware Allow vm VLANs to pass through this
                     * vnet.
                     * @param zone zone id
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(String alias, String delete, String digest, Integer tag, Boolean vlanaware, String zone) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("alias", alias);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("tag", tag);
                        parameters.put("vlanaware", vlanaware);
                        parameters.put("zone", zone);
                        return _client.set("/cluster/sdn/vnets/" + _vnet + "", parameters);
                    }

                    /**
                     * Update sdn vnet object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/sdn/vnets/" + _vnet + "", null);
                    }

                }

                /**
                 * SDN vnets index.
                 *
                 * @param pending Display pending config.
                 * @param running Display running config.
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(Boolean pending, Boolean running) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("pending", pending);
                    parameters.put("running", running);
                    return _client.get("/cluster/sdn/vnets", parameters);
                }

                /**
                 * SDN vnets index.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/sdn/vnets", null);
                }

                /**
                 * Create a new sdn vnet object.
                 *
                 * @param vnet The SDN vnet object identifier.
                 * @param zone zone id
                 * @param alias alias name of the vnet
                 * @param tag vlan or vxlan id
                 * @param type Type Enum: vnet
                 * @param vlanaware Allow vm VLANs to pass through this vnet.
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String vnet, String zone, String alias, Integer tag, String type, Boolean vlanaware) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("vnet", vnet);
                    parameters.put("zone", zone);
                    parameters.put("alias", alias);
                    parameters.put("tag", tag);
                    parameters.put("type", type);
                    parameters.put("vlanaware", vlanaware);
                    return _client.create("/cluster/sdn/vnets", parameters);
                }

                /**
                 * Create a new sdn vnet object.
                 *
                 * @param vnet The SDN vnet object identifier.
                 * @param zone zone id
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String vnet, String zone) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("vnet", vnet);
                    parameters.put("zone", zone);
                    return _client.create("/cluster/sdn/vnets", parameters);
                }

            }

            public class PVEZones {

                private final PveClient _client;

                protected PVEZones(PveClient client) {
                    _client = client;

                }

                public PVEZoneItem get(Object zone) {
                    return new PVEZoneItem(_client, zone);
                }

                public class PVEZoneItem {

                    private final PveClient _client;
                    private final Object _zone;

                    protected PVEZoneItem(PveClient client, Object zone) {
                        _client = client;
                        _zone = zone;
                    }

                    /**
                     * Delete sdn zone object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/sdn/zones/" + _zone + "", null);
                    }

                    /**
                     * Read sdn zone configuration.
                     *
                     * @param pending Display pending config.
                     * @param running Display running config.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read(Boolean pending, Boolean running) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("pending", pending);
                        parameters.put("running", running);
                        return _client.get("/cluster/sdn/zones/" + _zone + "", parameters);
                    }

                    /**
                     * Read sdn zone configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/sdn/zones/" + _zone + "", null);
                    }

                    /**
                     * Update sdn zone object configuration.
                     *
                     * @param advertise_subnets Advertise evpn subnets if you
                     * have silent hosts
                     * @param bridge
                     * @param controller Frr router name
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param disable_arp_nd_suppression Disable ipv4 arp
                     * &amp;&amp; ipv6 neighbour discovery suppression
                     * @param dns dns api server
                     * @param dnszone dns domain zone ex: mydomain.com
                     * @param dp_id Faucet dataplane id
                     * @param exitnodes List of cluster node names.
                     * @param exitnodes_local_routing Allow exitnodes to connect
                     * to evpn guests
                     * @param ipam use a specific ipam
                     * @param mac Anycast logical router mac address
                     * @param mtu MTU
                     * @param nodes List of cluster node names.
                     * @param peers peers address list.
                     * @param reversedns reverse dns api server
                     * @param tag Service-VLAN Tag
                     * @param vlan_protocol Enum: 802.1q,802.1ad
                     * @param vrf_vxlan l3vni.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(Boolean advertise_subnets, String bridge, String controller, String delete, String digest, Boolean disable_arp_nd_suppression, String dns, String dnszone, Integer dp_id, String exitnodes, Boolean exitnodes_local_routing, String ipam, String mac, Integer mtu, String nodes, String peers, String reversedns, Integer tag, String vlan_protocol, Integer vrf_vxlan) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("advertise-subnets", advertise_subnets);
                        parameters.put("bridge", bridge);
                        parameters.put("controller", controller);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("disable-arp-nd-suppression", disable_arp_nd_suppression);
                        parameters.put("dns", dns);
                        parameters.put("dnszone", dnszone);
                        parameters.put("dp-id", dp_id);
                        parameters.put("exitnodes", exitnodes);
                        parameters.put("exitnodes-local-routing", exitnodes_local_routing);
                        parameters.put("ipam", ipam);
                        parameters.put("mac", mac);
                        parameters.put("mtu", mtu);
                        parameters.put("nodes", nodes);
                        parameters.put("peers", peers);
                        parameters.put("reversedns", reversedns);
                        parameters.put("tag", tag);
                        parameters.put("vlan-protocol", vlan_protocol);
                        parameters.put("vrf-vxlan", vrf_vxlan);
                        return _client.set("/cluster/sdn/zones/" + _zone + "", parameters);
                    }

                    /**
                     * Update sdn zone object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/sdn/zones/" + _zone + "", null);
                    }

                }

                /**
                 * SDN zones index.
                 *
                 * @param pending Display pending config.
                 * @param running Display running config.
                 * @param type Only list SDN zones of specific type Enum:
                 * evpn,faucet,qinq,simple,vlan,vxlan
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(Boolean pending, Boolean running, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("pending", pending);
                    parameters.put("running", running);
                    parameters.put("type", type);
                    return _client.get("/cluster/sdn/zones", parameters);
                }

                /**
                 * SDN zones index.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/sdn/zones", null);
                }

                /**
                 * Create a new sdn zone object.
                 *
                 * @param type Plugin type. Enum:
                 * evpn,faucet,qinq,simple,vlan,vxlan
                 * @param zone The SDN zone object identifier.
                 * @param advertise_subnets Advertise evpn subnets if you have
                 * silent hosts
                 * @param bridge
                 * @param controller Frr router name
                 * @param disable_arp_nd_suppression Disable ipv4 arp &amp;&amp;
                 * ipv6 neighbour discovery suppression
                 * @param dns dns api server
                 * @param dnszone dns domain zone ex: mydomain.com
                 * @param dp_id Faucet dataplane id
                 * @param exitnodes List of cluster node names.
                 * @param exitnodes_local_routing Allow exitnodes to connect to
                 * evpn guests
                 * @param ipam use a specific ipam
                 * @param mac Anycast logical router mac address
                 * @param mtu MTU
                 * @param nodes List of cluster node names.
                 * @param peers peers address list.
                 * @param reversedns reverse dns api server
                 * @param tag Service-VLAN Tag
                 * @param vlan_protocol Enum: 802.1q,802.1ad
                 * @param vrf_vxlan l3vni.
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String type, String zone, Boolean advertise_subnets, String bridge, String controller, Boolean disable_arp_nd_suppression, String dns, String dnszone, Integer dp_id, String exitnodes, Boolean exitnodes_local_routing, String ipam, String mac, Integer mtu, String nodes, String peers, String reversedns, Integer tag, String vlan_protocol, Integer vrf_vxlan) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    parameters.put("zone", zone);
                    parameters.put("advertise-subnets", advertise_subnets);
                    parameters.put("bridge", bridge);
                    parameters.put("controller", controller);
                    parameters.put("disable-arp-nd-suppression", disable_arp_nd_suppression);
                    parameters.put("dns", dns);
                    parameters.put("dnszone", dnszone);
                    parameters.put("dp-id", dp_id);
                    parameters.put("exitnodes", exitnodes);
                    parameters.put("exitnodes-local-routing", exitnodes_local_routing);
                    parameters.put("ipam", ipam);
                    parameters.put("mac", mac);
                    parameters.put("mtu", mtu);
                    parameters.put("nodes", nodes);
                    parameters.put("peers", peers);
                    parameters.put("reversedns", reversedns);
                    parameters.put("tag", tag);
                    parameters.put("vlan-protocol", vlan_protocol);
                    parameters.put("vrf-vxlan", vrf_vxlan);
                    return _client.create("/cluster/sdn/zones", parameters);
                }

                /**
                 * Create a new sdn zone object.
                 *
                 * @param type Plugin type. Enum:
                 * evpn,faucet,qinq,simple,vlan,vxlan
                 * @param zone The SDN zone object identifier.
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String type, String zone) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    parameters.put("zone", zone);
                    return _client.create("/cluster/sdn/zones", parameters);
                }

            }

            public class PVEControllers {

                private final PveClient _client;

                protected PVEControllers(PveClient client) {
                    _client = client;

                }

                public PVEControllerItem get(Object controller) {
                    return new PVEControllerItem(_client, controller);
                }

                public class PVEControllerItem {

                    private final PveClient _client;
                    private final Object _controller;

                    protected PVEControllerItem(PveClient client, Object controller) {
                        _client = client;
                        _controller = controller;
                    }

                    /**
                     * Delete sdn controller object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/sdn/controllers/" + _controller + "", null);
                    }

                    /**
                     * Read sdn controller configuration.
                     *
                     * @param pending Display pending config.
                     * @param running Display running config.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read(Boolean pending, Boolean running) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("pending", pending);
                        parameters.put("running", running);
                        return _client.get("/cluster/sdn/controllers/" + _controller + "", parameters);
                    }

                    /**
                     * Read sdn controller configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/sdn/controllers/" + _controller + "", null);
                    }

                    /**
                     * Update sdn controller object configuration.
                     *
                     * @param asn autonomous system number
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param ebgp Enable ebgp. (remote-as external)
                     * @param ebgp_multihop
                     * @param loopback source loopback interface.
                     * @param node The cluster node name.
                     * @param peers peers address list.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(Integer asn, String delete, String digest, Boolean ebgp, Integer ebgp_multihop, String loopback, String node, String peers) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("asn", asn);
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("ebgp", ebgp);
                        parameters.put("ebgp-multihop", ebgp_multihop);
                        parameters.put("loopback", loopback);
                        parameters.put("node", node);
                        parameters.put("peers", peers);
                        return _client.set("/cluster/sdn/controllers/" + _controller + "", parameters);
                    }

                    /**
                     * Update sdn controller object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/sdn/controllers/" + _controller + "", null);
                    }

                }

                /**
                 * SDN controllers index.
                 *
                 * @param pending Display pending config.
                 * @param running Display running config.
                 * @param type Only list sdn controllers of specific type Enum:
                 * bgp,evpn,faucet
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(Boolean pending, Boolean running, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("pending", pending);
                    parameters.put("running", running);
                    parameters.put("type", type);
                    return _client.get("/cluster/sdn/controllers", parameters);
                }

                /**
                 * SDN controllers index.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/sdn/controllers", null);
                }

                /**
                 * Create a new sdn controller object.
                 *
                 * @param controller The SDN controller object identifier.
                 * @param type Plugin type. Enum: bgp,evpn,faucet
                 * @param asn autonomous system number
                 * @param ebgp Enable ebgp. (remote-as external)
                 * @param ebgp_multihop
                 * @param loopback source loopback interface.
                 * @param node The cluster node name.
                 * @param peers peers address list.
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String controller, String type, Integer asn, Boolean ebgp, Integer ebgp_multihop, String loopback, String node, String peers) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("controller", controller);
                    parameters.put("type", type);
                    parameters.put("asn", asn);
                    parameters.put("ebgp", ebgp);
                    parameters.put("ebgp-multihop", ebgp_multihop);
                    parameters.put("loopback", loopback);
                    parameters.put("node", node);
                    parameters.put("peers", peers);
                    return _client.create("/cluster/sdn/controllers", parameters);
                }

                /**
                 * Create a new sdn controller object.
                 *
                 * @param controller The SDN controller object identifier.
                 * @param type Plugin type. Enum: bgp,evpn,faucet
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String controller, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("controller", controller);
                    parameters.put("type", type);
                    return _client.create("/cluster/sdn/controllers", parameters);
                }

            }

            public class PVEIpams {

                private final PveClient _client;

                protected PVEIpams(PveClient client) {
                    _client = client;

                }

                public PVEIpamItem get(Object ipam) {
                    return new PVEIpamItem(_client, ipam);
                }

                public class PVEIpamItem {

                    private final PveClient _client;
                    private final Object _ipam;

                    protected PVEIpamItem(PveClient client, Object ipam) {
                        _client = client;
                        _ipam = ipam;
                    }

                    /**
                     * Delete sdn ipam object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/sdn/ipams/" + _ipam + "", null);
                    }

                    /**
                     * Read sdn ipam configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/sdn/ipams/" + _ipam + "", null);
                    }

                    /**
                     * Update sdn ipam object configuration.
                     *
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param section
                     * @param token
                     * @param url
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(String delete, String digest, Integer section, String token, String url) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("section", section);
                        parameters.put("token", token);
                        parameters.put("url", url);
                        return _client.set("/cluster/sdn/ipams/" + _ipam + "", parameters);
                    }

                    /**
                     * Update sdn ipam object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/sdn/ipams/" + _ipam + "", null);
                    }

                }

                /**
                 * SDN ipams index.
                 *
                 * @param type Only list sdn ipams of specific type Enum:
                 * netbox,phpipam,pve
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.get("/cluster/sdn/ipams", parameters);
                }

                /**
                 * SDN ipams index.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/sdn/ipams", null);
                }

                /**
                 * Create a new sdn ipam object.
                 *
                 * @param ipam The SDN ipam object identifier.
                 * @param type Plugin type. Enum: netbox,phpipam,pve
                 * @param section
                 * @param token
                 * @param url
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String ipam, String type, Integer section, String token, String url) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ipam", ipam);
                    parameters.put("type", type);
                    parameters.put("section", section);
                    parameters.put("token", token);
                    parameters.put("url", url);
                    return _client.create("/cluster/sdn/ipams", parameters);
                }

                /**
                 * Create a new sdn ipam object.
                 *
                 * @param ipam The SDN ipam object identifier.
                 * @param type Plugin type. Enum: netbox,phpipam,pve
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String ipam, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ipam", ipam);
                    parameters.put("type", type);
                    return _client.create("/cluster/sdn/ipams", parameters);
                }

            }

            public class PVEDns {

                private final PveClient _client;

                protected PVEDns(PveClient client) {
                    _client = client;

                }

                public PVEDnsItem get(Object dns) {
                    return new PVEDnsItem(_client, dns);
                }

                public class PVEDnsItem {

                    private final PveClient _client;
                    private final Object _dns;

                    protected PVEDnsItem(PveClient client, Object dns) {
                        _client = client;
                        _dns = dns;
                    }

                    /**
                     * Delete sdn dns object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result delete() throws JSONException {
                        return _client.delete("/cluster/sdn/dns/" + _dns + "", null);
                    }

                    /**
                     * Read sdn dns configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result read() throws JSONException {
                        return _client.get("/cluster/sdn/dns/" + _dns + "", null);
                    }

                    /**
                     * Update sdn dns object configuration.
                     *
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param key
                     * @param reversemaskv6
                     * @param ttl
                     * @param url
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update(String delete, String digest, String key, Integer reversemaskv6, Integer ttl, String url) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("key", key);
                        parameters.put("reversemaskv6", reversemaskv6);
                        parameters.put("ttl", ttl);
                        parameters.put("url", url);
                        return _client.set("/cluster/sdn/dns/" + _dns + "", parameters);
                    }

                    /**
                     * Update sdn dns object configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result update() throws JSONException {
                        return _client.set("/cluster/sdn/dns/" + _dns + "", null);
                    }

                }

                /**
                 * SDN dns index.
                 *
                 * @param type Only list sdn dns of specific type Enum: powerdns
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.get("/cluster/sdn/dns", parameters);
                }

                /**
                 * SDN dns index.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/cluster/sdn/dns", null);
                }

                /**
                 * Create a new sdn dns object.
                 *
                 * @param dns The SDN dns object identifier.
                 * @param key
                 * @param type Plugin type. Enum: powerdns
                 * @param url
                 * @param reversemaskv6
                 * @param reversev6mask
                 * @param ttl
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String dns, String key, String type, String url, Integer reversemaskv6, Integer reversev6mask, Integer ttl) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("dns", dns);
                    parameters.put("key", key);
                    parameters.put("type", type);
                    parameters.put("url", url);
                    parameters.put("reversemaskv6", reversemaskv6);
                    parameters.put("reversev6mask", reversev6mask);
                    parameters.put("ttl", ttl);
                    return _client.create("/cluster/sdn/dns", parameters);
                }

                /**
                 * Create a new sdn dns object.
                 *
                 * @param dns The SDN dns object identifier.
                 * @param key
                 * @param type Plugin type. Enum: powerdns
                 * @param url
                 * @return Result
                 * @throws JSONException
                 */

                public Result create(String dns, String key, String type, String url) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("dns", dns);
                    parameters.put("key", key);
                    parameters.put("type", type);
                    parameters.put("url", url);
                    return _client.create("/cluster/sdn/dns", parameters);
                }

            }

            /**
             * Directory index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/cluster/sdn", null);
            }

            /**
             * Apply sdn controller changes &amp;&amp; reload.
             *
             * @return Result
             * @throws JSONException
             */

            public Result reload() throws JSONException {
                return _client.set("/cluster/sdn", null);
            }

        }

        public class PVELog {

            private final PveClient _client;

            protected PVELog(PveClient client) {
                _client = client;

            }

            /**
             * Read cluster log
             *
             * @param max Maximum number of entries.
             * @return Result
             * @throws JSONException
             */
            public Result log(Integer max) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("max", max);
                return _client.get("/cluster/log", parameters);
            }

            /**
             * Read cluster log
             *
             * @return Result
             * @throws JSONException
             */

            public Result log() throws JSONException {
                return _client.get("/cluster/log", null);
            }

        }

        public class PVEResources {

            private final PveClient _client;

            protected PVEResources(PveClient client) {
                _client = client;

            }

            /**
             * Resources index (cluster wide).
             *
             * @param type Enum: vm,storage,node,sdn
             * @return Result
             * @throws JSONException
             */
            public Result resources(String type) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("type", type);
                return _client.get("/cluster/resources", parameters);
            }

            /**
             * Resources index (cluster wide).
             *
             * @return Result
             * @throws JSONException
             */

            public Result resources() throws JSONException {
                return _client.get("/cluster/resources", null);
            }

        }

        public class PVETasks {

            private final PveClient _client;

            protected PVETasks(PveClient client) {
                _client = client;

            }

            /**
             * List recent tasks (cluster wide).
             *
             * @return Result
             * @throws JSONException
             */
            public Result tasks() throws JSONException {
                return _client.get("/cluster/tasks", null);
            }

        }

        public class PVEOptions {

            private final PveClient _client;

            protected PVEOptions(PveClient client) {
                _client = client;

            }

            /**
             * Get datacenter options.
             *
             * @return Result
             * @throws JSONException
             */
            public Result getOptions() throws JSONException {
                return _client.get("/cluster/options", null);
            }

            /**
             * Set datacenter options.
             *
             * @param bwlimit Set bandwidth/io limits various operations.
             * @param console Select the default Console viewer. You can either
             * use the builtin java applet (VNC; deprecated and maps to html5),
             * an external virt-viewer comtatible application (SPICE), an HTML5
             * based vnc viewer (noVNC), or an HTML5 based console client
             * (xtermjs). If the selected viewer is not available (e.g. SPICE
             * not activated for the VM), the fallback is noVNC. Enum:
             * applet,vv,html5,xtermjs
             * @param delete A list of settings you want to delete.
             * @param description Datacenter description. Shown in the
             * web-interface datacenter notes panel. This is saved as comment
             * inside the configuration file.
             * @param email_from Specify email address to send notification from
             * (default is root@$hostname)
             * @param fencing Set the fencing mode of the HA cluster. Hardware
             * mode needs a valid configuration of fence devices in
             * /etc/pve/ha/fence.cfg. With both all two modes are used. WARNING:
             * 'hardware' and 'both' are EXPERIMENTAL &amp; WIP Enum:
             * watchdog,hardware,both
             * @param ha Cluster wide HA settings.
             * @param http_proxy Specify external http proxy which is used for
             * downloads (example: 'http://username:password@host:port/')
             * @param keyboard Default keybord layout for vnc server. Enum:
             * de,de-ch,da,en-gb,en-us,es,fi,fr,fr-be,fr-ca,fr-ch,hu,is,it,ja,lt,mk,nl,no,pl,pt,pt-br,sv,sl,tr
             * @param language Default GUI language. Enum:
             * ca,da,de,en,es,eu,fa,fr,he,it,ja,nb,nn,pl,pt_BR,ru,sl,sv,tr,zh_CN,zh_TW
             * @param mac_prefix Prefix for autogenerated MAC addresses.
             * @param max_workers Defines how many workers (per node) are
             * maximal started on actions like 'stopall VMs' or task from the
             * ha-manager.
             * @param migration For cluster wide migration settings.
             * @param migration_unsecure Migration is secure using SSH tunnel by
             * default. For secure private networks you can disable it to speed
             * up migration. Deprecated, use the 'migration' property instead!
             * @param u2f u2f
             * @param webauthn webauthn configuration
             * @return Result
             * @throws JSONException
             */

            public Result setOptions(String bwlimit, String console, String delete, String description, String email_from, String fencing, String ha, String http_proxy, String keyboard, String language, String mac_prefix, Integer max_workers, String migration, Boolean migration_unsecure, String u2f, String webauthn) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("bwlimit", bwlimit);
                parameters.put("console", console);
                parameters.put("delete", delete);
                parameters.put("description", description);
                parameters.put("email_from", email_from);
                parameters.put("fencing", fencing);
                parameters.put("ha", ha);
                parameters.put("http_proxy", http_proxy);
                parameters.put("keyboard", keyboard);
                parameters.put("language", language);
                parameters.put("mac_prefix", mac_prefix);
                parameters.put("max_workers", max_workers);
                parameters.put("migration", migration);
                parameters.put("migration_unsecure", migration_unsecure);
                parameters.put("u2f", u2f);
                parameters.put("webauthn", webauthn);
                return _client.set("/cluster/options", parameters);
            }

            /**
             * Set datacenter options.
             *
             * @return Result
             * @throws JSONException
             */

            public Result setOptions() throws JSONException {
                return _client.set("/cluster/options", null);
            }

        }

        public class PVEStatus {

            private final PveClient _client;

            protected PVEStatus(PveClient client) {
                _client = client;

            }

            /**
             * Get cluster status information.
             *
             * @return Result
             * @throws JSONException
             */
            public Result getStatus() throws JSONException {
                return _client.get("/cluster/status", null);
            }

        }

        public class PVENextid {

            private final PveClient _client;

            protected PVENextid(PveClient client) {
                _client = client;

            }

            /**
             * Get next free VMID. If you pass an VMID it will raise an error if
             * the ID is already used.
             *
             * @param vmid The (unique) ID of the VM.
             * @return Result
             * @throws JSONException
             */
            public Result nextid(Integer vmid) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("vmid", vmid);
                return _client.get("/cluster/nextid", parameters);
            }

            /**
             * Get next free VMID. If you pass an VMID it will raise an error if
             * the ID is already used.
             *
             * @return Result
             * @throws JSONException
             */

            public Result nextid() throws JSONException {
                return _client.get("/cluster/nextid", null);
            }

        }

        /**
         * Cluster index.
         *
         * @return Result
         * @throws JSONException
         */
        public Result index() throws JSONException {
            return _client.get("/cluster", null);
        }

    }

    public class PVENodes {

        private final PveClient _client;

        protected PVENodes(PveClient client) {
            _client = client;

        }

        public PVENodeItem get(Object node) {
            return new PVENodeItem(_client, node);
        }

        public class PVENodeItem {

            private final PveClient _client;
            private final Object _node;

            protected PVENodeItem(PveClient client, Object node) {
                _client = client;
                _node = node;
            }

            private PVEQemu _qemu;

            public PVEQemu getQemu() {
                return _qemu == null ? (_qemu = new PVEQemu(_client, _node)) : _qemu;
            }
            private PVELxc _lxc;

            public PVELxc getLxc() {
                return _lxc == null ? (_lxc = new PVELxc(_client, _node)) : _lxc;
            }
            private PVECeph _ceph;

            public PVECeph getCeph() {
                return _ceph == null ? (_ceph = new PVECeph(_client, _node)) : _ceph;
            }
            private PVEVzdump _vzdump;

            public PVEVzdump getVzdump() {
                return _vzdump == null ? (_vzdump = new PVEVzdump(_client, _node)) : _vzdump;
            }
            private PVEServices _services;

            public PVEServices getServices() {
                return _services == null ? (_services = new PVEServices(_client, _node)) : _services;
            }
            private PVESubscription _subscription;

            public PVESubscription getSubscription() {
                return _subscription == null ? (_subscription = new PVESubscription(_client, _node)) : _subscription;
            }
            private PVENetwork _network;

            public PVENetwork getNetwork() {
                return _network == null ? (_network = new PVENetwork(_client, _node)) : _network;
            }
            private PVETasks _tasks;

            public PVETasks getTasks() {
                return _tasks == null ? (_tasks = new PVETasks(_client, _node)) : _tasks;
            }
            private PVEScan _scan;

            public PVEScan getScan() {
                return _scan == null ? (_scan = new PVEScan(_client, _node)) : _scan;
            }
            private PVEHardware _hardware;

            public PVEHardware getHardware() {
                return _hardware == null ? (_hardware = new PVEHardware(_client, _node)) : _hardware;
            }
            private PVECapabilities _capabilities;

            public PVECapabilities getCapabilities() {
                return _capabilities == null ? (_capabilities = new PVECapabilities(_client, _node)) : _capabilities;
            }
            private PVEStorage _storage;

            public PVEStorage getStorage() {
                return _storage == null ? (_storage = new PVEStorage(_client, _node)) : _storage;
            }
            private PVEDisks _disks;

            public PVEDisks getDisks() {
                return _disks == null ? (_disks = new PVEDisks(_client, _node)) : _disks;
            }
            private PVEApt _apt;

            public PVEApt getApt() {
                return _apt == null ? (_apt = new PVEApt(_client, _node)) : _apt;
            }
            private PVEFirewall _firewall;

            public PVEFirewall getFirewall() {
                return _firewall == null ? (_firewall = new PVEFirewall(_client, _node)) : _firewall;
            }
            private PVEReplication _replication;

            public PVEReplication getReplication() {
                return _replication == null ? (_replication = new PVEReplication(_client, _node)) : _replication;
            }
            private PVECertificates _certificates;

            public PVECertificates getCertificates() {
                return _certificates == null ? (_certificates = new PVECertificates(_client, _node)) : _certificates;
            }
            private PVEConfig _config;

            public PVEConfig getConfig() {
                return _config == null ? (_config = new PVEConfig(_client, _node)) : _config;
            }
            private PVESdn _sdn;

            public PVESdn getSdn() {
                return _sdn == null ? (_sdn = new PVESdn(_client, _node)) : _sdn;
            }
            private PVEVersion _version;

            public PVEVersion getVersion() {
                return _version == null ? (_version = new PVEVersion(_client, _node)) : _version;
            }
            private PVEStatus _status;

            public PVEStatus getStatus() {
                return _status == null ? (_status = new PVEStatus(_client, _node)) : _status;
            }
            private PVENetstat _netstat;

            public PVENetstat getNetstat() {
                return _netstat == null ? (_netstat = new PVENetstat(_client, _node)) : _netstat;
            }
            private PVEExecute _execute;

            public PVEExecute getExecute() {
                return _execute == null ? (_execute = new PVEExecute(_client, _node)) : _execute;
            }
            private PVEWakeonlan _wakeonlan;

            public PVEWakeonlan getWakeonlan() {
                return _wakeonlan == null ? (_wakeonlan = new PVEWakeonlan(_client, _node)) : _wakeonlan;
            }
            private PVERrd _rrd;

            public PVERrd getRrd() {
                return _rrd == null ? (_rrd = new PVERrd(_client, _node)) : _rrd;
            }
            private PVERrddata _rrddata;

            public PVERrddata getRrddata() {
                return _rrddata == null ? (_rrddata = new PVERrddata(_client, _node)) : _rrddata;
            }
            private PVESyslog _syslog;

            public PVESyslog getSyslog() {
                return _syslog == null ? (_syslog = new PVESyslog(_client, _node)) : _syslog;
            }
            private PVEJournal _journal;

            public PVEJournal getJournal() {
                return _journal == null ? (_journal = new PVEJournal(_client, _node)) : _journal;
            }
            private PVEVncshell _vncshell;

            public PVEVncshell getVncshell() {
                return _vncshell == null ? (_vncshell = new PVEVncshell(_client, _node)) : _vncshell;
            }
            private PVETermproxy _termproxy;

            public PVETermproxy getTermproxy() {
                return _termproxy == null ? (_termproxy = new PVETermproxy(_client, _node)) : _termproxy;
            }
            private PVEVncwebsocket _vncwebsocket;

            public PVEVncwebsocket getVncwebsocket() {
                return _vncwebsocket == null ? (_vncwebsocket = new PVEVncwebsocket(_client, _node)) : _vncwebsocket;
            }
            private PVESpiceshell _spiceshell;

            public PVESpiceshell getSpiceshell() {
                return _spiceshell == null ? (_spiceshell = new PVESpiceshell(_client, _node)) : _spiceshell;
            }
            private PVEDns _dns;

            public PVEDns getDns() {
                return _dns == null ? (_dns = new PVEDns(_client, _node)) : _dns;
            }
            private PVETime _time;

            public PVETime getTime() {
                return _time == null ? (_time = new PVETime(_client, _node)) : _time;
            }
            private PVEAplinfo _aplinfo;

            public PVEAplinfo getAplinfo() {
                return _aplinfo == null ? (_aplinfo = new PVEAplinfo(_client, _node)) : _aplinfo;
            }
            private PVEQueryUrlMetadata _queryUrlMetadata;

            public PVEQueryUrlMetadata getQueryUrlMetadata() {
                return _queryUrlMetadata == null ? (_queryUrlMetadata = new PVEQueryUrlMetadata(_client, _node)) : _queryUrlMetadata;
            }
            private PVEReport _report;

            public PVEReport getReport() {
                return _report == null ? (_report = new PVEReport(_client, _node)) : _report;
            }
            private PVEStartall _startall;

            public PVEStartall getStartall() {
                return _startall == null ? (_startall = new PVEStartall(_client, _node)) : _startall;
            }
            private PVEStopall _stopall;

            public PVEStopall getStopall() {
                return _stopall == null ? (_stopall = new PVEStopall(_client, _node)) : _stopall;
            }
            private PVEMigrateall _migrateall;

            public PVEMigrateall getMigrateall() {
                return _migrateall == null ? (_migrateall = new PVEMigrateall(_client, _node)) : _migrateall;
            }
            private PVEHosts _hosts;

            public PVEHosts getHosts() {
                return _hosts == null ? (_hosts = new PVEHosts(_client, _node)) : _hosts;
            }

            public class PVEQemu {

                private final PveClient _client;
                private final Object _node;

                protected PVEQemu(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEVmidItem get(Object vmid) {
                    return new PVEVmidItem(_client, _node, vmid);
                }

                public class PVEVmidItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _vmid;

                    protected PVEVmidItem(PveClient client, Object node, Object vmid) {
                        _client = client;
                        _node = node;
                        _vmid = vmid;
                    }

                    private PVEFirewall _firewall;

                    public PVEFirewall getFirewall() {
                        return _firewall == null ? (_firewall = new PVEFirewall(_client, _node, _vmid)) : _firewall;
                    }
                    private PVEAgent _agent;

                    public PVEAgent getAgent() {
                        return _agent == null ? (_agent = new PVEAgent(_client, _node, _vmid)) : _agent;
                    }
                    private PVERrd _rrd;

                    public PVERrd getRrd() {
                        return _rrd == null ? (_rrd = new PVERrd(_client, _node, _vmid)) : _rrd;
                    }
                    private PVERrddata _rrddata;

                    public PVERrddata getRrddata() {
                        return _rrddata == null ? (_rrddata = new PVERrddata(_client, _node, _vmid)) : _rrddata;
                    }
                    private PVEConfig _config;

                    public PVEConfig getConfig() {
                        return _config == null ? (_config = new PVEConfig(_client, _node, _vmid)) : _config;
                    }
                    private PVEPending _pending;

                    public PVEPending getPending() {
                        return _pending == null ? (_pending = new PVEPending(_client, _node, _vmid)) : _pending;
                    }
                    private PVEUnlink _unlink;

                    public PVEUnlink getUnlink() {
                        return _unlink == null ? (_unlink = new PVEUnlink(_client, _node, _vmid)) : _unlink;
                    }
                    private PVEVncproxy _vncproxy;

                    public PVEVncproxy getVncproxy() {
                        return _vncproxy == null ? (_vncproxy = new PVEVncproxy(_client, _node, _vmid)) : _vncproxy;
                    }
                    private PVETermproxy _termproxy;

                    public PVETermproxy getTermproxy() {
                        return _termproxy == null ? (_termproxy = new PVETermproxy(_client, _node, _vmid)) : _termproxy;
                    }
                    private PVEVncwebsocket _vncwebsocket;

                    public PVEVncwebsocket getVncwebsocket() {
                        return _vncwebsocket == null ? (_vncwebsocket = new PVEVncwebsocket(_client, _node, _vmid)) : _vncwebsocket;
                    }
                    private PVESpiceproxy _spiceproxy;

                    public PVESpiceproxy getSpiceproxy() {
                        return _spiceproxy == null ? (_spiceproxy = new PVESpiceproxy(_client, _node, _vmid)) : _spiceproxy;
                    }
                    private PVEStatus _status;

                    public PVEStatus getStatus() {
                        return _status == null ? (_status = new PVEStatus(_client, _node, _vmid)) : _status;
                    }
                    private PVESendkey _sendkey;

                    public PVESendkey getSendkey() {
                        return _sendkey == null ? (_sendkey = new PVESendkey(_client, _node, _vmid)) : _sendkey;
                    }
                    private PVEFeature _feature;

                    public PVEFeature getFeature() {
                        return _feature == null ? (_feature = new PVEFeature(_client, _node, _vmid)) : _feature;
                    }
                    private PVEClone _clone;

                    public PVEClone getClone() {
                        return _clone == null ? (_clone = new PVEClone(_client, _node, _vmid)) : _clone;
                    }
                    private PVEMoveDisk _moveDisk;

                    public PVEMoveDisk getMoveDisk() {
                        return _moveDisk == null ? (_moveDisk = new PVEMoveDisk(_client, _node, _vmid)) : _moveDisk;
                    }
                    private PVEMigrate _migrate;

                    public PVEMigrate getMigrate() {
                        return _migrate == null ? (_migrate = new PVEMigrate(_client, _node, _vmid)) : _migrate;
                    }
                    private PVEMonitor _monitor;

                    public PVEMonitor getMonitor() {
                        return _monitor == null ? (_monitor = new PVEMonitor(_client, _node, _vmid)) : _monitor;
                    }
                    private PVEResize _resize;

                    public PVEResize getResize() {
                        return _resize == null ? (_resize = new PVEResize(_client, _node, _vmid)) : _resize;
                    }
                    private PVESnapshot _snapshot;

                    public PVESnapshot getSnapshot() {
                        return _snapshot == null ? (_snapshot = new PVESnapshot(_client, _node, _vmid)) : _snapshot;
                    }
                    private PVETemplate _template;

                    public PVETemplate getTemplate() {
                        return _template == null ? (_template = new PVETemplate(_client, _node, _vmid)) : _template;
                    }
                    private PVECloudinit _cloudinit;

                    public PVECloudinit getCloudinit() {
                        return _cloudinit == null ? (_cloudinit = new PVECloudinit(_client, _node, _vmid)) : _cloudinit;
                    }

                    public class PVEFirewall {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEFirewall(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        private PVERules _rules;

                        public PVERules getRules() {
                            return _rules == null ? (_rules = new PVERules(_client, _node, _vmid)) : _rules;
                        }
                        private PVEAliases _aliases;

                        public PVEAliases getAliases() {
                            return _aliases == null ? (_aliases = new PVEAliases(_client, _node, _vmid)) : _aliases;
                        }
                        private PVEIpset _ipset;

                        public PVEIpset getIpset() {
                            return _ipset == null ? (_ipset = new PVEIpset(_client, _node, _vmid)) : _ipset;
                        }
                        private PVEOptions _options;

                        public PVEOptions getOptions() {
                            return _options == null ? (_options = new PVEOptions(_client, _node, _vmid)) : _options;
                        }
                        private PVELog _log;

                        public PVELog getLog() {
                            return _log == null ? (_log = new PVELog(_client, _node, _vmid)) : _log;
                        }
                        private PVERefs _refs;

                        public PVERefs getRefs() {
                            return _refs == null ? (_refs = new PVERefs(_client, _node, _vmid)) : _refs;
                        }

                        public class PVERules {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVERules(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            public PVEPosItem get(Object pos) {
                                return new PVEPosItem(_client, _node, _vmid, pos);
                            }

                            public class PVEPosItem {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _pos;

                                protected PVEPosItem(PveClient client, Object node, Object vmid, Object pos) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _pos = pos;
                                }

                                /**
                                 * Delete rule.
                                 *
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result deleteRule(String digest) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("digest", digest);
                                    return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules/" + _pos + "", parameters);
                                }

                                /**
                                 * Delete rule.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result deleteRule() throws JSONException {
                                    return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules/" + _pos + "", null);
                                }

                                /**
                                 * Get single rule data.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result getRule() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules/" + _pos + "", null);
                                }

                                /**
                                 * Modify rule data.
                                 *
                                 * @param action Rule action ('ACCEPT', 'DROP',
                                 * 'REJECT') or security group name.
                                 * @param comment Descriptive comment.
                                 * @param delete A list of settings you want to
                                 * delete.
                                 * @param dest Restrict packet destination
                                 * address. This can refer to a single IP
                                 * address, an IP set ('+ipsetname') or an IP
                                 * alias definition. You can also specify an
                                 * address range like
                                 * '20.34.101.207-201.3.9.99', or a list of IP
                                 * addresses and networks (entries are separated
                                 * by comma). Please do not mix IPv4 and IPv6
                                 * addresses inside such lists.
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @param dport Restrict TCP/UDP destination
                                 * port. You can use service names or simple
                                 * numbers (0-65535), as defined in
                                 * '/etc/services'. Port ranges can be specified
                                 * with '\d+:\d+', for example '80:85', and you
                                 * can use comma separated list to match several
                                 * ports or ranges.
                                 * @param enable Flag to enable/disable a rule.
                                 * @param icmp_type Specify icmp-type. Only
                                 * valid if proto equals 'icmp'.
                                 * @param iface Network interface name. You have
                                 * to use network configuration key names for
                                 * VMs and containers ('net\d+'). Host related
                                 * rules can use arbitrary strings.
                                 * @param log Log level for firewall rule. Enum:
                                 * emerg,alert,crit,err,warning,notice,info,debug,nolog
                                 * @param macro Use predefined standard macro.
                                 * @param moveto Move rule to new position
                                 * &amp;lt;moveto&amp;gt;. Other arguments are
                                 * ignored.
                                 * @param proto IP protocol. You can use
                                 * protocol names ('tcp'/'udp') or simple
                                 * numbers, as defined in '/etc/protocols'.
                                 * @param source Restrict packet source address.
                                 * This can refer to a single IP address, an IP
                                 * set ('+ipsetname') or an IP alias definition.
                                 * You can also specify an address range like
                                 * '20.34.101.207-201.3.9.99', or a list of IP
                                 * addresses and networks (entries are separated
                                 * by comma). Please do not mix IPv4 and IPv6
                                 * addresses inside such lists.
                                 * @param sport Restrict TCP/UDP source port.
                                 * You can use service names or simple numbers
                                 * (0-65535), as defined in '/etc/services'.
                                 * Port ranges can be specified with '\d+:\d+',
                                 * for example '80:85', and you can use comma
                                 * separated list to match several ports or
                                 * ranges.
                                 * @param type Rule type. Enum: in,out,group
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateRule(String action, String comment, String delete, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer moveto, String proto, String source, String sport, String type) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("action", action);
                                    parameters.put("comment", comment);
                                    parameters.put("delete", delete);
                                    parameters.put("dest", dest);
                                    parameters.put("digest", digest);
                                    parameters.put("dport", dport);
                                    parameters.put("enable", enable);
                                    parameters.put("icmp-type", icmp_type);
                                    parameters.put("iface", iface);
                                    parameters.put("log", log);
                                    parameters.put("macro", macro);
                                    parameters.put("moveto", moveto);
                                    parameters.put("proto", proto);
                                    parameters.put("source", source);
                                    parameters.put("sport", sport);
                                    parameters.put("type", type);
                                    return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules/" + _pos + "", parameters);
                                }

                                /**
                                 * Modify rule data.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateRule() throws JSONException {
                                    return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules/" + _pos + "", null);
                                }

                            }

                            /**
                             * List rules.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getRules() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules", null);
                            }

                            /**
                             * Create new rule.
                             *
                             * @param action Rule action ('ACCEPT', 'DROP',
                             * 'REJECT') or security group name.
                             * @param type Rule type. Enum: in,out,group
                             * @param comment Descriptive comment.
                             * @param dest Restrict packet destination address.
                             * This can refer to a single IP address, an IP set
                             * ('+ipsetname') or an IP alias definition. You can
                             * also specify an address range like
                             * '20.34.101.207-201.3.9.99', or a list of IP
                             * addresses and networks (entries are separated by
                             * comma). Please do not mix IPv4 and IPv6 addresses
                             * inside such lists.
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param dport Restrict TCP/UDP destination port.
                             * You can use service names or simple numbers
                             * (0-65535), as defined in '/etc/services'. Port
                             * ranges can be specified with '\d+:\d+', for
                             * example '80:85', and you can use comma separated
                             * list to match several ports or ranges.
                             * @param enable Flag to enable/disable a rule.
                             * @param icmp_type Specify icmp-type. Only valid if
                             * proto equals 'icmp'.
                             * @param iface Network interface name. You have to
                             * use network configuration key names for VMs and
                             * containers ('net\d+'). Host related rules can use
                             * arbitrary strings.
                             * @param log Log level for firewall rule. Enum:
                             * emerg,alert,crit,err,warning,notice,info,debug,nolog
                             * @param macro Use predefined standard macro.
                             * @param pos Update rule at position
                             * &amp;lt;pos&amp;gt;.
                             * @param proto IP protocol. You can use protocol
                             * names ('tcp'/'udp') or simple numbers, as defined
                             * in '/etc/protocols'.
                             * @param source Restrict packet source address.
                             * This can refer to a single IP address, an IP set
                             * ('+ipsetname') or an IP alias definition. You can
                             * also specify an address range like
                             * '20.34.101.207-201.3.9.99', or a list of IP
                             * addresses and networks (entries are separated by
                             * comma). Please do not mix IPv4 and IPv6 addresses
                             * inside such lists.
                             * @param sport Restrict TCP/UDP source port. You
                             * can use service names or simple numbers
                             * (0-65535), as defined in '/etc/services'. Port
                             * ranges can be specified with '\d+:\d+', for
                             * example '80:85', and you can use comma separated
                             * list to match several ports or ranges.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createRule(String action, String type, String comment, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer pos, String proto, String source, String sport) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("action", action);
                                parameters.put("type", type);
                                parameters.put("comment", comment);
                                parameters.put("dest", dest);
                                parameters.put("digest", digest);
                                parameters.put("dport", dport);
                                parameters.put("enable", enable);
                                parameters.put("icmp-type", icmp_type);
                                parameters.put("iface", iface);
                                parameters.put("log", log);
                                parameters.put("macro", macro);
                                parameters.put("pos", pos);
                                parameters.put("proto", proto);
                                parameters.put("source", source);
                                parameters.put("sport", sport);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules", parameters);
                            }

                            /**
                             * Create new rule.
                             *
                             * @param action Rule action ('ACCEPT', 'DROP',
                             * 'REJECT') or security group name.
                             * @param type Rule type. Enum: in,out,group
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createRule(String action, String type) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("action", action);
                                parameters.put("type", type);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/rules", parameters);
                            }

                        }

                        public class PVEAliases {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEAliases(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            public PVENameItem get(Object name) {
                                return new PVENameItem(_client, _node, _vmid, name);
                            }

                            public class PVENameItem {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _name;

                                protected PVENameItem(PveClient client, Object node, Object vmid, Object name) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _name = name;
                                }

                                /**
                                 * Remove IP or Network alias.
                                 *
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result removeAlias(String digest) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("digest", digest);
                                    return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases/" + _name + "", parameters);
                                }

                                /**
                                 * Remove IP or Network alias.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result removeAlias() throws JSONException {
                                    return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases/" + _name + "", null);
                                }

                                /**
                                 * Read alias.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result readAlias() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases/" + _name + "", null);
                                }

                                /**
                                 * Update IP or Network alias.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @param comment
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @param rename Rename an existing alias.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateAlias(String cidr, String comment, String digest, String rename) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    parameters.put("comment", comment);
                                    parameters.put("digest", digest);
                                    parameters.put("rename", rename);
                                    return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases/" + _name + "", parameters);
                                }

                                /**
                                 * Update IP or Network alias.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateAlias(String cidr) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases/" + _name + "", parameters);
                                }

                            }

                            /**
                             * List aliases
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getAliases() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases", null);
                            }

                            /**
                             * Create IP or Network Alias.
                             *
                             * @param cidr Network/IP specification in CIDR
                             * format.
                             * @param name Alias name.
                             * @param comment
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createAlias(String cidr, String name, String comment) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("cidr", cidr);
                                parameters.put("name", name);
                                parameters.put("comment", comment);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases", parameters);
                            }

                            /**
                             * Create IP or Network Alias.
                             *
                             * @param cidr Network/IP specification in CIDR
                             * format.
                             * @param name Alias name.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createAlias(String cidr, String name) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("cidr", cidr);
                                parameters.put("name", name);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/aliases", parameters);
                            }

                        }

                        public class PVEIpset {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEIpset(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            public PVENameItem get(Object name) {
                                return new PVENameItem(_client, _node, _vmid, name);
                            }

                            public class PVENameItem {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _name;

                                protected PVENameItem(PveClient client, Object node, Object vmid, Object name) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _name = name;
                                }

                                public PVECidrItem get(Object cidr) {
                                    return new PVECidrItem(_client, _node, _vmid, _name, cidr);
                                }

                                public class PVECidrItem {

                                    private final PveClient _client;
                                    private final Object _node;
                                    private final Object _vmid;
                                    private final Object _name;
                                    private final Object _cidr;

                                    protected PVECidrItem(PveClient client, Object node, Object vmid, Object name, Object cidr) {
                                        _client = client;
                                        _node = node;
                                        _vmid = vmid;
                                        _name = name;
                                        _cidr = cidr;
                                    }

                                    /**
                                     * Remove IP or Network from IPSet.
                                     *
                                     * @param digest Prevent changes if current
                                     * configuration file has different SHA1
                                     * digest. This can be used to prevent
                                     * concurrent modifications.
                                     * @return Result
                                     * @throws JSONException
                                     */
                                    public Result removeIp(String digest) throws JSONException {
                                        Map<String, Object> parameters = new HashMap<>();
                                        parameters.put("digest", digest);
                                        return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", parameters);
                                    }

                                    /**
                                     * Remove IP or Network from IPSet.
                                     *
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result removeIp() throws JSONException {
                                        return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", null);
                                    }

                                    /**
                                     * Read IP or Network settings from IPSet.
                                     *
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result readIp() throws JSONException {
                                        return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", null);
                                    }

                                    /**
                                     * Update IP or Network settings
                                     *
                                     * @param comment
                                     * @param digest Prevent changes if current
                                     * configuration file has different SHA1
                                     * digest. This can be used to prevent
                                     * concurrent modifications.
                                     * @param nomatch
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result updateIp(String comment, String digest, Boolean nomatch) throws JSONException {
                                        Map<String, Object> parameters = new HashMap<>();
                                        parameters.put("comment", comment);
                                        parameters.put("digest", digest);
                                        parameters.put("nomatch", nomatch);
                                        return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", parameters);
                                    }

                                    /**
                                     * Update IP or Network settings
                                     *
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result updateIp() throws JSONException {
                                        return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", null);
                                    }

                                }

                                /**
                                 * Delete IPSet
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result deleteIpset() throws JSONException {
                                    return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "", null);
                                }

                                /**
                                 * List IPSet content
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result getIpset() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "", null);
                                }

                                /**
                                 * Add IP or Network to IPSet.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @param comment
                                 * @param nomatch
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result createIp(String cidr, String comment, Boolean nomatch) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    parameters.put("comment", comment);
                                    parameters.put("nomatch", nomatch);
                                    return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "", parameters);
                                }

                                /**
                                 * Add IP or Network to IPSet.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result createIp(String cidr) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset/" + _name + "", parameters);
                                }

                            }

                            /**
                             * List IPSets
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result ipsetIndex() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset", null);
                            }

                            /**
                             * Create new IPSet
                             *
                             * @param name IP set name.
                             * @param comment
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param rename Rename an existing IPSet. You can
                             * set 'rename' to the same value as 'name' to
                             * update the 'comment' of an existing IPSet.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createIpset(String name, String comment, String digest, String rename) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("name", name);
                                parameters.put("comment", comment);
                                parameters.put("digest", digest);
                                parameters.put("rename", rename);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset", parameters);
                            }

                            /**
                             * Create new IPSet
                             *
                             * @param name IP set name.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createIpset(String name) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("name", name);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/ipset", parameters);
                            }

                        }

                        public class PVEOptions {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEOptions(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Get VM firewall options.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getOptions() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/options", null);
                            }

                            /**
                             * Set Firewall options.
                             *
                             * @param delete A list of settings you want to
                             * delete.
                             * @param dhcp Enable DHCP.
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param enable Enable/disable firewall rules.
                             * @param ipfilter Enable default IP filters. This
                             * is equivalent to adding an empty
                             * ipfilter-net&amp;lt;id&amp;gt; ipset for every
                             * interface. Such ipsets implicitly contain sane
                             * default restrictions such as restricting IPv6
                             * link local addresses to the one derived from the
                             * interface's MAC address. For containers the
                             * configured IP addresses will be implicitly added.
                             * @param log_level_in Log level for incoming
                             * traffic. Enum:
                             * emerg,alert,crit,err,warning,notice,info,debug,nolog
                             * @param log_level_out Log level for outgoing
                             * traffic. Enum:
                             * emerg,alert,crit,err,warning,notice,info,debug,nolog
                             * @param macfilter Enable/disable MAC address
                             * filter.
                             * @param ndp Enable NDP (Neighbor Discovery
                             * Protocol).
                             * @param policy_in Input policy. Enum:
                             * ACCEPT,REJECT,DROP
                             * @param policy_out Output policy. Enum:
                             * ACCEPT,REJECT,DROP
                             * @param radv Allow sending Router Advertisement.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result setOptions(String delete, Boolean dhcp, String digest, Boolean enable, Boolean ipfilter, String log_level_in, String log_level_out, Boolean macfilter, Boolean ndp, String policy_in, String policy_out, Boolean radv) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("delete", delete);
                                parameters.put("dhcp", dhcp);
                                parameters.put("digest", digest);
                                parameters.put("enable", enable);
                                parameters.put("ipfilter", ipfilter);
                                parameters.put("log_level_in", log_level_in);
                                parameters.put("log_level_out", log_level_out);
                                parameters.put("macfilter", macfilter);
                                parameters.put("ndp", ndp);
                                parameters.put("policy_in", policy_in);
                                parameters.put("policy_out", policy_out);
                                parameters.put("radv", radv);
                                return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/options", parameters);
                            }

                            /**
                             * Set Firewall options.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result setOptions() throws JSONException {
                                return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/options", null);
                            }

                        }

                        public class PVELog {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVELog(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Read firewall log
                             *
                             * @param limit
                             * @param start
                             * @return Result
                             * @throws JSONException
                             */
                            public Result log(Integer limit, Integer start) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("limit", limit);
                                parameters.put("start", start);
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/log", parameters);
                            }

                            /**
                             * Read firewall log
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result log() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/log", null);
                            }

                        }

                        public class PVERefs {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVERefs(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Lists possible IPSet/Alias reference which are
                             * allowed in source/dest properties.
                             *
                             * @param type Only list references of specified
                             * type. Enum: alias,ipset
                             * @return Result
                             * @throws JSONException
                             */
                            public Result refs(String type) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("type", type);
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/refs", parameters);
                            }

                            /**
                             * Lists possible IPSet/Alias reference which are
                             * allowed in source/dest properties.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result refs() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall/refs", null);
                            }

                        }

                        /**
                         * Directory index.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result index() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/firewall", null);
                        }

                    }

                    public class PVEAgent {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEAgent(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        private PVEFsfreezeFreeze _fsfreezeFreeze;

                        public PVEFsfreezeFreeze getFsfreezeFreeze() {
                            return _fsfreezeFreeze == null ? (_fsfreezeFreeze = new PVEFsfreezeFreeze(_client, _node, _vmid)) : _fsfreezeFreeze;
                        }
                        private PVEFsfreezeStatus _fsfreezeStatus;

                        public PVEFsfreezeStatus getFsfreezeStatus() {
                            return _fsfreezeStatus == null ? (_fsfreezeStatus = new PVEFsfreezeStatus(_client, _node, _vmid)) : _fsfreezeStatus;
                        }
                        private PVEFsfreezeThaw _fsfreezeThaw;

                        public PVEFsfreezeThaw getFsfreezeThaw() {
                            return _fsfreezeThaw == null ? (_fsfreezeThaw = new PVEFsfreezeThaw(_client, _node, _vmid)) : _fsfreezeThaw;
                        }
                        private PVEFstrim _fstrim;

                        public PVEFstrim getFstrim() {
                            return _fstrim == null ? (_fstrim = new PVEFstrim(_client, _node, _vmid)) : _fstrim;
                        }
                        private PVEGetFsinfo _getFsinfo;

                        public PVEGetFsinfo getGetFsinfo() {
                            return _getFsinfo == null ? (_getFsinfo = new PVEGetFsinfo(_client, _node, _vmid)) : _getFsinfo;
                        }
                        private PVEGetHostName _getHostName;

                        public PVEGetHostName getGetHostName() {
                            return _getHostName == null ? (_getHostName = new PVEGetHostName(_client, _node, _vmid)) : _getHostName;
                        }
                        private PVEGetMemoryBlockInfo _getMemoryBlockInfo;

                        public PVEGetMemoryBlockInfo getGetMemoryBlockInfo() {
                            return _getMemoryBlockInfo == null ? (_getMemoryBlockInfo = new PVEGetMemoryBlockInfo(_client, _node, _vmid)) : _getMemoryBlockInfo;
                        }
                        private PVEGetMemoryBlocks _getMemoryBlocks;

                        public PVEGetMemoryBlocks getGetMemoryBlocks() {
                            return _getMemoryBlocks == null ? (_getMemoryBlocks = new PVEGetMemoryBlocks(_client, _node, _vmid)) : _getMemoryBlocks;
                        }
                        private PVEGetOsinfo _getOsinfo;

                        public PVEGetOsinfo getGetOsinfo() {
                            return _getOsinfo == null ? (_getOsinfo = new PVEGetOsinfo(_client, _node, _vmid)) : _getOsinfo;
                        }
                        private PVEGetTime _getTime;

                        public PVEGetTime getGetTime() {
                            return _getTime == null ? (_getTime = new PVEGetTime(_client, _node, _vmid)) : _getTime;
                        }
                        private PVEGetTimezone _getTimezone;

                        public PVEGetTimezone getGetTimezone() {
                            return _getTimezone == null ? (_getTimezone = new PVEGetTimezone(_client, _node, _vmid)) : _getTimezone;
                        }
                        private PVEGetUsers _getUsers;

                        public PVEGetUsers getGetUsers() {
                            return _getUsers == null ? (_getUsers = new PVEGetUsers(_client, _node, _vmid)) : _getUsers;
                        }
                        private PVEGetVcpus _getVcpus;

                        public PVEGetVcpus getGetVcpus() {
                            return _getVcpus == null ? (_getVcpus = new PVEGetVcpus(_client, _node, _vmid)) : _getVcpus;
                        }
                        private PVEInfo _info;

                        public PVEInfo getInfo() {
                            return _info == null ? (_info = new PVEInfo(_client, _node, _vmid)) : _info;
                        }
                        private PVENetworkGetInterfaces _networkGetInterfaces;

                        public PVENetworkGetInterfaces getNetworkGetInterfaces() {
                            return _networkGetInterfaces == null ? (_networkGetInterfaces = new PVENetworkGetInterfaces(_client, _node, _vmid)) : _networkGetInterfaces;
                        }
                        private PVEPing _ping;

                        public PVEPing getPing() {
                            return _ping == null ? (_ping = new PVEPing(_client, _node, _vmid)) : _ping;
                        }
                        private PVEShutdown _shutdown;

                        public PVEShutdown getShutdown() {
                            return _shutdown == null ? (_shutdown = new PVEShutdown(_client, _node, _vmid)) : _shutdown;
                        }
                        private PVESuspendDisk _suspendDisk;

                        public PVESuspendDisk getSuspendDisk() {
                            return _suspendDisk == null ? (_suspendDisk = new PVESuspendDisk(_client, _node, _vmid)) : _suspendDisk;
                        }
                        private PVESuspendHybrid _suspendHybrid;

                        public PVESuspendHybrid getSuspendHybrid() {
                            return _suspendHybrid == null ? (_suspendHybrid = new PVESuspendHybrid(_client, _node, _vmid)) : _suspendHybrid;
                        }
                        private PVESuspendRam _suspendRam;

                        public PVESuspendRam getSuspendRam() {
                            return _suspendRam == null ? (_suspendRam = new PVESuspendRam(_client, _node, _vmid)) : _suspendRam;
                        }
                        private PVESetUserPassword _setUserPassword;

                        public PVESetUserPassword getSetUserPassword() {
                            return _setUserPassword == null ? (_setUserPassword = new PVESetUserPassword(_client, _node, _vmid)) : _setUserPassword;
                        }
                        private PVEExec _exec;

                        public PVEExec getExec() {
                            return _exec == null ? (_exec = new PVEExec(_client, _node, _vmid)) : _exec;
                        }
                        private PVEExecStatus _execStatus;

                        public PVEExecStatus getExecStatus() {
                            return _execStatus == null ? (_execStatus = new PVEExecStatus(_client, _node, _vmid)) : _execStatus;
                        }
                        private PVEFileRead _fileRead;

                        public PVEFileRead getFileRead() {
                            return _fileRead == null ? (_fileRead = new PVEFileRead(_client, _node, _vmid)) : _fileRead;
                        }
                        private PVEFileWrite _fileWrite;

                        public PVEFileWrite getFileWrite() {
                            return _fileWrite == null ? (_fileWrite = new PVEFileWrite(_client, _node, _vmid)) : _fileWrite;
                        }

                        public class PVEFsfreezeFreeze {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEFsfreezeFreeze(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute fsfreeze-freeze.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result fsfreezeFreeze() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/fsfreeze-freeze", null);
                            }

                        }

                        public class PVEFsfreezeStatus {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEFsfreezeStatus(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute fsfreeze-status.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result fsfreezeStatus() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/fsfreeze-status", null);
                            }

                        }

                        public class PVEFsfreezeThaw {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEFsfreezeThaw(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute fsfreeze-thaw.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result fsfreezeThaw() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/fsfreeze-thaw", null);
                            }

                        }

                        public class PVEFstrim {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEFstrim(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute fstrim.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result fstrim() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/fstrim", null);
                            }

                        }

                        public class PVEGetFsinfo {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetFsinfo(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-fsinfo.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getFsinfo() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-fsinfo", null);
                            }

                        }

                        public class PVEGetHostName {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetHostName(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-host-name.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getHostName() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-host-name", null);
                            }

                        }

                        public class PVEGetMemoryBlockInfo {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetMemoryBlockInfo(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-memory-block-info.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getMemoryBlockInfo() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-memory-block-info", null);
                            }

                        }

                        public class PVEGetMemoryBlocks {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetMemoryBlocks(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-memory-blocks.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getMemoryBlocks() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-memory-blocks", null);
                            }

                        }

                        public class PVEGetOsinfo {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetOsinfo(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-osinfo.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getOsinfo() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-osinfo", null);
                            }

                        }

                        public class PVEGetTime {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetTime(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-time.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getTime() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-time", null);
                            }

                        }

                        public class PVEGetTimezone {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetTimezone(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-timezone.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getTimezone() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-timezone", null);
                            }

                        }

                        public class PVEGetUsers {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetUsers(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-users.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getUsers() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-users", null);
                            }

                        }

                        public class PVEGetVcpus {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEGetVcpus(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute get-vcpus.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getVcpus() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/get-vcpus", null);
                            }

                        }

                        public class PVEInfo {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEInfo(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute info.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result info() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/info", null);
                            }

                        }

                        public class PVENetworkGetInterfaces {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVENetworkGetInterfaces(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute network-get-interfaces.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result networkGetInterfaces() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/network-get-interfaces", null);
                            }

                        }

                        public class PVEPing {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEPing(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute ping.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result ping() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/ping", null);
                            }

                        }

                        public class PVEShutdown {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEShutdown(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute shutdown.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result shutdown() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/shutdown", null);
                            }

                        }

                        public class PVESuspendDisk {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVESuspendDisk(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute suspend-disk.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result suspendDisk() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/suspend-disk", null);
                            }

                        }

                        public class PVESuspendHybrid {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVESuspendHybrid(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute suspend-hybrid.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result suspendHybrid() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/suspend-hybrid", null);
                            }

                        }

                        public class PVESuspendRam {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVESuspendRam(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Execute suspend-ram.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result suspendRam() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/suspend-ram", null);
                            }

                        }

                        public class PVESetUserPassword {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVESetUserPassword(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Sets the password for the given user to the given
                             * password
                             *
                             * @param password The new password.
                             * @param username The user to set the password for.
                             * @param crypted set to 1 if the password has
                             * already been passed through crypt()
                             * @return Result
                             * @throws JSONException
                             */
                            public Result setUserPassword(String password, String username, Boolean crypted) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("password", password);
                                parameters.put("username", username);
                                parameters.put("crypted", crypted);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/set-user-password", parameters);
                            }

                            /**
                             * Sets the password for the given user to the given
                             * password
                             *
                             * @param password The new password.
                             * @param username The user to set the password for.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result setUserPassword(String password, String username) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("password", password);
                                parameters.put("username", username);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/set-user-password", parameters);
                            }

                        }

                        public class PVEExec {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEExec(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Executes the given command in the vm via the
                             * guest-agent and returns an object with the pid.
                             *
                             * @param command The command as a list of program +
                             * arguments
                             * @param input_data Data to pass as 'input-data' to
                             * the guest. Usually treated as STDIN to 'command'.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result exec(String command, String input_data) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("command", command);
                                parameters.put("input-data", input_data);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/exec", parameters);
                            }

                            /**
                             * Executes the given command in the vm via the
                             * guest-agent and returns an object with the pid.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result exec() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/exec", null);
                            }

                        }

                        public class PVEExecStatus {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEExecStatus(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Gets the status of the given pid started by the
                             * guest-agent
                             *
                             * @param pid The PID to query
                             * @return Result
                             * @throws JSONException
                             */
                            public Result execStatus(int pid) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("pid", pid);
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/exec-status", parameters);
                            }

                        }

                        public class PVEFileRead {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEFileRead(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Reads the given file via guest agent. Is limited
                             * to 16777216 bytes.
                             *
                             * @param file The path to the file
                             * @return Result
                             * @throws JSONException
                             */
                            public Result fileRead(String file) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("file", file);
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent/file-read", parameters);
                            }

                        }

                        public class PVEFileWrite {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEFileWrite(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Writes the given file via guest agent.
                             *
                             * @param content The content to write into the
                             * file.
                             * @param file The path to the file.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result fileWrite(String content, String file) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("content", content);
                                parameters.put("file", file);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent/file-write", parameters);
                            }

                        }

                        /**
                         * Qemu Agent command index.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result index() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/agent", null);
                        }

                        /**
                         * Execute Qemu Guest Agent commands.
                         *
                         * @param command The QGA command. Enum:
                         * fsfreeze-freeze,fsfreeze-status,fsfreeze-thaw,fstrim,get-fsinfo,get-host-name,get-memory-block-info,get-memory-blocks,get-osinfo,get-time,get-timezone,get-users,get-vcpus,info,network-get-interfaces,ping,shutdown,suspend-disk,suspend-hybrid,suspend-ram
                         * @return Result
                         * @throws JSONException
                         */

                        public Result agent(String command) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("command", command);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/agent", parameters);
                        }

                    }

                    public class PVERrd {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVERrd(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Read VM RRD statistics (returns PNG)
                         *
                         * @param ds The list of datasources you want to
                         * display.
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @param cf The RRD consolidation function Enum:
                         * AVERAGE,MAX
                         * @return Result
                         * @throws JSONException
                         */
                        public Result rrd(String ds, String timeframe, String cf) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("ds", ds);
                            parameters.put("timeframe", timeframe);
                            parameters.put("cf", cf);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/rrd", parameters);
                        }

                        /**
                         * Read VM RRD statistics (returns PNG)
                         *
                         * @param ds The list of datasources you want to
                         * display.
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @return Result
                         * @throws JSONException
                         */

                        public Result rrd(String ds, String timeframe) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("ds", ds);
                            parameters.put("timeframe", timeframe);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/rrd", parameters);
                        }

                    }

                    public class PVERrddata {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVERrddata(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Read VM RRD statistics
                         *
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @param cf The RRD consolidation function Enum:
                         * AVERAGE,MAX
                         * @return Result
                         * @throws JSONException
                         */
                        public Result rrddata(String timeframe, String cf) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("timeframe", timeframe);
                            parameters.put("cf", cf);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/rrddata", parameters);
                        }

                        /**
                         * Read VM RRD statistics
                         *
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @return Result
                         * @throws JSONException
                         */

                        public Result rrddata(String timeframe) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("timeframe", timeframe);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/rrddata", parameters);
                        }

                    }

                    public class PVEConfig {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEConfig(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Get the virtual machine configuration with pending
                         * configuration changes applied. Set the 'current'
                         * parameter to get the current configuration instead.
                         *
                         * @param current Get current values (instead of pending
                         * values).
                         * @param snapshot Fetch config values from given
                         * snapshot.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmConfig(Boolean current, String snapshot) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("current", current);
                            parameters.put("snapshot", snapshot);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/config", parameters);
                        }

                        /**
                         * Get the virtual machine configuration with pending
                         * configuration changes applied. Set the 'current'
                         * parameter to get the current configuration instead.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vmConfig() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/config", null);
                        }

                        /**
                         * Set virtual machine options (asynchrounous API).
                         *
                         * @param acpi Enable/disable ACPI.
                         * @param agent Enable/disable communication with the
                         * Qemu Guest Agent and its properties.
                         * @param arch Virtual processor architecture. Defaults
                         * to the host. Enum: x86_64,aarch64
                         * @param args Arbitrary arguments passed to kvm.
                         * @param audio0 Configure a audio device, useful in
                         * combination with QXL/Spice.
                         * @param autostart Automatic restart after crash
                         * (currently ignored).
                         * @param background_delay Time to wait for the task to
                         * finish. We return 'null' if the task finish within
                         * that time.
                         * @param balloon Amount of target RAM for the VM in MB.
                         * Using zero disables the ballon driver.
                         * @param bios Select BIOS implementation. Enum:
                         * seabios,ovmf
                         * @param boot Specify guest boot order. Use the
                         * 'order=' sub-property as usage with no key or
                         * 'legacy=' is deprecated.
                         * @param bootdisk Enable booting from specified disk.
                         * Deprecated: Use 'boot: order=foo;bar' instead.
                         * @param cdrom This is an alias for option -ide2
                         * @param cicustom cloud-init: Specify custom files to
                         * replace the automatically generated ones at start.
                         * @param cipassword cloud-init: Password to assign the
                         * user. Using this is generally not recommended. Use
                         * ssh keys instead. Also note that older cloud-init
                         * versions do not support hashed passwords.
                         * @param citype Specifies the cloud-init configuration
                         * format. The default depends on the configured
                         * operating system type (`ostype`. We use the `nocloud`
                         * format for Linux, and `configdrive2` for windows.
                         * Enum: configdrive2,nocloud,opennebula
                         * @param ciuser cloud-init: User name to change ssh
                         * keys and password for instead of the image's
                         * configured default user.
                         * @param cores The number of cores per socket.
                         * @param cpu Emulated CPU type.
                         * @param cpulimit Limit of CPU usage.
                         * @param cpuunits CPU weight for a VM, will be clamped
                         * to [1, 10000] in cgroup v2.
                         * @param delete A list of settings you want to delete.
                         * @param description Description for the VM. Shown in
                         * the web-interface VM's summary. This is saved as
                         * comment inside the configuration file.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param efidisk0 Configure a Disk for storing EFI
                         * vars. Use the special syntax STORAGE_ID:SIZE_IN_GiB
                         * to allocate a new volume. Note that SIZE_IN_GiB is
                         * ignored here and that the default EFI vars are copied
                         * to the volume instead.
                         * @param force Force physical removal. Without this, we
                         * simple remove the disk from the config file and
                         * create an additional configuration entry called
                         * 'unused[n]', which contains the volume ID. Unlink of
                         * unused[n] always cause physical removal.
                         * @param freeze Freeze CPU at startup (use 'c' monitor
                         * command to start execution).
                         * @param hookscript Script that will be executed during
                         * various steps in the vms lifetime.
                         * @param hostpciN Map host PCI devices into guest.
                         * @param hotplug Selectively enable hotplug features.
                         * This is a comma separated list of hotplug features:
                         * 'network', 'disk', 'cpu', 'memory' and 'usb'. Use '0'
                         * to disable hotplug completely. Using '1' as value is
                         * an alias for the default `network,disk,usb`.
                         * @param hugepages Enable/disable hugepages memory.
                         * Enum: any,2,1024
                         * @param ideN Use volume as IDE hard disk or CD-ROM (n
                         * is 0 to 3). Use the special syntax
                         * STORAGE_ID:SIZE_IN_GiB to allocate a new volume.
                         * @param ipconfigN cloud-init: Specify IP addresses and
                         * gateways for the corresponding interface. IP
                         * addresses use CIDR notation, gateways are optional
                         * but need an IP of the same type specified. The
                         * special string 'dhcp' can be used for IP addresses to
                         * use DHCP, in which case no explicit gateway should be
                         * provided. For IPv6 the special string 'auto' can be
                         * used to use stateless autoconfiguration. This
                         * requires cloud-init 19.4 or newer. If cloud-init is
                         * enabled and neither an IPv4 nor an IPv6 address is
                         * specified, it defaults to using dhcp on IPv4.
                         * @param ivshmem Inter-VM shared memory. Useful for
                         * direct communication between VMs, or to the host.
                         * @param keephugepages Use together with hugepages. If
                         * enabled, hugepages will not not be deleted after VM
                         * shutdown and can be used for subsequent starts.
                         * @param keyboard Keyboard layout for VNC server. The
                         * default is read from the'/etc/pve/datacenter.cfg'
                         * configuration file. It should not be necessary to set
                         * it. Enum:
                         * de,de-ch,da,en-gb,en-us,es,fi,fr,fr-be,fr-ca,fr-ch,hu,is,it,ja,lt,mk,nl,no,pl,pt,pt-br,sv,sl,tr
                         * @param kvm Enable/disable KVM hardware
                         * virtualization.
                         * @param localtime Set the real time clock (RTC) to
                         * local time. This is enabled by default if the
                         * `ostype` indicates a Microsoft Windows OS.
                         * @param lock_ Lock/unlock the VM. Enum:
                         * backup,clone,create,migrate,rollback,snapshot,snapshot-delete,suspending,suspended
                         * @param machine Specifies the Qemu machine type.
                         * @param memory Amount of RAM for the VM in MB. This is
                         * the maximum available memory when you use the balloon
                         * device.
                         * @param migrate_downtime Set maximum tolerated
                         * downtime (in seconds) for migrations.
                         * @param migrate_speed Set maximum speed (in MB/s) for
                         * migrations. Value 0 is no limit.
                         * @param name Set a name for the VM. Only used on the
                         * configuration web interface.
                         * @param nameserver cloud-init: Sets DNS server IP
                         * address for a container. Create will' .'
                         * automatically use the setting from the host if
                         * neither searchdomain nor nameserver' .' are set.
                         * @param netN Specify network devices.
                         * @param numa Enable/disable NUMA.
                         * @param numaN NUMA topology.
                         * @param onboot Specifies whether a VM will be started
                         * during system bootup.
                         * @param ostype Specify guest operating system. Enum:
                         * other,wxp,w2k,w2k3,w2k8,wvista,win7,win8,win10,win11,l24,l26,solaris
                         * @param parallelN Map host parallel devices (n is 0 to
                         * 2).
                         * @param protection Sets the protection flag of the VM.
                         * This will disable the remove VM and remove disk
                         * operations.
                         * @param reboot Allow reboot. If set to '0' the VM exit
                         * on reboot.
                         * @param revert Revert a pending change.
                         * @param rng0 Configure a VirtIO-based Random Number
                         * Generator.
                         * @param sataN Use volume as SATA hard disk or CD-ROM
                         * (n is 0 to 5). Use the special syntax
                         * STORAGE_ID:SIZE_IN_GiB to allocate a new volume.
                         * @param scsiN Use volume as SCSI hard disk or CD-ROM
                         * (n is 0 to 30). Use the special syntax
                         * STORAGE_ID:SIZE_IN_GiB to allocate a new volume.
                         * @param scsihw SCSI controller model Enum:
                         * lsi,lsi53c810,virtio-scsi-pci,virtio-scsi-single,megasas,pvscsi
                         * @param searchdomain cloud-init: Sets DNS search
                         * domains for a container. Create will' .'
                         * automatically use the setting from the host if
                         * neither searchdomain nor nameserver' .' are set.
                         * @param serialN Create a serial device inside the VM
                         * (n is 0 to 3)
                         * @param shares Amount of memory shares for
                         * auto-ballooning. The larger the number is, the more
                         * memory this VM gets. Number is relative to weights of
                         * all other running VMs. Using zero disables
                         * auto-ballooning. Auto-ballooning is done by pvestatd.
                         * @param skiplock Ignore locks - only root is allowed
                         * to use this option.
                         * @param smbios1 Specify SMBIOS type 1 fields.
                         * @param smp The number of CPUs. Please use option
                         * -sockets instead.
                         * @param sockets The number of CPU sockets.
                         * @param spice_enhancements Configure additional
                         * enhancements for SPICE.
                         * @param sshkeys cloud-init: Setup public SSH keys (one
                         * key per line, OpenSSH format).
                         * @param startdate Set the initial date of the real
                         * time clock. Valid format for date are:'now' or
                         * '2006-06-17T16:01:21' or '2006-06-17'.
                         * @param startup Startup and shutdown behavior. Order
                         * is a non-negative number defining the general startup
                         * order. Shutdown in done with reverse ordering.
                         * Additionally you can set the 'up' or 'down' delay in
                         * seconds, which specifies a delay to wait before the
                         * next VM is started or stopped.
                         * @param tablet Enable/disable the USB tablet device.
                         * @param tags Tags of the VM. This is only meta
                         * information.
                         * @param tdf Enable/disable time drift fix.
                         * @param template Enable/disable Template.
                         * @param tpmstate0 Configure a Disk for storing TPM
                         * state. Use the special syntax STORAGE_ID:SIZE_IN_GiB
                         * to allocate a new volume. Note that SIZE_IN_GiB is
                         * ignored here and that the default size of 4 MiB will
                         * always be used instead. The format is also fixed to
                         * 'raw'.
                         * @param unusedN Reference to unused volumes. This is
                         * used internally, and should not be modified manually.
                         * @param usbN Configure an USB device (n is 0 to 4).
                         * @param vcpus Number of hotplugged vcpus.
                         * @param vga Configure the VGA hardware.
                         * @param virtioN Use volume as VIRTIO hard disk (n is 0
                         * to 15). Use the special syntax STORAGE_ID:SIZE_IN_GiB
                         * to allocate a new volume.
                         * @param vmgenid Set VM Generation ID. Use '1' to
                         * autogenerate on create or update, pass '0' to disable
                         * explicitly.
                         * @param vmstatestorage Default storage for VM state
                         * volumes/files.
                         * @param watchdog Create a virtual hardware watchdog
                         * device.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateVmAsync(Boolean acpi, String agent, String arch, String args, String audio0, Boolean autostart, Integer background_delay, Integer balloon, String bios, String boot, String bootdisk, String cdrom, String cicustom, String cipassword, String citype, String ciuser, Integer cores, String cpu, Float cpulimit, Integer cpuunits, String delete, String description, String digest, String efidisk0, Boolean force, Boolean freeze, String hookscript, Map<Integer, String> hostpciN, String hotplug, String hugepages, Map<Integer, String> ideN, Map<Integer, String> ipconfigN, String ivshmem, Boolean keephugepages, String keyboard, Boolean kvm, Boolean localtime, String lock_, String machine, Integer memory, Float migrate_downtime, Integer migrate_speed, String name, String nameserver, Map<Integer, String> netN, Boolean numa, Map<Integer, String> numaN, Boolean onboot, String ostype, Map<Integer, String> parallelN, Boolean protection, Boolean reboot, String revert, String rng0, Map<Integer, String> sataN, Map<Integer, String> scsiN, String scsihw, String searchdomain, Map<Integer, String> serialN, Integer shares, Boolean skiplock, String smbios1, Integer smp, Integer sockets, String spice_enhancements, String sshkeys, String startdate, String startup, Boolean tablet, String tags, Boolean tdf, Boolean template, String tpmstate0, Map<Integer, String> unusedN, Map<Integer, String> usbN, Integer vcpus, String vga, Map<Integer, String> virtioN, String vmgenid, String vmstatestorage, String watchdog) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("acpi", acpi);
                            parameters.put("agent", agent);
                            parameters.put("arch", arch);
                            parameters.put("args", args);
                            parameters.put("audio0", audio0);
                            parameters.put("autostart", autostart);
                            parameters.put("background_delay", background_delay);
                            parameters.put("balloon", balloon);
                            parameters.put("bios", bios);
                            parameters.put("boot", boot);
                            parameters.put("bootdisk", bootdisk);
                            parameters.put("cdrom", cdrom);
                            parameters.put("cicustom", cicustom);
                            parameters.put("cipassword", cipassword);
                            parameters.put("citype", citype);
                            parameters.put("ciuser", ciuser);
                            parameters.put("cores", cores);
                            parameters.put("cpu", cpu);
                            parameters.put("cpulimit", cpulimit);
                            parameters.put("cpuunits", cpuunits);
                            parameters.put("delete", delete);
                            parameters.put("description", description);
                            parameters.put("digest", digest);
                            parameters.put("efidisk0", efidisk0);
                            parameters.put("force", force);
                            parameters.put("freeze", freeze);
                            parameters.put("hookscript", hookscript);
                            parameters.put("hotplug", hotplug);
                            parameters.put("hugepages", hugepages);
                            parameters.put("ivshmem", ivshmem);
                            parameters.put("keephugepages", keephugepages);
                            parameters.put("keyboard", keyboard);
                            parameters.put("kvm", kvm);
                            parameters.put("localtime", localtime);
                            parameters.put("lock", lock_);
                            parameters.put("machine", machine);
                            parameters.put("memory", memory);
                            parameters.put("migrate_downtime", migrate_downtime);
                            parameters.put("migrate_speed", migrate_speed);
                            parameters.put("name", name);
                            parameters.put("nameserver", nameserver);
                            parameters.put("numa", numa);
                            parameters.put("onboot", onboot);
                            parameters.put("ostype", ostype);
                            parameters.put("protection", protection);
                            parameters.put("reboot", reboot);
                            parameters.put("revert", revert);
                            parameters.put("rng0", rng0);
                            parameters.put("scsihw", scsihw);
                            parameters.put("searchdomain", searchdomain);
                            parameters.put("shares", shares);
                            parameters.put("skiplock", skiplock);
                            parameters.put("smbios1", smbios1);
                            parameters.put("smp", smp);
                            parameters.put("sockets", sockets);
                            parameters.put("spice_enhancements", spice_enhancements);
                            parameters.put("sshkeys", sshkeys);
                            parameters.put("startdate", startdate);
                            parameters.put("startup", startup);
                            parameters.put("tablet", tablet);
                            parameters.put("tags", tags);
                            parameters.put("tdf", tdf);
                            parameters.put("template", template);
                            parameters.put("tpmstate0", tpmstate0);
                            parameters.put("vcpus", vcpus);
                            parameters.put("vga", vga);
                            parameters.put("vmgenid", vmgenid);
                            parameters.put("vmstatestorage", vmstatestorage);
                            parameters.put("watchdog", watchdog);
                            addIndexedParameter(parameters, "hostpci", hostpciN);
                            addIndexedParameter(parameters, "ide", ideN);
                            addIndexedParameter(parameters, "ipconfig", ipconfigN);
                            addIndexedParameter(parameters, "net", netN);
                            addIndexedParameter(parameters, "numa", numaN);
                            addIndexedParameter(parameters, "parallel", parallelN);
                            addIndexedParameter(parameters, "sata", sataN);
                            addIndexedParameter(parameters, "scsi", scsiN);
                            addIndexedParameter(parameters, "serial", serialN);
                            addIndexedParameter(parameters, "unused", unusedN);
                            addIndexedParameter(parameters, "usb", usbN);
                            addIndexedParameter(parameters, "virtio", virtioN);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/config", parameters);
                        }

                        /**
                         * Set virtual machine options (asynchrounous API).
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateVmAsync() throws JSONException {
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/config", null);
                        }

                        /**
                         * Set virtual machine options (synchrounous API) - You
                         * should consider using the POST method instead for any
                         * actions involving hotplug or storage allocation.
                         *
                         * @param acpi Enable/disable ACPI.
                         * @param agent Enable/disable communication with the
                         * Qemu Guest Agent and its properties.
                         * @param arch Virtual processor architecture. Defaults
                         * to the host. Enum: x86_64,aarch64
                         * @param args Arbitrary arguments passed to kvm.
                         * @param audio0 Configure a audio device, useful in
                         * combination with QXL/Spice.
                         * @param autostart Automatic restart after crash
                         * (currently ignored).
                         * @param balloon Amount of target RAM for the VM in MB.
                         * Using zero disables the ballon driver.
                         * @param bios Select BIOS implementation. Enum:
                         * seabios,ovmf
                         * @param boot Specify guest boot order. Use the
                         * 'order=' sub-property as usage with no key or
                         * 'legacy=' is deprecated.
                         * @param bootdisk Enable booting from specified disk.
                         * Deprecated: Use 'boot: order=foo;bar' instead.
                         * @param cdrom This is an alias for option -ide2
                         * @param cicustom cloud-init: Specify custom files to
                         * replace the automatically generated ones at start.
                         * @param cipassword cloud-init: Password to assign the
                         * user. Using this is generally not recommended. Use
                         * ssh keys instead. Also note that older cloud-init
                         * versions do not support hashed passwords.
                         * @param citype Specifies the cloud-init configuration
                         * format. The default depends on the configured
                         * operating system type (`ostype`. We use the `nocloud`
                         * format for Linux, and `configdrive2` for windows.
                         * Enum: configdrive2,nocloud,opennebula
                         * @param ciuser cloud-init: User name to change ssh
                         * keys and password for instead of the image's
                         * configured default user.
                         * @param cores The number of cores per socket.
                         * @param cpu Emulated CPU type.
                         * @param cpulimit Limit of CPU usage.
                         * @param cpuunits CPU weight for a VM, will be clamped
                         * to [1, 10000] in cgroup v2.
                         * @param delete A list of settings you want to delete.
                         * @param description Description for the VM. Shown in
                         * the web-interface VM's summary. This is saved as
                         * comment inside the configuration file.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param efidisk0 Configure a Disk for storing EFI
                         * vars. Use the special syntax STORAGE_ID:SIZE_IN_GiB
                         * to allocate a new volume. Note that SIZE_IN_GiB is
                         * ignored here and that the default EFI vars are copied
                         * to the volume instead.
                         * @param force Force physical removal. Without this, we
                         * simple remove the disk from the config file and
                         * create an additional configuration entry called
                         * 'unused[n]', which contains the volume ID. Unlink of
                         * unused[n] always cause physical removal.
                         * @param freeze Freeze CPU at startup (use 'c' monitor
                         * command to start execution).
                         * @param hookscript Script that will be executed during
                         * various steps in the vms lifetime.
                         * @param hostpciN Map host PCI devices into guest.
                         * @param hotplug Selectively enable hotplug features.
                         * This is a comma separated list of hotplug features:
                         * 'network', 'disk', 'cpu', 'memory' and 'usb'. Use '0'
                         * to disable hotplug completely. Using '1' as value is
                         * an alias for the default `network,disk,usb`.
                         * @param hugepages Enable/disable hugepages memory.
                         * Enum: any,2,1024
                         * @param ideN Use volume as IDE hard disk or CD-ROM (n
                         * is 0 to 3). Use the special syntax
                         * STORAGE_ID:SIZE_IN_GiB to allocate a new volume.
                         * @param ipconfigN cloud-init: Specify IP addresses and
                         * gateways for the corresponding interface. IP
                         * addresses use CIDR notation, gateways are optional
                         * but need an IP of the same type specified. The
                         * special string 'dhcp' can be used for IP addresses to
                         * use DHCP, in which case no explicit gateway should be
                         * provided. For IPv6 the special string 'auto' can be
                         * used to use stateless autoconfiguration. This
                         * requires cloud-init 19.4 or newer. If cloud-init is
                         * enabled and neither an IPv4 nor an IPv6 address is
                         * specified, it defaults to using dhcp on IPv4.
                         * @param ivshmem Inter-VM shared memory. Useful for
                         * direct communication between VMs, or to the host.
                         * @param keephugepages Use together with hugepages. If
                         * enabled, hugepages will not not be deleted after VM
                         * shutdown and can be used for subsequent starts.
                         * @param keyboard Keyboard layout for VNC server. The
                         * default is read from the'/etc/pve/datacenter.cfg'
                         * configuration file. It should not be necessary to set
                         * it. Enum:
                         * de,de-ch,da,en-gb,en-us,es,fi,fr,fr-be,fr-ca,fr-ch,hu,is,it,ja,lt,mk,nl,no,pl,pt,pt-br,sv,sl,tr
                         * @param kvm Enable/disable KVM hardware
                         * virtualization.
                         * @param localtime Set the real time clock (RTC) to
                         * local time. This is enabled by default if the
                         * `ostype` indicates a Microsoft Windows OS.
                         * @param lock_ Lock/unlock the VM. Enum:
                         * backup,clone,create,migrate,rollback,snapshot,snapshot-delete,suspending,suspended
                         * @param machine Specifies the Qemu machine type.
                         * @param memory Amount of RAM for the VM in MB. This is
                         * the maximum available memory when you use the balloon
                         * device.
                         * @param migrate_downtime Set maximum tolerated
                         * downtime (in seconds) for migrations.
                         * @param migrate_speed Set maximum speed (in MB/s) for
                         * migrations. Value 0 is no limit.
                         * @param name Set a name for the VM. Only used on the
                         * configuration web interface.
                         * @param nameserver cloud-init: Sets DNS server IP
                         * address for a container. Create will' .'
                         * automatically use the setting from the host if
                         * neither searchdomain nor nameserver' .' are set.
                         * @param netN Specify network devices.
                         * @param numa Enable/disable NUMA.
                         * @param numaN NUMA topology.
                         * @param onboot Specifies whether a VM will be started
                         * during system bootup.
                         * @param ostype Specify guest operating system. Enum:
                         * other,wxp,w2k,w2k3,w2k8,wvista,win7,win8,win10,win11,l24,l26,solaris
                         * @param parallelN Map host parallel devices (n is 0 to
                         * 2).
                         * @param protection Sets the protection flag of the VM.
                         * This will disable the remove VM and remove disk
                         * operations.
                         * @param reboot Allow reboot. If set to '0' the VM exit
                         * on reboot.
                         * @param revert Revert a pending change.
                         * @param rng0 Configure a VirtIO-based Random Number
                         * Generator.
                         * @param sataN Use volume as SATA hard disk or CD-ROM
                         * (n is 0 to 5). Use the special syntax
                         * STORAGE_ID:SIZE_IN_GiB to allocate a new volume.
                         * @param scsiN Use volume as SCSI hard disk or CD-ROM
                         * (n is 0 to 30). Use the special syntax
                         * STORAGE_ID:SIZE_IN_GiB to allocate a new volume.
                         * @param scsihw SCSI controller model Enum:
                         * lsi,lsi53c810,virtio-scsi-pci,virtio-scsi-single,megasas,pvscsi
                         * @param searchdomain cloud-init: Sets DNS search
                         * domains for a container. Create will' .'
                         * automatically use the setting from the host if
                         * neither searchdomain nor nameserver' .' are set.
                         * @param serialN Create a serial device inside the VM
                         * (n is 0 to 3)
                         * @param shares Amount of memory shares for
                         * auto-ballooning. The larger the number is, the more
                         * memory this VM gets. Number is relative to weights of
                         * all other running VMs. Using zero disables
                         * auto-ballooning. Auto-ballooning is done by pvestatd.
                         * @param skiplock Ignore locks - only root is allowed
                         * to use this option.
                         * @param smbios1 Specify SMBIOS type 1 fields.
                         * @param smp The number of CPUs. Please use option
                         * -sockets instead.
                         * @param sockets The number of CPU sockets.
                         * @param spice_enhancements Configure additional
                         * enhancements for SPICE.
                         * @param sshkeys cloud-init: Setup public SSH keys (one
                         * key per line, OpenSSH format).
                         * @param startdate Set the initial date of the real
                         * time clock. Valid format for date are:'now' or
                         * '2006-06-17T16:01:21' or '2006-06-17'.
                         * @param startup Startup and shutdown behavior. Order
                         * is a non-negative number defining the general startup
                         * order. Shutdown in done with reverse ordering.
                         * Additionally you can set the 'up' or 'down' delay in
                         * seconds, which specifies a delay to wait before the
                         * next VM is started or stopped.
                         * @param tablet Enable/disable the USB tablet device.
                         * @param tags Tags of the VM. This is only meta
                         * information.
                         * @param tdf Enable/disable time drift fix.
                         * @param template Enable/disable Template.
                         * @param tpmstate0 Configure a Disk for storing TPM
                         * state. Use the special syntax STORAGE_ID:SIZE_IN_GiB
                         * to allocate a new volume. Note that SIZE_IN_GiB is
                         * ignored here and that the default size of 4 MiB will
                         * always be used instead. The format is also fixed to
                         * 'raw'.
                         * @param unusedN Reference to unused volumes. This is
                         * used internally, and should not be modified manually.
                         * @param usbN Configure an USB device (n is 0 to 4).
                         * @param vcpus Number of hotplugged vcpus.
                         * @param vga Configure the VGA hardware.
                         * @param virtioN Use volume as VIRTIO hard disk (n is 0
                         * to 15). Use the special syntax STORAGE_ID:SIZE_IN_GiB
                         * to allocate a new volume.
                         * @param vmgenid Set VM Generation ID. Use '1' to
                         * autogenerate on create or update, pass '0' to disable
                         * explicitly.
                         * @param vmstatestorage Default storage for VM state
                         * volumes/files.
                         * @param watchdog Create a virtual hardware watchdog
                         * device.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateVm(Boolean acpi, String agent, String arch, String args, String audio0, Boolean autostart, Integer balloon, String bios, String boot, String bootdisk, String cdrom, String cicustom, String cipassword, String citype, String ciuser, Integer cores, String cpu, Float cpulimit, Integer cpuunits, String delete, String description, String digest, String efidisk0, Boolean force, Boolean freeze, String hookscript, Map<Integer, String> hostpciN, String hotplug, String hugepages, Map<Integer, String> ideN, Map<Integer, String> ipconfigN, String ivshmem, Boolean keephugepages, String keyboard, Boolean kvm, Boolean localtime, String lock_, String machine, Integer memory, Float migrate_downtime, Integer migrate_speed, String name, String nameserver, Map<Integer, String> netN, Boolean numa, Map<Integer, String> numaN, Boolean onboot, String ostype, Map<Integer, String> parallelN, Boolean protection, Boolean reboot, String revert, String rng0, Map<Integer, String> sataN, Map<Integer, String> scsiN, String scsihw, String searchdomain, Map<Integer, String> serialN, Integer shares, Boolean skiplock, String smbios1, Integer smp, Integer sockets, String spice_enhancements, String sshkeys, String startdate, String startup, Boolean tablet, String tags, Boolean tdf, Boolean template, String tpmstate0, Map<Integer, String> unusedN, Map<Integer, String> usbN, Integer vcpus, String vga, Map<Integer, String> virtioN, String vmgenid, String vmstatestorage, String watchdog) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("acpi", acpi);
                            parameters.put("agent", agent);
                            parameters.put("arch", arch);
                            parameters.put("args", args);
                            parameters.put("audio0", audio0);
                            parameters.put("autostart", autostart);
                            parameters.put("balloon", balloon);
                            parameters.put("bios", bios);
                            parameters.put("boot", boot);
                            parameters.put("bootdisk", bootdisk);
                            parameters.put("cdrom", cdrom);
                            parameters.put("cicustom", cicustom);
                            parameters.put("cipassword", cipassword);
                            parameters.put("citype", citype);
                            parameters.put("ciuser", ciuser);
                            parameters.put("cores", cores);
                            parameters.put("cpu", cpu);
                            parameters.put("cpulimit", cpulimit);
                            parameters.put("cpuunits", cpuunits);
                            parameters.put("delete", delete);
                            parameters.put("description", description);
                            parameters.put("digest", digest);
                            parameters.put("efidisk0", efidisk0);
                            parameters.put("force", force);
                            parameters.put("freeze", freeze);
                            parameters.put("hookscript", hookscript);
                            parameters.put("hotplug", hotplug);
                            parameters.put("hugepages", hugepages);
                            parameters.put("ivshmem", ivshmem);
                            parameters.put("keephugepages", keephugepages);
                            parameters.put("keyboard", keyboard);
                            parameters.put("kvm", kvm);
                            parameters.put("localtime", localtime);
                            parameters.put("lock", lock_);
                            parameters.put("machine", machine);
                            parameters.put("memory", memory);
                            parameters.put("migrate_downtime", migrate_downtime);
                            parameters.put("migrate_speed", migrate_speed);
                            parameters.put("name", name);
                            parameters.put("nameserver", nameserver);
                            parameters.put("numa", numa);
                            parameters.put("onboot", onboot);
                            parameters.put("ostype", ostype);
                            parameters.put("protection", protection);
                            parameters.put("reboot", reboot);
                            parameters.put("revert", revert);
                            parameters.put("rng0", rng0);
                            parameters.put("scsihw", scsihw);
                            parameters.put("searchdomain", searchdomain);
                            parameters.put("shares", shares);
                            parameters.put("skiplock", skiplock);
                            parameters.put("smbios1", smbios1);
                            parameters.put("smp", smp);
                            parameters.put("sockets", sockets);
                            parameters.put("spice_enhancements", spice_enhancements);
                            parameters.put("sshkeys", sshkeys);
                            parameters.put("startdate", startdate);
                            parameters.put("startup", startup);
                            parameters.put("tablet", tablet);
                            parameters.put("tags", tags);
                            parameters.put("tdf", tdf);
                            parameters.put("template", template);
                            parameters.put("tpmstate0", tpmstate0);
                            parameters.put("vcpus", vcpus);
                            parameters.put("vga", vga);
                            parameters.put("vmgenid", vmgenid);
                            parameters.put("vmstatestorage", vmstatestorage);
                            parameters.put("watchdog", watchdog);
                            addIndexedParameter(parameters, "hostpci", hostpciN);
                            addIndexedParameter(parameters, "ide", ideN);
                            addIndexedParameter(parameters, "ipconfig", ipconfigN);
                            addIndexedParameter(parameters, "net", netN);
                            addIndexedParameter(parameters, "numa", numaN);
                            addIndexedParameter(parameters, "parallel", parallelN);
                            addIndexedParameter(parameters, "sata", sataN);
                            addIndexedParameter(parameters, "scsi", scsiN);
                            addIndexedParameter(parameters, "serial", serialN);
                            addIndexedParameter(parameters, "unused", unusedN);
                            addIndexedParameter(parameters, "usb", usbN);
                            addIndexedParameter(parameters, "virtio", virtioN);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/config", parameters);
                        }

                        /**
                         * Set virtual machine options (synchrounous API) - You
                         * should consider using the POST method instead for any
                         * actions involving hotplug or storage allocation.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateVm() throws JSONException {
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/config", null);
                        }

                    }

                    public class PVEPending {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEPending(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Get the virtual machine configuration with both
                         * current and pending values.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmPending() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/pending", null);
                        }

                    }

                    public class PVEUnlink {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEUnlink(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Unlink/delete disk images.
                         *
                         * @param idlist A list of disk IDs you want to delete.
                         * @param force Force physical removal. Without this, we
                         * simple remove the disk from the config file and
                         * create an additional configuration entry called
                         * 'unused[n]', which contains the volume ID. Unlink of
                         * unused[n] always cause physical removal.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result unlink(String idlist, Boolean force) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("idlist", idlist);
                            parameters.put("force", force);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/unlink", parameters);
                        }

                        /**
                         * Unlink/delete disk images.
                         *
                         * @param idlist A list of disk IDs you want to delete.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result unlink(String idlist) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("idlist", idlist);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/unlink", parameters);
                        }

                    }

                    public class PVEVncproxy {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEVncproxy(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Creates a TCP VNC proxy connections.
                         *
                         * @param generate_password Generates a random password
                         * to be used as ticket instead of the API ticket.
                         * @param websocket starts websockify instead of
                         * vncproxy
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vncproxy(Boolean generate_password, Boolean websocket) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("generate-password", generate_password);
                            parameters.put("websocket", websocket);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/vncproxy", parameters);
                        }

                        /**
                         * Creates a TCP VNC proxy connections.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vncproxy() throws JSONException {
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/vncproxy", null);
                        }

                    }

                    public class PVETermproxy {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVETermproxy(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Creates a TCP proxy connections.
                         *
                         * @param serial opens a serial terminal (defaults to
                         * display) Enum: serial0,serial1,serial2,serial3
                         * @return Result
                         * @throws JSONException
                         */
                        public Result termproxy(String serial) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("serial", serial);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/termproxy", parameters);
                        }

                        /**
                         * Creates a TCP proxy connections.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result termproxy() throws JSONException {
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/termproxy", null);
                        }

                    }

                    public class PVEVncwebsocket {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEVncwebsocket(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Opens a weksocket for VNC traffic.
                         *
                         * @param port Port number returned by previous vncproxy
                         * call.
                         * @param vncticket Ticket from previous call to
                         * vncproxy.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vncwebsocket(int port, String vncticket) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("port", port);
                            parameters.put("vncticket", vncticket);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/vncwebsocket", parameters);
                        }

                    }

                    public class PVESpiceproxy {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVESpiceproxy(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Returns a SPICE configuration to connect to the VM.
                         *
                         * @param proxy SPICE proxy server. This can be used by
                         * the client to specify the proxy server. All nodes in
                         * a cluster runs 'spiceproxy', so it is up to the
                         * client to choose one. By default, we return the node
                         * where the VM is currently running. As reasonable
                         * setting is to use same node you use to connect to the
                         * API (This is window.location.hostname for the JS
                         * GUI).
                         * @return Result
                         * @throws JSONException
                         */
                        public Result spiceproxy(String proxy) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("proxy", proxy);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/spiceproxy", parameters);
                        }

                        /**
                         * Returns a SPICE configuration to connect to the VM.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result spiceproxy() throws JSONException {
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/spiceproxy", null);
                        }

                    }

                    public class PVEStatus {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEStatus(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        private PVECurrent _current;

                        public PVECurrent getCurrent() {
                            return _current == null ? (_current = new PVECurrent(_client, _node, _vmid)) : _current;
                        }
                        private PVEStart _start;

                        public PVEStart getStart() {
                            return _start == null ? (_start = new PVEStart(_client, _node, _vmid)) : _start;
                        }
                        private PVEStop _stop;

                        public PVEStop getStop() {
                            return _stop == null ? (_stop = new PVEStop(_client, _node, _vmid)) : _stop;
                        }
                        private PVEReset _reset;

                        public PVEReset getReset() {
                            return _reset == null ? (_reset = new PVEReset(_client, _node, _vmid)) : _reset;
                        }
                        private PVEShutdown _shutdown;

                        public PVEShutdown getShutdown() {
                            return _shutdown == null ? (_shutdown = new PVEShutdown(_client, _node, _vmid)) : _shutdown;
                        }
                        private PVEReboot _reboot;

                        public PVEReboot getReboot() {
                            return _reboot == null ? (_reboot = new PVEReboot(_client, _node, _vmid)) : _reboot;
                        }
                        private PVESuspend _suspend;

                        public PVESuspend getSuspend() {
                            return _suspend == null ? (_suspend = new PVESuspend(_client, _node, _vmid)) : _suspend;
                        }
                        private PVEResume _resume;

                        public PVEResume getResume() {
                            return _resume == null ? (_resume = new PVEResume(_client, _node, _vmid)) : _resume;
                        }

                        public class PVECurrent {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVECurrent(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Get virtual machine status.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmStatus() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/status/current", null);
                            }

                        }

                        public class PVEStart {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEStart(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Start virtual machine.
                             *
                             * @param force_cpu Override QEMU's -cpu argument
                             * with the given string.
                             * @param machine Specifies the Qemu machine type.
                             * @param migratedfrom The cluster node name.
                             * @param migration_network CIDR of the (sub)
                             * network that is used for migration.
                             * @param migration_type Migration traffic is
                             * encrypted using an SSH tunnel by default. On
                             * secure, completely private networks this can be
                             * disabled to increase performance. Enum:
                             * secure,insecure
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @param stateuri Some command save/restore state
                             * from this location.
                             * @param targetstorage Mapping from source to
                             * target storages. Providing only a single storage
                             * ID maps all source storages to that storage.
                             * Providing the special value '1' will map each
                             * source storage to itself.
                             * @param timeout Wait maximal timeout seconds.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmStart(String force_cpu, String machine, String migratedfrom, String migration_network, String migration_type, Boolean skiplock, String stateuri, String targetstorage, Integer timeout) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("force-cpu", force_cpu);
                                parameters.put("machine", machine);
                                parameters.put("migratedfrom", migratedfrom);
                                parameters.put("migration_network", migration_network);
                                parameters.put("migration_type", migration_type);
                                parameters.put("skiplock", skiplock);
                                parameters.put("stateuri", stateuri);
                                parameters.put("targetstorage", targetstorage);
                                parameters.put("timeout", timeout);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/start", parameters);
                            }

                            /**
                             * Start virtual machine.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmStart() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/start", null);
                            }

                        }

                        public class PVEStop {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEStop(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Stop virtual machine. The qemu process will exit
                             * immediately. Thisis akin to pulling the power
                             * plug of a running computer and may damage the VM
                             * data
                             *
                             * @param keepActive Do not deactivate storage
                             * volumes.
                             * @param migratedfrom The cluster node name.
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @param timeout Wait maximal timeout seconds.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmStop(Boolean keepActive, String migratedfrom, Boolean skiplock, Integer timeout) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("keepActive", keepActive);
                                parameters.put("migratedfrom", migratedfrom);
                                parameters.put("skiplock", skiplock);
                                parameters.put("timeout", timeout);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/stop", parameters);
                            }

                            /**
                             * Stop virtual machine. The qemu process will exit
                             * immediately. Thisis akin to pulling the power
                             * plug of a running computer and may damage the VM
                             * data
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmStop() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/stop", null);
                            }

                        }

                        public class PVEReset {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEReset(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Reset virtual machine.
                             *
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmReset(Boolean skiplock) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("skiplock", skiplock);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/reset", parameters);
                            }

                            /**
                             * Reset virtual machine.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmReset() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/reset", null);
                            }

                        }

                        public class PVEShutdown {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEShutdown(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Shutdown virtual machine. This is similar to
                             * pressing the power button on a physical
                             * machine.This will send an ACPI event for the
                             * guest OS, which should then proceed to a clean
                             * shutdown.
                             *
                             * @param forceStop Make sure the VM stops.
                             * @param keepActive Do not deactivate storage
                             * volumes.
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @param timeout Wait maximal timeout seconds.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmShutdown(Boolean forceStop, Boolean keepActive, Boolean skiplock, Integer timeout) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("forceStop", forceStop);
                                parameters.put("keepActive", keepActive);
                                parameters.put("skiplock", skiplock);
                                parameters.put("timeout", timeout);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/shutdown", parameters);
                            }

                            /**
                             * Shutdown virtual machine. This is similar to
                             * pressing the power button on a physical
                             * machine.This will send an ACPI event for the
                             * guest OS, which should then proceed to a clean
                             * shutdown.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmShutdown() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/shutdown", null);
                            }

                        }

                        public class PVEReboot {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEReboot(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Reboot the VM by shutting it down, and starting
                             * it again. Applies pending changes.
                             *
                             * @param timeout Wait maximal timeout seconds for
                             * the shutdown.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmReboot(Integer timeout) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("timeout", timeout);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/reboot", parameters);
                            }

                            /**
                             * Reboot the VM by shutting it down, and starting
                             * it again. Applies pending changes.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmReboot() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/reboot", null);
                            }

                        }

                        public class PVESuspend {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVESuspend(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Suspend virtual machine.
                             *
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @param statestorage The storage for the VM state
                             * @param todisk If set, suspends the VM to disk.
                             * Will be resumed on next VM start.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmSuspend(Boolean skiplock, String statestorage, Boolean todisk) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("skiplock", skiplock);
                                parameters.put("statestorage", statestorage);
                                parameters.put("todisk", todisk);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/suspend", parameters);
                            }

                            /**
                             * Suspend virtual machine.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmSuspend() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/suspend", null);
                            }

                        }

                        public class PVEResume {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEResume(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Resume virtual machine.
                             *
                             * @param nocheck
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmResume(Boolean nocheck, Boolean skiplock) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("nocheck", nocheck);
                                parameters.put("skiplock", skiplock);
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/resume", parameters);
                            }

                            /**
                             * Resume virtual machine.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmResume() throws JSONException {
                                return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/status/resume", null);
                            }

                        }

                        /**
                         * Directory index
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmcmdidx() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/status", null);
                        }

                    }

                    public class PVESendkey {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVESendkey(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Send key event to virtual machine.
                         *
                         * @param key The key (qemu monitor encoding).
                         * @param skiplock Ignore locks - only root is allowed
                         * to use this option.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmSendkey(String key, Boolean skiplock) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("key", key);
                            parameters.put("skiplock", skiplock);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/sendkey", parameters);
                        }

                        /**
                         * Send key event to virtual machine.
                         *
                         * @param key The key (qemu monitor encoding).
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vmSendkey(String key) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("key", key);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/sendkey", parameters);
                        }

                    }

                    public class PVEFeature {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEFeature(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Check if feature for virtual machine is available.
                         *
                         * @param feature Feature to check. Enum:
                         * snapshot,clone,copy
                         * @param snapname The name of the snapshot.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmFeature(String feature, String snapname) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("feature", feature);
                            parameters.put("snapname", snapname);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/feature", parameters);
                        }

                        /**
                         * Check if feature for virtual machine is available.
                         *
                         * @param feature Feature to check. Enum:
                         * snapshot,clone,copy
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vmFeature(String feature) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("feature", feature);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/feature", parameters);
                        }

                    }

                    public class PVEClone {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEClone(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Create a copy of virtual machine/template.
                         *
                         * @param newid VMID for the clone.
                         * @param bwlimit Override I/O bandwidth limit (in
                         * KiB/s).
                         * @param description Description for the new VM.
                         * @param format Target format for file storage. Only
                         * valid for full clone. Enum: raw,qcow2,vmdk
                         * @param full Create a full copy of all disks. This is
                         * always done when you clone a normal VM. For VM
                         * templates, we try to create a linked clone by
                         * default.
                         * @param name Set a name for the new VM.
                         * @param pool Add the new VM to the specified pool.
                         * @param snapname The name of the snapshot.
                         * @param storage Target storage for full clone.
                         * @param target Target node. Only allowed if the
                         * original VM is on shared storage.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result cloneVm(int newid, Integer bwlimit, String description, String format, Boolean full, String name, String pool, String snapname, String storage, String target) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("newid", newid);
                            parameters.put("bwlimit", bwlimit);
                            parameters.put("description", description);
                            parameters.put("format", format);
                            parameters.put("full", full);
                            parameters.put("name", name);
                            parameters.put("pool", pool);
                            parameters.put("snapname", snapname);
                            parameters.put("storage", storage);
                            parameters.put("target", target);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/clone", parameters);
                        }

                        /**
                         * Create a copy of virtual machine/template.
                         *
                         * @param newid VMID for the clone.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result cloneVm(int newid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("newid", newid);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/clone", parameters);
                        }

                    }

                    public class PVEMoveDisk {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEMoveDisk(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Move volume to different storage or to a different
                         * VM.
                         *
                         * @param disk The disk you want to move. Enum:
                         * ide0,ide1,ide2,ide3,scsi0,scsi1,scsi2,scsi3,scsi4,scsi5,scsi6,scsi7,scsi8,scsi9,scsi10,scsi11,scsi12,scsi13,scsi14,scsi15,scsi16,scsi17,scsi18,scsi19,scsi20,scsi21,scsi22,scsi23,scsi24,scsi25,scsi26,scsi27,scsi28,scsi29,scsi30,virtio0,virtio1,virtio2,virtio3,virtio4,virtio5,virtio6,virtio7,virtio8,virtio9,virtio10,virtio11,virtio12,virtio13,virtio14,virtio15,sata0,sata1,sata2,sata3,sata4,sata5,efidisk0,tpmstate0,unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99,unused100,unused101,unused102,unused103,unused104,unused105,unused106,unused107,unused108,unused109,unused110,unused111,unused112,unused113,unused114,unused115,unused116,unused117,unused118,unused119,unused120,unused121,unused122,unused123,unused124,unused125,unused126,unused127,unused128,unused129,unused130,unused131,unused132,unused133,unused134,unused135,unused136,unused137,unused138,unused139,unused140,unused141,unused142,unused143,unused144,unused145,unused146,unused147,unused148,unused149,unused150,unused151,unused152,unused153,unused154,unused155,unused156,unused157,unused158,unused159,unused160,unused161,unused162,unused163,unused164,unused165,unused166,unused167,unused168,unused169,unused170,unused171,unused172,unused173,unused174,unused175,unused176,unused177,unused178,unused179,unused180,unused181,unused182,unused183,unused184,unused185,unused186,unused187,unused188,unused189,unused190,unused191,unused192,unused193,unused194,unused195,unused196,unused197,unused198,unused199,unused200,unused201,unused202,unused203,unused204,unused205,unused206,unused207,unused208,unused209,unused210,unused211,unused212,unused213,unused214,unused215,unused216,unused217,unused218,unused219,unused220,unused221,unused222,unused223,unused224,unused225,unused226,unused227,unused228,unused229,unused230,unused231,unused232,unused233,unused234,unused235,unused236,unused237,unused238,unused239,unused240,unused241,unused242,unused243,unused244,unused245,unused246,unused247,unused248,unused249,unused250,unused251,unused252,unused253,unused254,unused255
                         * @param bwlimit Override I/O bandwidth limit (in
                         * KiB/s).
                         * @param delete Delete the original disk after
                         * successful copy. By default the original disk is kept
                         * as unused disk.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1" ." digest.
                         * This can be used to prevent concurrent modifications.
                         * @param format Target Format. Enum: raw,qcow2,vmdk
                         * @param storage Target storage.
                         * @param target_digest Prevent changes if the current
                         * config file of the target VM has a" ." different SHA1
                         * digest. This can be used to detect concurrent
                         * modifications.
                         * @param target_disk The config key the disk will be
                         * moved to on the target VM (for example, ide0 or
                         * scsi1). Default is the source disk key. Enum:
                         * ide0,ide1,ide2,ide3,scsi0,scsi1,scsi2,scsi3,scsi4,scsi5,scsi6,scsi7,scsi8,scsi9,scsi10,scsi11,scsi12,scsi13,scsi14,scsi15,scsi16,scsi17,scsi18,scsi19,scsi20,scsi21,scsi22,scsi23,scsi24,scsi25,scsi26,scsi27,scsi28,scsi29,scsi30,virtio0,virtio1,virtio2,virtio3,virtio4,virtio5,virtio6,virtio7,virtio8,virtio9,virtio10,virtio11,virtio12,virtio13,virtio14,virtio15,sata0,sata1,sata2,sata3,sata4,sata5,efidisk0,tpmstate0,unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99,unused100,unused101,unused102,unused103,unused104,unused105,unused106,unused107,unused108,unused109,unused110,unused111,unused112,unused113,unused114,unused115,unused116,unused117,unused118,unused119,unused120,unused121,unused122,unused123,unused124,unused125,unused126,unused127,unused128,unused129,unused130,unused131,unused132,unused133,unused134,unused135,unused136,unused137,unused138,unused139,unused140,unused141,unused142,unused143,unused144,unused145,unused146,unused147,unused148,unused149,unused150,unused151,unused152,unused153,unused154,unused155,unused156,unused157,unused158,unused159,unused160,unused161,unused162,unused163,unused164,unused165,unused166,unused167,unused168,unused169,unused170,unused171,unused172,unused173,unused174,unused175,unused176,unused177,unused178,unused179,unused180,unused181,unused182,unused183,unused184,unused185,unused186,unused187,unused188,unused189,unused190,unused191,unused192,unused193,unused194,unused195,unused196,unused197,unused198,unused199,unused200,unused201,unused202,unused203,unused204,unused205,unused206,unused207,unused208,unused209,unused210,unused211,unused212,unused213,unused214,unused215,unused216,unused217,unused218,unused219,unused220,unused221,unused222,unused223,unused224,unused225,unused226,unused227,unused228,unused229,unused230,unused231,unused232,unused233,unused234,unused235,unused236,unused237,unused238,unused239,unused240,unused241,unused242,unused243,unused244,unused245,unused246,unused247,unused248,unused249,unused250,unused251,unused252,unused253,unused254,unused255
                         * @param target_vmid The (unique) ID of the VM.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result moveVmDisk(String disk, Integer bwlimit, Boolean delete, String digest, String format, String storage, String target_digest, String target_disk, Integer target_vmid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            parameters.put("bwlimit", bwlimit);
                            parameters.put("delete", delete);
                            parameters.put("digest", digest);
                            parameters.put("format", format);
                            parameters.put("storage", storage);
                            parameters.put("target-digest", target_digest);
                            parameters.put("target-disk", target_disk);
                            parameters.put("target-vmid", target_vmid);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/move_disk", parameters);
                        }

                        /**
                         * Move volume to different storage or to a different
                         * VM.
                         *
                         * @param disk The disk you want to move. Enum:
                         * ide0,ide1,ide2,ide3,scsi0,scsi1,scsi2,scsi3,scsi4,scsi5,scsi6,scsi7,scsi8,scsi9,scsi10,scsi11,scsi12,scsi13,scsi14,scsi15,scsi16,scsi17,scsi18,scsi19,scsi20,scsi21,scsi22,scsi23,scsi24,scsi25,scsi26,scsi27,scsi28,scsi29,scsi30,virtio0,virtio1,virtio2,virtio3,virtio4,virtio5,virtio6,virtio7,virtio8,virtio9,virtio10,virtio11,virtio12,virtio13,virtio14,virtio15,sata0,sata1,sata2,sata3,sata4,sata5,efidisk0,tpmstate0,unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99,unused100,unused101,unused102,unused103,unused104,unused105,unused106,unused107,unused108,unused109,unused110,unused111,unused112,unused113,unused114,unused115,unused116,unused117,unused118,unused119,unused120,unused121,unused122,unused123,unused124,unused125,unused126,unused127,unused128,unused129,unused130,unused131,unused132,unused133,unused134,unused135,unused136,unused137,unused138,unused139,unused140,unused141,unused142,unused143,unused144,unused145,unused146,unused147,unused148,unused149,unused150,unused151,unused152,unused153,unused154,unused155,unused156,unused157,unused158,unused159,unused160,unused161,unused162,unused163,unused164,unused165,unused166,unused167,unused168,unused169,unused170,unused171,unused172,unused173,unused174,unused175,unused176,unused177,unused178,unused179,unused180,unused181,unused182,unused183,unused184,unused185,unused186,unused187,unused188,unused189,unused190,unused191,unused192,unused193,unused194,unused195,unused196,unused197,unused198,unused199,unused200,unused201,unused202,unused203,unused204,unused205,unused206,unused207,unused208,unused209,unused210,unused211,unused212,unused213,unused214,unused215,unused216,unused217,unused218,unused219,unused220,unused221,unused222,unused223,unused224,unused225,unused226,unused227,unused228,unused229,unused230,unused231,unused232,unused233,unused234,unused235,unused236,unused237,unused238,unused239,unused240,unused241,unused242,unused243,unused244,unused245,unused246,unused247,unused248,unused249,unused250,unused251,unused252,unused253,unused254,unused255
                         * @return Result
                         * @throws JSONException
                         */

                        public Result moveVmDisk(String disk) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/move_disk", parameters);
                        }

                    }

                    public class PVEMigrate {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEMigrate(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Get preconditions for migration.
                         *
                         * @param target Target node.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result migrateVmPrecondition(String target) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("target", target);
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/migrate", parameters);
                        }

                        /**
                         * Get preconditions for migration.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result migrateVmPrecondition() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/migrate", null);
                        }

                        /**
                         * Migrate virtual machine. Creates a new migration
                         * task.
                         *
                         * @param target Target node.
                         * @param bwlimit Override I/O bandwidth limit (in
                         * KiB/s).
                         * @param force Allow to migrate VMs which use local
                         * devices. Only root may use this option.
                         * @param migration_network CIDR of the (sub) network
                         * that is used for migration.
                         * @param migration_type Migration traffic is encrypted
                         * using an SSH tunnel by default. On secure, completely
                         * private networks this can be disabled to increase
                         * performance. Enum: secure,insecure
                         * @param online Use online/live migration if VM is
                         * running. Ignored if VM is stopped.
                         * @param targetstorage Mapping from source to target
                         * storages. Providing only a single storage ID maps all
                         * source storages to that storage. Providing the
                         * special value '1' will map each source storage to
                         * itself.
                         * @param with_local_disks Enable live storage migration
                         * for local disk
                         * @return Result
                         * @throws JSONException
                         */

                        public Result migrateVm(String target, Integer bwlimit, Boolean force, String migration_network, String migration_type, Boolean online, String targetstorage, Boolean with_local_disks) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("target", target);
                            parameters.put("bwlimit", bwlimit);
                            parameters.put("force", force);
                            parameters.put("migration_network", migration_network);
                            parameters.put("migration_type", migration_type);
                            parameters.put("online", online);
                            parameters.put("targetstorage", targetstorage);
                            parameters.put("with-local-disks", with_local_disks);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/migrate", parameters);
                        }

                        /**
                         * Migrate virtual machine. Creates a new migration
                         * task.
                         *
                         * @param target Target node.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result migrateVm(String target) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("target", target);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/migrate", parameters);
                        }

                    }

                    public class PVEMonitor {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEMonitor(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Execute Qemu monitor commands.
                         *
                         * @param command The monitor command.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result monitor(String command) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("command", command);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/monitor", parameters);
                        }

                    }

                    public class PVEResize {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEResize(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Extend volume size.
                         *
                         * @param disk The disk you want to resize. Enum:
                         * ide0,ide1,ide2,ide3,scsi0,scsi1,scsi2,scsi3,scsi4,scsi5,scsi6,scsi7,scsi8,scsi9,scsi10,scsi11,scsi12,scsi13,scsi14,scsi15,scsi16,scsi17,scsi18,scsi19,scsi20,scsi21,scsi22,scsi23,scsi24,scsi25,scsi26,scsi27,scsi28,scsi29,scsi30,virtio0,virtio1,virtio2,virtio3,virtio4,virtio5,virtio6,virtio7,virtio8,virtio9,virtio10,virtio11,virtio12,virtio13,virtio14,virtio15,sata0,sata1,sata2,sata3,sata4,sata5,efidisk0,tpmstate0
                         * @param size The new size. With the `+` sign the value
                         * is added to the actual size of the volume and without
                         * it, the value is taken as an absolute one. Shrinking
                         * disk size is not supported.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param skiplock Ignore locks - only root is allowed
                         * to use this option.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result resizeVm(String disk, String size, String digest, Boolean skiplock) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            parameters.put("size", size);
                            parameters.put("digest", digest);
                            parameters.put("skiplock", skiplock);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/resize", parameters);
                        }

                        /**
                         * Extend volume size.
                         *
                         * @param disk The disk you want to resize. Enum:
                         * ide0,ide1,ide2,ide3,scsi0,scsi1,scsi2,scsi3,scsi4,scsi5,scsi6,scsi7,scsi8,scsi9,scsi10,scsi11,scsi12,scsi13,scsi14,scsi15,scsi16,scsi17,scsi18,scsi19,scsi20,scsi21,scsi22,scsi23,scsi24,scsi25,scsi26,scsi27,scsi28,scsi29,scsi30,virtio0,virtio1,virtio2,virtio3,virtio4,virtio5,virtio6,virtio7,virtio8,virtio9,virtio10,virtio11,virtio12,virtio13,virtio14,virtio15,sata0,sata1,sata2,sata3,sata4,sata5,efidisk0,tpmstate0
                         * @param size The new size. With the `+` sign the value
                         * is added to the actual size of the volume and without
                         * it, the value is taken as an absolute one. Shrinking
                         * disk size is not supported.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result resizeVm(String disk, String size) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            parameters.put("size", size);
                            return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/resize", parameters);
                        }

                    }

                    public class PVESnapshot {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVESnapshot(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        public PVESnapnameItem get(Object snapname) {
                            return new PVESnapnameItem(_client, _node, _vmid, snapname);
                        }

                        public class PVESnapnameItem {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;
                            private final Object _snapname;

                            protected PVESnapnameItem(PveClient client, Object node, Object vmid, Object snapname) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                                _snapname = snapname;
                            }

                            private PVEConfig _config;

                            public PVEConfig getConfig() {
                                return _config == null ? (_config = new PVEConfig(_client, _node, _vmid, _snapname)) : _config;
                            }
                            private PVERollback _rollback;

                            public PVERollback getRollback() {
                                return _rollback == null ? (_rollback = new PVERollback(_client, _node, _vmid, _snapname)) : _rollback;
                            }

                            public class PVEConfig {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _snapname;

                                protected PVEConfig(PveClient client, Object node, Object vmid, Object snapname) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _snapname = snapname;
                                }

                                /**
                                 * Get snapshot configuration
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result getSnapshotConfig() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "/config", null);
                                }

                                /**
                                 * Update snapshot metadata.
                                 *
                                 * @param description A textual description or
                                 * comment.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateSnapshotConfig(String description) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("description", description);
                                    return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "/config", parameters);
                                }

                                /**
                                 * Update snapshot metadata.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateSnapshotConfig() throws JSONException {
                                    return _client.set("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "/config", null);
                                }

                            }

                            public class PVERollback {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _snapname;

                                protected PVERollback(PveClient client, Object node, Object vmid, Object snapname) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _snapname = snapname;
                                }

                                /**
                                 * Rollback VM state to specified snapshot.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result rollback() throws JSONException {
                                    return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "/rollback", null);
                                }

                            }

                            /**
                             * Delete a VM snapshot.
                             *
                             * @param force For removal from config file, even
                             * if removing disk snapshots fails.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result delsnapshot(Boolean force) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("force", force);
                                return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "", parameters);
                            }

                            /**
                             * Delete a VM snapshot.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result delsnapshot() throws JSONException {
                                return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "", null);
                            }

                            /**
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result snapshotCmdIdx() throws JSONException {
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot/" + _snapname + "", null);
                            }

                        }

                        /**
                         * List all snapshots.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result snapshotList() throws JSONException {
                            return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot", null);
                        }

                        /**
                         * Snapshot a VM.
                         *
                         * @param snapname The name of the snapshot.
                         * @param description A textual description or comment.
                         * @param vmstate Save the vmstate
                         * @return Result
                         * @throws JSONException
                         */

                        public Result snapshot(String snapname, String description, Boolean vmstate) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("snapname", snapname);
                            parameters.put("description", description);
                            parameters.put("vmstate", vmstate);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot", parameters);
                        }

                        /**
                         * Snapshot a VM.
                         *
                         * @param snapname The name of the snapshot.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result snapshot(String snapname) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("snapname", snapname);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/snapshot", parameters);
                        }

                    }

                    public class PVETemplate {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVETemplate(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Create a Template.
                         *
                         * @param disk If you want to convert only 1 disk to
                         * base image. Enum:
                         * ide0,ide1,ide2,ide3,scsi0,scsi1,scsi2,scsi3,scsi4,scsi5,scsi6,scsi7,scsi8,scsi9,scsi10,scsi11,scsi12,scsi13,scsi14,scsi15,scsi16,scsi17,scsi18,scsi19,scsi20,scsi21,scsi22,scsi23,scsi24,scsi25,scsi26,scsi27,scsi28,scsi29,scsi30,virtio0,virtio1,virtio2,virtio3,virtio4,virtio5,virtio6,virtio7,virtio8,virtio9,virtio10,virtio11,virtio12,virtio13,virtio14,virtio15,sata0,sata1,sata2,sata3,sata4,sata5,efidisk0,tpmstate0
                         * @return Result
                         * @throws JSONException
                         */
                        public Result template(String disk) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/template", parameters);
                        }

                        /**
                         * Create a Template.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result template() throws JSONException {
                            return _client.create("/nodes/" + _node + "/qemu/" + _vmid + "/template", null);
                        }

                    }

                    public class PVECloudinit {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVECloudinit(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        private PVEDump _dump;

                        public PVEDump getDump() {
                            return _dump == null ? (_dump = new PVEDump(_client, _node, _vmid)) : _dump;
                        }

                        public class PVEDump {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEDump(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Get automatically generated cloudinit config.
                             *
                             * @param type Config type. Enum: user,network,meta
                             * @return Result
                             * @throws JSONException
                             */
                            public Result cloudinitGeneratedConfigDump(String type) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("type", type);
                                return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "/cloudinit/dump", parameters);
                            }

                        }

                    }

                    /**
                     * Destroy the VM and all used/owned volumes. Removes any VM
                     * specific permissions and firewall rules
                     *
                     * @param destroy_unreferenced_disks If set, destroy
                     * additionally all disks not referenced in the config but
                     * with a matching VMID from all enabled storages.
                     * @param purge Remove VMID from configurations, like backup
                     * &amp; replication jobs and HA.
                     * @param skiplock Ignore locks - only root is allowed to
                     * use this option.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result destroyVm(Boolean destroy_unreferenced_disks, Boolean purge, Boolean skiplock) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("destroy-unreferenced-disks", destroy_unreferenced_disks);
                        parameters.put("purge", purge);
                        parameters.put("skiplock", skiplock);
                        return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "", parameters);
                    }

                    /**
                     * Destroy the VM and all used/owned volumes. Removes any VM
                     * specific permissions and firewall rules
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result destroyVm() throws JSONException {
                        return _client.delete("/nodes/" + _node + "/qemu/" + _vmid + "", null);
                    }

                    /**
                     * Directory index
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result vmdiridx() throws JSONException {
                        return _client.get("/nodes/" + _node + "/qemu/" + _vmid + "", null);
                    }

                }

                /**
                 * Virtual machine index (per node).
                 *
                 * @param full Determine the full status of active VMs.
                 * @return Result
                 * @throws JSONException
                 */
                public Result vmlist(Boolean full) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("full", full);
                    return _client.get("/nodes/" + _node + "/qemu", parameters);
                }

                /**
                 * Virtual machine index (per node).
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result vmlist() throws JSONException {
                    return _client.get("/nodes/" + _node + "/qemu", null);
                }

                /**
                 * Create or restore a virtual machine.
                 *
                 * @param vmid The (unique) ID of the VM.
                 * @param acpi Enable/disable ACPI.
                 * @param agent Enable/disable communication with the Qemu Guest
                 * Agent and its properties.
                 * @param arch Virtual processor architecture. Defaults to the
                 * host. Enum: x86_64,aarch64
                 * @param archive The backup archive. Either the file system
                 * path to a .tar or .vma file (use '-' to pipe data from stdin)
                 * or a proxmox storage backup volume identifier.
                 * @param args Arbitrary arguments passed to kvm.
                 * @param audio0 Configure a audio device, useful in combination
                 * with QXL/Spice.
                 * @param autostart Automatic restart after crash (currently
                 * ignored).
                 * @param balloon Amount of target RAM for the VM in MB. Using
                 * zero disables the ballon driver.
                 * @param bios Select BIOS implementation. Enum: seabios,ovmf
                 * @param boot Specify guest boot order. Use the 'order='
                 * sub-property as usage with no key or 'legacy=' is deprecated.
                 * @param bootdisk Enable booting from specified disk.
                 * Deprecated: Use 'boot: order=foo;bar' instead.
                 * @param bwlimit Override I/O bandwidth limit (in KiB/s).
                 * @param cdrom This is an alias for option -ide2
                 * @param cicustom cloud-init: Specify custom files to replace
                 * the automatically generated ones at start.
                 * @param cipassword cloud-init: Password to assign the user.
                 * Using this is generally not recommended. Use ssh keys
                 * instead. Also note that older cloud-init versions do not
                 * support hashed passwords.
                 * @param citype Specifies the cloud-init configuration format.
                 * The default depends on the configured operating system type
                 * (`ostype`. We use the `nocloud` format for Linux, and
                 * `configdrive2` for windows. Enum:
                 * configdrive2,nocloud,opennebula
                 * @param ciuser cloud-init: User name to change ssh keys and
                 * password for instead of the image's configured default user.
                 * @param cores The number of cores per socket.
                 * @param cpu Emulated CPU type.
                 * @param cpulimit Limit of CPU usage.
                 * @param cpuunits CPU weight for a VM, will be clamped to [1,
                 * 10000] in cgroup v2.
                 * @param description Description for the VM. Shown in the
                 * web-interface VM's summary. This is saved as comment inside
                 * the configuration file.
                 * @param efidisk0 Configure a Disk for storing EFI vars. Use
                 * the special syntax STORAGE_ID:SIZE_IN_GiB to allocate a new
                 * volume. Note that SIZE_IN_GiB is ignored here and that the
                 * default EFI vars are copied to the volume instead.
                 * @param force Allow to overwrite existing VM.
                 * @param freeze Freeze CPU at startup (use 'c' monitor command
                 * to start execution).
                 * @param hookscript Script that will be executed during various
                 * steps in the vms lifetime.
                 * @param hostpciN Map host PCI devices into guest.
                 * @param hotplug Selectively enable hotplug features. This is a
                 * comma separated list of hotplug features: 'network', 'disk',
                 * 'cpu', 'memory' and 'usb'. Use '0' to disable hotplug
                 * completely. Using '1' as value is an alias for the default
                 * `network,disk,usb`.
                 * @param hugepages Enable/disable hugepages memory. Enum:
                 * any,2,1024
                 * @param ideN Use volume as IDE hard disk or CD-ROM (n is 0 to
                 * 3). Use the special syntax STORAGE_ID:SIZE_IN_GiB to allocate
                 * a new volume.
                 * @param ipconfigN cloud-init: Specify IP addresses and
                 * gateways for the corresponding interface. IP addresses use
                 * CIDR notation, gateways are optional but need an IP of the
                 * same type specified. The special string 'dhcp' can be used
                 * for IP addresses to use DHCP, in which case no explicit
                 * gateway should be provided. For IPv6 the special string
                 * 'auto' can be used to use stateless autoconfiguration. This
                 * requires cloud-init 19.4 or newer. If cloud-init is enabled
                 * and neither an IPv4 nor an IPv6 address is specified, it
                 * defaults to using dhcp on IPv4.
                 * @param ivshmem Inter-VM shared memory. Useful for direct
                 * communication between VMs, or to the host.
                 * @param keephugepages Use together with hugepages. If enabled,
                 * hugepages will not not be deleted after VM shutdown and can
                 * be used for subsequent starts.
                 * @param keyboard Keyboard layout for VNC server. The default
                 * is read from the'/etc/pve/datacenter.cfg' configuration file.
                 * It should not be necessary to set it. Enum:
                 * de,de-ch,da,en-gb,en-us,es,fi,fr,fr-be,fr-ca,fr-ch,hu,is,it,ja,lt,mk,nl,no,pl,pt,pt-br,sv,sl,tr
                 * @param kvm Enable/disable KVM hardware virtualization.
                 * @param live_restore Start the VM immediately from the backup
                 * and restore in background. PBS only.
                 * @param localtime Set the real time clock (RTC) to local time.
                 * This is enabled by default if the `ostype` indicates a
                 * Microsoft Windows OS.
                 * @param lock_ Lock/unlock the VM. Enum:
                 * backup,clone,create,migrate,rollback,snapshot,snapshot-delete,suspending,suspended
                 * @param machine Specifies the Qemu machine type.
                 * @param memory Amount of RAM for the VM in MB. This is the
                 * maximum available memory when you use the balloon device.
                 * @param migrate_downtime Set maximum tolerated downtime (in
                 * seconds) for migrations.
                 * @param migrate_speed Set maximum speed (in MB/s) for
                 * migrations. Value 0 is no limit.
                 * @param name Set a name for the VM. Only used on the
                 * configuration web interface.
                 * @param nameserver cloud-init: Sets DNS server IP address for
                 * a container. Create will' .' automatically use the setting
                 * from the host if neither searchdomain nor nameserver' .' are
                 * set.
                 * @param netN Specify network devices.
                 * @param numa Enable/disable NUMA.
                 * @param numaN NUMA topology.
                 * @param onboot Specifies whether a VM will be started during
                 * system bootup.
                 * @param ostype Specify guest operating system. Enum:
                 * other,wxp,w2k,w2k3,w2k8,wvista,win7,win8,win10,win11,l24,l26,solaris
                 * @param parallelN Map host parallel devices (n is 0 to 2).
                 * @param pool Add the VM to the specified pool.
                 * @param protection Sets the protection flag of the VM. This
                 * will disable the remove VM and remove disk operations.
                 * @param reboot Allow reboot. If set to '0' the VM exit on
                 * reboot.
                 * @param rng0 Configure a VirtIO-based Random Number Generator.
                 * @param sataN Use volume as SATA hard disk or CD-ROM (n is 0
                 * to 5). Use the special syntax STORAGE_ID:SIZE_IN_GiB to
                 * allocate a new volume.
                 * @param scsiN Use volume as SCSI hard disk or CD-ROM (n is 0
                 * to 30). Use the special syntax STORAGE_ID:SIZE_IN_GiB to
                 * allocate a new volume.
                 * @param scsihw SCSI controller model Enum:
                 * lsi,lsi53c810,virtio-scsi-pci,virtio-scsi-single,megasas,pvscsi
                 * @param searchdomain cloud-init: Sets DNS search domains for a
                 * container. Create will' .' automatically use the setting from
                 * the host if neither searchdomain nor nameserver' .' are set.
                 * @param serialN Create a serial device inside the VM (n is 0
                 * to 3)
                 * @param shares Amount of memory shares for auto-ballooning.
                 * The larger the number is, the more memory this VM gets.
                 * Number is relative to weights of all other running VMs. Using
                 * zero disables auto-ballooning. Auto-ballooning is done by
                 * pvestatd.
                 * @param smbios1 Specify SMBIOS type 1 fields.
                 * @param smp The number of CPUs. Please use option -sockets
                 * instead.
                 * @param sockets The number of CPU sockets.
                 * @param spice_enhancements Configure additional enhancements
                 * for SPICE.
                 * @param sshkeys cloud-init: Setup public SSH keys (one key per
                 * line, OpenSSH format).
                 * @param start Start VM after it was created successfully.
                 * @param startdate Set the initial date of the real time clock.
                 * Valid format for date are:'now' or '2006-06-17T16:01:21' or
                 * '2006-06-17'.
                 * @param startup Startup and shutdown behavior. Order is a
                 * non-negative number defining the general startup order.
                 * Shutdown in done with reverse ordering. Additionally you can
                 * set the 'up' or 'down' delay in seconds, which specifies a
                 * delay to wait before the next VM is started or stopped.
                 * @param storage Default storage.
                 * @param tablet Enable/disable the USB tablet device.
                 * @param tags Tags of the VM. This is only meta information.
                 * @param tdf Enable/disable time drift fix.
                 * @param template Enable/disable Template.
                 * @param tpmstate0 Configure a Disk for storing TPM state. Use
                 * the special syntax STORAGE_ID:SIZE_IN_GiB to allocate a new
                 * volume. Note that SIZE_IN_GiB is ignored here and that the
                 * default size of 4 MiB will always be used instead. The format
                 * is also fixed to 'raw'.
                 * @param unique Assign a unique random ethernet address.
                 * @param unusedN Reference to unused volumes. This is used
                 * internally, and should not be modified manually.
                 * @param usbN Configure an USB device (n is 0 to 4).
                 * @param vcpus Number of hotplugged vcpus.
                 * @param vga Configure the VGA hardware.
                 * @param virtioN Use volume as VIRTIO hard disk (n is 0 to 15).
                 * Use the special syntax STORAGE_ID:SIZE_IN_GiB to allocate a
                 * new volume.
                 * @param vmgenid Set VM Generation ID. Use '1' to autogenerate
                 * on create or update, pass '0' to disable explicitly.
                 * @param vmstatestorage Default storage for VM state
                 * volumes/files.
                 * @param watchdog Create a virtual hardware watchdog device.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createVm(int vmid, Boolean acpi, String agent, String arch, String archive, String args, String audio0, Boolean autostart, Integer balloon, String bios, String boot, String bootdisk, Integer bwlimit, String cdrom, String cicustom, String cipassword, String citype, String ciuser, Integer cores, String cpu, Float cpulimit, Integer cpuunits, String description, String efidisk0, Boolean force, Boolean freeze, String hookscript, Map<Integer, String> hostpciN, String hotplug, String hugepages, Map<Integer, String> ideN, Map<Integer, String> ipconfigN, String ivshmem, Boolean keephugepages, String keyboard, Boolean kvm, Boolean live_restore, Boolean localtime, String lock_, String machine, Integer memory, Float migrate_downtime, Integer migrate_speed, String name, String nameserver, Map<Integer, String> netN, Boolean numa, Map<Integer, String> numaN, Boolean onboot, String ostype, Map<Integer, String> parallelN, String pool, Boolean protection, Boolean reboot, String rng0, Map<Integer, String> sataN, Map<Integer, String> scsiN, String scsihw, String searchdomain, Map<Integer, String> serialN, Integer shares, String smbios1, Integer smp, Integer sockets, String spice_enhancements, String sshkeys, Boolean start, String startdate, String startup, String storage, Boolean tablet, String tags, Boolean tdf, Boolean template, String tpmstate0, Boolean unique, Map<Integer, String> unusedN, Map<Integer, String> usbN, Integer vcpus, String vga, Map<Integer, String> virtioN, String vmgenid, String vmstatestorage, String watchdog) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("vmid", vmid);
                    parameters.put("acpi", acpi);
                    parameters.put("agent", agent);
                    parameters.put("arch", arch);
                    parameters.put("archive", archive);
                    parameters.put("args", args);
                    parameters.put("audio0", audio0);
                    parameters.put("autostart", autostart);
                    parameters.put("balloon", balloon);
                    parameters.put("bios", bios);
                    parameters.put("boot", boot);
                    parameters.put("bootdisk", bootdisk);
                    parameters.put("bwlimit", bwlimit);
                    parameters.put("cdrom", cdrom);
                    parameters.put("cicustom", cicustom);
                    parameters.put("cipassword", cipassword);
                    parameters.put("citype", citype);
                    parameters.put("ciuser", ciuser);
                    parameters.put("cores", cores);
                    parameters.put("cpu", cpu);
                    parameters.put("cpulimit", cpulimit);
                    parameters.put("cpuunits", cpuunits);
                    parameters.put("description", description);
                    parameters.put("efidisk0", efidisk0);
                    parameters.put("force", force);
                    parameters.put("freeze", freeze);
                    parameters.put("hookscript", hookscript);
                    parameters.put("hotplug", hotplug);
                    parameters.put("hugepages", hugepages);
                    parameters.put("ivshmem", ivshmem);
                    parameters.put("keephugepages", keephugepages);
                    parameters.put("keyboard", keyboard);
                    parameters.put("kvm", kvm);
                    parameters.put("live-restore", live_restore);
                    parameters.put("localtime", localtime);
                    parameters.put("lock", lock_);
                    parameters.put("machine", machine);
                    parameters.put("memory", memory);
                    parameters.put("migrate_downtime", migrate_downtime);
                    parameters.put("migrate_speed", migrate_speed);
                    parameters.put("name", name);
                    parameters.put("nameserver", nameserver);
                    parameters.put("numa", numa);
                    parameters.put("onboot", onboot);
                    parameters.put("ostype", ostype);
                    parameters.put("pool", pool);
                    parameters.put("protection", protection);
                    parameters.put("reboot", reboot);
                    parameters.put("rng0", rng0);
                    parameters.put("scsihw", scsihw);
                    parameters.put("searchdomain", searchdomain);
                    parameters.put("shares", shares);
                    parameters.put("smbios1", smbios1);
                    parameters.put("smp", smp);
                    parameters.put("sockets", sockets);
                    parameters.put("spice_enhancements", spice_enhancements);
                    parameters.put("sshkeys", sshkeys);
                    parameters.put("start", start);
                    parameters.put("startdate", startdate);
                    parameters.put("startup", startup);
                    parameters.put("storage", storage);
                    parameters.put("tablet", tablet);
                    parameters.put("tags", tags);
                    parameters.put("tdf", tdf);
                    parameters.put("template", template);
                    parameters.put("tpmstate0", tpmstate0);
                    parameters.put("unique", unique);
                    parameters.put("vcpus", vcpus);
                    parameters.put("vga", vga);
                    parameters.put("vmgenid", vmgenid);
                    parameters.put("vmstatestorage", vmstatestorage);
                    parameters.put("watchdog", watchdog);
                    addIndexedParameter(parameters, "hostpci", hostpciN);
                    addIndexedParameter(parameters, "ide", ideN);
                    addIndexedParameter(parameters, "ipconfig", ipconfigN);
                    addIndexedParameter(parameters, "net", netN);
                    addIndexedParameter(parameters, "numa", numaN);
                    addIndexedParameter(parameters, "parallel", parallelN);
                    addIndexedParameter(parameters, "sata", sataN);
                    addIndexedParameter(parameters, "scsi", scsiN);
                    addIndexedParameter(parameters, "serial", serialN);
                    addIndexedParameter(parameters, "unused", unusedN);
                    addIndexedParameter(parameters, "usb", usbN);
                    addIndexedParameter(parameters, "virtio", virtioN);
                    return _client.create("/nodes/" + _node + "/qemu", parameters);
                }

                /**
                 * Create or restore a virtual machine.
                 *
                 * @param vmid The (unique) ID of the VM.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createVm(int vmid) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("vmid", vmid);
                    return _client.create("/nodes/" + _node + "/qemu", parameters);
                }

            }

            public class PVELxc {

                private final PveClient _client;
                private final Object _node;

                protected PVELxc(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEVmidItem get(Object vmid) {
                    return new PVEVmidItem(_client, _node, vmid);
                }

                public class PVEVmidItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _vmid;

                    protected PVEVmidItem(PveClient client, Object node, Object vmid) {
                        _client = client;
                        _node = node;
                        _vmid = vmid;
                    }

                    private PVEConfig _config;

                    public PVEConfig getConfig() {
                        return _config == null ? (_config = new PVEConfig(_client, _node, _vmid)) : _config;
                    }
                    private PVEStatus _status;

                    public PVEStatus getStatus() {
                        return _status == null ? (_status = new PVEStatus(_client, _node, _vmid)) : _status;
                    }
                    private PVESnapshot _snapshot;

                    public PVESnapshot getSnapshot() {
                        return _snapshot == null ? (_snapshot = new PVESnapshot(_client, _node, _vmid)) : _snapshot;
                    }
                    private PVEFirewall _firewall;

                    public PVEFirewall getFirewall() {
                        return _firewall == null ? (_firewall = new PVEFirewall(_client, _node, _vmid)) : _firewall;
                    }
                    private PVERrd _rrd;

                    public PVERrd getRrd() {
                        return _rrd == null ? (_rrd = new PVERrd(_client, _node, _vmid)) : _rrd;
                    }
                    private PVERrddata _rrddata;

                    public PVERrddata getRrddata() {
                        return _rrddata == null ? (_rrddata = new PVERrddata(_client, _node, _vmid)) : _rrddata;
                    }
                    private PVEVncproxy _vncproxy;

                    public PVEVncproxy getVncproxy() {
                        return _vncproxy == null ? (_vncproxy = new PVEVncproxy(_client, _node, _vmid)) : _vncproxy;
                    }
                    private PVETermproxy _termproxy;

                    public PVETermproxy getTermproxy() {
                        return _termproxy == null ? (_termproxy = new PVETermproxy(_client, _node, _vmid)) : _termproxy;
                    }
                    private PVEVncwebsocket _vncwebsocket;

                    public PVEVncwebsocket getVncwebsocket() {
                        return _vncwebsocket == null ? (_vncwebsocket = new PVEVncwebsocket(_client, _node, _vmid)) : _vncwebsocket;
                    }
                    private PVESpiceproxy _spiceproxy;

                    public PVESpiceproxy getSpiceproxy() {
                        return _spiceproxy == null ? (_spiceproxy = new PVESpiceproxy(_client, _node, _vmid)) : _spiceproxy;
                    }
                    private PVEMigrate _migrate;

                    public PVEMigrate getMigrate() {
                        return _migrate == null ? (_migrate = new PVEMigrate(_client, _node, _vmid)) : _migrate;
                    }
                    private PVEFeature _feature;

                    public PVEFeature getFeature() {
                        return _feature == null ? (_feature = new PVEFeature(_client, _node, _vmid)) : _feature;
                    }
                    private PVETemplate _template;

                    public PVETemplate getTemplate() {
                        return _template == null ? (_template = new PVETemplate(_client, _node, _vmid)) : _template;
                    }
                    private PVEClone _clone;

                    public PVEClone getClone() {
                        return _clone == null ? (_clone = new PVEClone(_client, _node, _vmid)) : _clone;
                    }
                    private PVEResize _resize;

                    public PVEResize getResize() {
                        return _resize == null ? (_resize = new PVEResize(_client, _node, _vmid)) : _resize;
                    }
                    private PVEMoveVolume _moveVolume;

                    public PVEMoveVolume getMoveVolume() {
                        return _moveVolume == null ? (_moveVolume = new PVEMoveVolume(_client, _node, _vmid)) : _moveVolume;
                    }
                    private PVEPending _pending;

                    public PVEPending getPending() {
                        return _pending == null ? (_pending = new PVEPending(_client, _node, _vmid)) : _pending;
                    }

                    public class PVEConfig {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEConfig(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Get container configuration.
                         *
                         * @param current Get current values (instead of pending
                         * values).
                         * @param snapshot Fetch config values from given
                         * snapshot.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmConfig(Boolean current, String snapshot) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("current", current);
                            parameters.put("snapshot", snapshot);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/config", parameters);
                        }

                        /**
                         * Get container configuration.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vmConfig() throws JSONException {
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/config", null);
                        }

                        /**
                         * Set container options.
                         *
                         * @param arch OS architecture type. Enum:
                         * amd64,i386,arm64,armhf
                         * @param cmode Console mode. By default, the console
                         * command tries to open a connection to one of the
                         * available tty devices. By setting cmode to 'console'
                         * it tries to attach to /dev/console instead. If you
                         * set cmode to 'shell', it simply invokes a shell
                         * inside the container (no login). Enum:
                         * shell,console,tty
                         * @param console Attach a console device (/dev/console)
                         * to the container.
                         * @param cores The number of cores assigned to the
                         * container. A container can use all available cores by
                         * default.
                         * @param cpulimit Limit of CPU usage. NOTE: If the
                         * computer has 2 CPUs, it has a total of '2' CPU time.
                         * Value '0' indicates no CPU limit.
                         * @param cpuunits CPU weight for a VM. Argument is used
                         * in the kernel fair scheduler. The larger the number
                         * is, the more CPU time this VM gets. Number is
                         * relative to the weights of all the other running VMs.
                         * NOTE: You can disable fair-scheduler configuration by
                         * setting this to 0.
                         * @param debug Try to be more verbose. For now this
                         * only enables debug log-level on start.
                         * @param delete A list of settings you want to delete.
                         * @param description Description for the Container.
                         * Shown in the web-interface CT's summary. This is
                         * saved as comment inside the configuration file.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param features Allow containers access to advanced
                         * features.
                         * @param hookscript Script that will be exectued during
                         * various steps in the containers lifetime.
                         * @param hostname Set a host name for the container.
                         * @param lock_ Lock/unlock the VM. Enum:
                         * backup,create,destroyed,disk,fstrim,migrate,mounted,rollback,snapshot,snapshot-delete
                         * @param memory Amount of RAM for the VM in MB.
                         * @param mpN Use volume as container mount point. Use
                         * the special syntax STORAGE_ID:SIZE_IN_GiB to allocate
                         * a new volume.
                         * @param nameserver Sets DNS server IP address for a
                         * container. Create will automatically use the setting
                         * from the host if you neither set searchdomain nor
                         * nameserver.
                         * @param netN Specifies network interfaces for the
                         * container.
                         * @param onboot Specifies whether a VM will be started
                         * during system bootup.
                         * @param ostype OS type. This is used to setup
                         * configuration inside the container, and corresponds
                         * to lxc setup scripts in
                         * /usr/share/lxc/config/&amp;lt;ostype&amp;gt;.common.conf.
                         * Value 'unmanaged' can be used to skip and OS specific
                         * setup. Enum:
                         * debian,devuan,ubuntu,centos,fedora,opensuse,archlinux,alpine,gentoo,unmanaged
                         * @param protection Sets the protection flag of the
                         * container. This will prevent the CT or CT's disk
                         * remove/update operation.
                         * @param revert Revert a pending change.
                         * @param rootfs Use volume as container root.
                         * @param searchdomain Sets DNS search domains for a
                         * container. Create will automatically use the setting
                         * from the host if you neither set searchdomain nor
                         * nameserver.
                         * @param startup Startup and shutdown behavior. Order
                         * is a non-negative number defining the general startup
                         * order. Shutdown in done with reverse ordering.
                         * Additionally you can set the 'up' or 'down' delay in
                         * seconds, which specifies a delay to wait before the
                         * next VM is started or stopped.
                         * @param swap Amount of SWAP for the VM in MB.
                         * @param tags Tags of the Container. This is only meta
                         * information.
                         * @param template Enable/disable Template.
                         * @param timezone Time zone to use in the container. If
                         * option isn't set, then nothing will be done. Can be
                         * set to 'host' to match the host time zone, or an
                         * arbitrary time zone option from
                         * /usr/share/zoneinfo/zone.tab
                         * @param tty Specify the number of tty available to the
                         * container
                         * @param unprivileged Makes the container run as
                         * unprivileged user. (Should not be modified manually.)
                         * @param unusedN Reference to unused volumes. This is
                         * used internally, and should not be modified manually.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateVm(String arch, String cmode, Boolean console, Integer cores, Float cpulimit, Integer cpuunits, Boolean debug, String delete, String description, String digest, String features, String hookscript, String hostname, String lock_, Integer memory, Map<Integer, String> mpN, String nameserver, Map<Integer, String> netN, Boolean onboot, String ostype, Boolean protection, String revert, String rootfs, String searchdomain, String startup, Integer swap, String tags, Boolean template, String timezone, Integer tty, Boolean unprivileged, Map<Integer, String> unusedN) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("arch", arch);
                            parameters.put("cmode", cmode);
                            parameters.put("console", console);
                            parameters.put("cores", cores);
                            parameters.put("cpulimit", cpulimit);
                            parameters.put("cpuunits", cpuunits);
                            parameters.put("debug", debug);
                            parameters.put("delete", delete);
                            parameters.put("description", description);
                            parameters.put("digest", digest);
                            parameters.put("features", features);
                            parameters.put("hookscript", hookscript);
                            parameters.put("hostname", hostname);
                            parameters.put("lock", lock_);
                            parameters.put("memory", memory);
                            parameters.put("nameserver", nameserver);
                            parameters.put("onboot", onboot);
                            parameters.put("ostype", ostype);
                            parameters.put("protection", protection);
                            parameters.put("revert", revert);
                            parameters.put("rootfs", rootfs);
                            parameters.put("searchdomain", searchdomain);
                            parameters.put("startup", startup);
                            parameters.put("swap", swap);
                            parameters.put("tags", tags);
                            parameters.put("template", template);
                            parameters.put("timezone", timezone);
                            parameters.put("tty", tty);
                            parameters.put("unprivileged", unprivileged);
                            addIndexedParameter(parameters, "mp", mpN);
                            addIndexedParameter(parameters, "net", netN);
                            addIndexedParameter(parameters, "unused", unusedN);
                            return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/config", parameters);
                        }

                        /**
                         * Set container options.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateVm() throws JSONException {
                            return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/config", null);
                        }

                    }

                    public class PVEStatus {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEStatus(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        private PVECurrent _current;

                        public PVECurrent getCurrent() {
                            return _current == null ? (_current = new PVECurrent(_client, _node, _vmid)) : _current;
                        }
                        private PVEStart _start;

                        public PVEStart getStart() {
                            return _start == null ? (_start = new PVEStart(_client, _node, _vmid)) : _start;
                        }
                        private PVEStop _stop;

                        public PVEStop getStop() {
                            return _stop == null ? (_stop = new PVEStop(_client, _node, _vmid)) : _stop;
                        }
                        private PVEShutdown _shutdown;

                        public PVEShutdown getShutdown() {
                            return _shutdown == null ? (_shutdown = new PVEShutdown(_client, _node, _vmid)) : _shutdown;
                        }
                        private PVESuspend _suspend;

                        public PVESuspend getSuspend() {
                            return _suspend == null ? (_suspend = new PVESuspend(_client, _node, _vmid)) : _suspend;
                        }
                        private PVEResume _resume;

                        public PVEResume getResume() {
                            return _resume == null ? (_resume = new PVEResume(_client, _node, _vmid)) : _resume;
                        }
                        private PVEReboot _reboot;

                        public PVEReboot getReboot() {
                            return _reboot == null ? (_reboot = new PVEReboot(_client, _node, _vmid)) : _reboot;
                        }

                        public class PVECurrent {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVECurrent(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Get virtual machine status.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmStatus() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/status/current", null);
                            }

                        }

                        public class PVEStart {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEStart(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Start the container.
                             *
                             * @param debug If set, enables very verbose debug
                             * log-level on start.
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmStart(Boolean debug, Boolean skiplock) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("debug", debug);
                                parameters.put("skiplock", skiplock);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/start", parameters);
                            }

                            /**
                             * Start the container.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmStart() throws JSONException {
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/start", null);
                            }

                        }

                        public class PVEStop {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEStop(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Stop the container. This will abruptly stop all
                             * processes running in the container.
                             *
                             * @param skiplock Ignore locks - only root is
                             * allowed to use this option.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmStop(Boolean skiplock) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("skiplock", skiplock);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/stop", parameters);
                            }

                            /**
                             * Stop the container. This will abruptly stop all
                             * processes running in the container.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmStop() throws JSONException {
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/stop", null);
                            }

                        }

                        public class PVEShutdown {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEShutdown(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Shutdown the container. This will trigger a clean
                             * shutdown of the container, see lxc-stop(1) for
                             * details.
                             *
                             * @param forceStop Make sure the Container stops.
                             * @param timeout Wait maximal timeout seconds.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmShutdown(Boolean forceStop, Integer timeout) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("forceStop", forceStop);
                                parameters.put("timeout", timeout);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/shutdown", parameters);
                            }

                            /**
                             * Shutdown the container. This will trigger a clean
                             * shutdown of the container, see lxc-stop(1) for
                             * details.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmShutdown() throws JSONException {
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/shutdown", null);
                            }

                        }

                        public class PVESuspend {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVESuspend(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Suspend the container. This is experimental.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmSuspend() throws JSONException {
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/suspend", null);
                            }

                        }

                        public class PVEResume {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEResume(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Resume the container.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmResume() throws JSONException {
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/resume", null);
                            }

                        }

                        public class PVEReboot {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEReboot(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Reboot the container by shutting it down, and
                             * starting it again. Applies pending changes.
                             *
                             * @param timeout Wait maximal timeout seconds for
                             * the shutdown.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result vmReboot(Integer timeout) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("timeout", timeout);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/reboot", parameters);
                            }

                            /**
                             * Reboot the container by shutting it down, and
                             * starting it again. Applies pending changes.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result vmReboot() throws JSONException {
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/status/reboot", null);
                            }

                        }

                        /**
                         * Directory index
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmcmdidx() throws JSONException {
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/status", null);
                        }

                    }

                    public class PVESnapshot {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVESnapshot(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        public PVESnapnameItem get(Object snapname) {
                            return new PVESnapnameItem(_client, _node, _vmid, snapname);
                        }

                        public class PVESnapnameItem {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;
                            private final Object _snapname;

                            protected PVESnapnameItem(PveClient client, Object node, Object vmid, Object snapname) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                                _snapname = snapname;
                            }

                            private PVERollback _rollback;

                            public PVERollback getRollback() {
                                return _rollback == null ? (_rollback = new PVERollback(_client, _node, _vmid, _snapname)) : _rollback;
                            }
                            private PVEConfig _config;

                            public PVEConfig getConfig() {
                                return _config == null ? (_config = new PVEConfig(_client, _node, _vmid, _snapname)) : _config;
                            }

                            public class PVERollback {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _snapname;

                                protected PVERollback(PveClient client, Object node, Object vmid, Object snapname) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _snapname = snapname;
                                }

                                /**
                                 * Rollback LXC state to specified snapshot.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result rollback() throws JSONException {
                                    return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "/rollback", null);
                                }

                            }

                            public class PVEConfig {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _snapname;

                                protected PVEConfig(PveClient client, Object node, Object vmid, Object snapname) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _snapname = snapname;
                                }

                                /**
                                 * Get snapshot configuration
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result getSnapshotConfig() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "/config", null);
                                }

                                /**
                                 * Update snapshot metadata.
                                 *
                                 * @param description A textual description or
                                 * comment.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateSnapshotConfig(String description) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("description", description);
                                    return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "/config", parameters);
                                }

                                /**
                                 * Update snapshot metadata.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateSnapshotConfig() throws JSONException {
                                    return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "/config", null);
                                }

                            }

                            /**
                             * Delete a LXC snapshot.
                             *
                             * @param force For removal from config file, even
                             * if removing disk snapshots fails.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result delsnapshot(Boolean force) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("force", force);
                                return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "", parameters);
                            }

                            /**
                             * Delete a LXC snapshot.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result delsnapshot() throws JSONException {
                                return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "", null);
                            }

                            /**
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result snapshotCmdIdx() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot/" + _snapname + "", null);
                            }

                        }

                        /**
                         * List all snapshots.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result list() throws JSONException {
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot", null);
                        }

                        /**
                         * Snapshot a container.
                         *
                         * @param snapname The name of the snapshot.
                         * @param description A textual description or comment.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result snapshot(String snapname, String description) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("snapname", snapname);
                            parameters.put("description", description);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot", parameters);
                        }

                        /**
                         * Snapshot a container.
                         *
                         * @param snapname The name of the snapshot.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result snapshot(String snapname) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("snapname", snapname);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/snapshot", parameters);
                        }

                    }

                    public class PVEFirewall {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEFirewall(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        private PVERules _rules;

                        public PVERules getRules() {
                            return _rules == null ? (_rules = new PVERules(_client, _node, _vmid)) : _rules;
                        }
                        private PVEAliases _aliases;

                        public PVEAliases getAliases() {
                            return _aliases == null ? (_aliases = new PVEAliases(_client, _node, _vmid)) : _aliases;
                        }
                        private PVEIpset _ipset;

                        public PVEIpset getIpset() {
                            return _ipset == null ? (_ipset = new PVEIpset(_client, _node, _vmid)) : _ipset;
                        }
                        private PVEOptions _options;

                        public PVEOptions getOptions() {
                            return _options == null ? (_options = new PVEOptions(_client, _node, _vmid)) : _options;
                        }
                        private PVELog _log;

                        public PVELog getLog() {
                            return _log == null ? (_log = new PVELog(_client, _node, _vmid)) : _log;
                        }
                        private PVERefs _refs;

                        public PVERefs getRefs() {
                            return _refs == null ? (_refs = new PVERefs(_client, _node, _vmid)) : _refs;
                        }

                        public class PVERules {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVERules(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            public PVEPosItem get(Object pos) {
                                return new PVEPosItem(_client, _node, _vmid, pos);
                            }

                            public class PVEPosItem {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _pos;

                                protected PVEPosItem(PveClient client, Object node, Object vmid, Object pos) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _pos = pos;
                                }

                                /**
                                 * Delete rule.
                                 *
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result deleteRule(String digest) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("digest", digest);
                                    return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules/" + _pos + "", parameters);
                                }

                                /**
                                 * Delete rule.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result deleteRule() throws JSONException {
                                    return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules/" + _pos + "", null);
                                }

                                /**
                                 * Get single rule data.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result getRule() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules/" + _pos + "", null);
                                }

                                /**
                                 * Modify rule data.
                                 *
                                 * @param action Rule action ('ACCEPT', 'DROP',
                                 * 'REJECT') or security group name.
                                 * @param comment Descriptive comment.
                                 * @param delete A list of settings you want to
                                 * delete.
                                 * @param dest Restrict packet destination
                                 * address. This can refer to a single IP
                                 * address, an IP set ('+ipsetname') or an IP
                                 * alias definition. You can also specify an
                                 * address range like
                                 * '20.34.101.207-201.3.9.99', or a list of IP
                                 * addresses and networks (entries are separated
                                 * by comma). Please do not mix IPv4 and IPv6
                                 * addresses inside such lists.
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @param dport Restrict TCP/UDP destination
                                 * port. You can use service names or simple
                                 * numbers (0-65535), as defined in
                                 * '/etc/services'. Port ranges can be specified
                                 * with '\d+:\d+', for example '80:85', and you
                                 * can use comma separated list to match several
                                 * ports or ranges.
                                 * @param enable Flag to enable/disable a rule.
                                 * @param icmp_type Specify icmp-type. Only
                                 * valid if proto equals 'icmp'.
                                 * @param iface Network interface name. You have
                                 * to use network configuration key names for
                                 * VMs and containers ('net\d+'). Host related
                                 * rules can use arbitrary strings.
                                 * @param log Log level for firewall rule. Enum:
                                 * emerg,alert,crit,err,warning,notice,info,debug,nolog
                                 * @param macro Use predefined standard macro.
                                 * @param moveto Move rule to new position
                                 * &amp;lt;moveto&amp;gt;. Other arguments are
                                 * ignored.
                                 * @param proto IP protocol. You can use
                                 * protocol names ('tcp'/'udp') or simple
                                 * numbers, as defined in '/etc/protocols'.
                                 * @param source Restrict packet source address.
                                 * This can refer to a single IP address, an IP
                                 * set ('+ipsetname') or an IP alias definition.
                                 * You can also specify an address range like
                                 * '20.34.101.207-201.3.9.99', or a list of IP
                                 * addresses and networks (entries are separated
                                 * by comma). Please do not mix IPv4 and IPv6
                                 * addresses inside such lists.
                                 * @param sport Restrict TCP/UDP source port.
                                 * You can use service names or simple numbers
                                 * (0-65535), as defined in '/etc/services'.
                                 * Port ranges can be specified with '\d+:\d+',
                                 * for example '80:85', and you can use comma
                                 * separated list to match several ports or
                                 * ranges.
                                 * @param type Rule type. Enum: in,out,group
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateRule(String action, String comment, String delete, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer moveto, String proto, String source, String sport, String type) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("action", action);
                                    parameters.put("comment", comment);
                                    parameters.put("delete", delete);
                                    parameters.put("dest", dest);
                                    parameters.put("digest", digest);
                                    parameters.put("dport", dport);
                                    parameters.put("enable", enable);
                                    parameters.put("icmp-type", icmp_type);
                                    parameters.put("iface", iface);
                                    parameters.put("log", log);
                                    parameters.put("macro", macro);
                                    parameters.put("moveto", moveto);
                                    parameters.put("proto", proto);
                                    parameters.put("source", source);
                                    parameters.put("sport", sport);
                                    parameters.put("type", type);
                                    return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules/" + _pos + "", parameters);
                                }

                                /**
                                 * Modify rule data.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateRule() throws JSONException {
                                    return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules/" + _pos + "", null);
                                }

                            }

                            /**
                             * List rules.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getRules() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules", null);
                            }

                            /**
                             * Create new rule.
                             *
                             * @param action Rule action ('ACCEPT', 'DROP',
                             * 'REJECT') or security group name.
                             * @param type Rule type. Enum: in,out,group
                             * @param comment Descriptive comment.
                             * @param dest Restrict packet destination address.
                             * This can refer to a single IP address, an IP set
                             * ('+ipsetname') or an IP alias definition. You can
                             * also specify an address range like
                             * '20.34.101.207-201.3.9.99', or a list of IP
                             * addresses and networks (entries are separated by
                             * comma). Please do not mix IPv4 and IPv6 addresses
                             * inside such lists.
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param dport Restrict TCP/UDP destination port.
                             * You can use service names or simple numbers
                             * (0-65535), as defined in '/etc/services'. Port
                             * ranges can be specified with '\d+:\d+', for
                             * example '80:85', and you can use comma separated
                             * list to match several ports or ranges.
                             * @param enable Flag to enable/disable a rule.
                             * @param icmp_type Specify icmp-type. Only valid if
                             * proto equals 'icmp'.
                             * @param iface Network interface name. You have to
                             * use network configuration key names for VMs and
                             * containers ('net\d+'). Host related rules can use
                             * arbitrary strings.
                             * @param log Log level for firewall rule. Enum:
                             * emerg,alert,crit,err,warning,notice,info,debug,nolog
                             * @param macro Use predefined standard macro.
                             * @param pos Update rule at position
                             * &amp;lt;pos&amp;gt;.
                             * @param proto IP protocol. You can use protocol
                             * names ('tcp'/'udp') or simple numbers, as defined
                             * in '/etc/protocols'.
                             * @param source Restrict packet source address.
                             * This can refer to a single IP address, an IP set
                             * ('+ipsetname') or an IP alias definition. You can
                             * also specify an address range like
                             * '20.34.101.207-201.3.9.99', or a list of IP
                             * addresses and networks (entries are separated by
                             * comma). Please do not mix IPv4 and IPv6 addresses
                             * inside such lists.
                             * @param sport Restrict TCP/UDP source port. You
                             * can use service names or simple numbers
                             * (0-65535), as defined in '/etc/services'. Port
                             * ranges can be specified with '\d+:\d+', for
                             * example '80:85', and you can use comma separated
                             * list to match several ports or ranges.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createRule(String action, String type, String comment, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer pos, String proto, String source, String sport) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("action", action);
                                parameters.put("type", type);
                                parameters.put("comment", comment);
                                parameters.put("dest", dest);
                                parameters.put("digest", digest);
                                parameters.put("dport", dport);
                                parameters.put("enable", enable);
                                parameters.put("icmp-type", icmp_type);
                                parameters.put("iface", iface);
                                parameters.put("log", log);
                                parameters.put("macro", macro);
                                parameters.put("pos", pos);
                                parameters.put("proto", proto);
                                parameters.put("source", source);
                                parameters.put("sport", sport);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules", parameters);
                            }

                            /**
                             * Create new rule.
                             *
                             * @param action Rule action ('ACCEPT', 'DROP',
                             * 'REJECT') or security group name.
                             * @param type Rule type. Enum: in,out,group
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createRule(String action, String type) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("action", action);
                                parameters.put("type", type);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/rules", parameters);
                            }

                        }

                        public class PVEAliases {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEAliases(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            public PVENameItem get(Object name) {
                                return new PVENameItem(_client, _node, _vmid, name);
                            }

                            public class PVENameItem {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _name;

                                protected PVENameItem(PveClient client, Object node, Object vmid, Object name) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _name = name;
                                }

                                /**
                                 * Remove IP or Network alias.
                                 *
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result removeAlias(String digest) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("digest", digest);
                                    return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases/" + _name + "", parameters);
                                }

                                /**
                                 * Remove IP or Network alias.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result removeAlias() throws JSONException {
                                    return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases/" + _name + "", null);
                                }

                                /**
                                 * Read alias.
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result readAlias() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases/" + _name + "", null);
                                }

                                /**
                                 * Update IP or Network alias.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @param comment
                                 * @param digest Prevent changes if current
                                 * configuration file has different SHA1 digest.
                                 * This can be used to prevent concurrent
                                 * modifications.
                                 * @param rename Rename an existing alias.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateAlias(String cidr, String comment, String digest, String rename) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    parameters.put("comment", comment);
                                    parameters.put("digest", digest);
                                    parameters.put("rename", rename);
                                    return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases/" + _name + "", parameters);
                                }

                                /**
                                 * Update IP or Network alias.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result updateAlias(String cidr) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases/" + _name + "", parameters);
                                }

                            }

                            /**
                             * List aliases
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getAliases() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases", null);
                            }

                            /**
                             * Create IP or Network Alias.
                             *
                             * @param cidr Network/IP specification in CIDR
                             * format.
                             * @param name Alias name.
                             * @param comment
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createAlias(String cidr, String name, String comment) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("cidr", cidr);
                                parameters.put("name", name);
                                parameters.put("comment", comment);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases", parameters);
                            }

                            /**
                             * Create IP or Network Alias.
                             *
                             * @param cidr Network/IP specification in CIDR
                             * format.
                             * @param name Alias name.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createAlias(String cidr, String name) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("cidr", cidr);
                                parameters.put("name", name);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/aliases", parameters);
                            }

                        }

                        public class PVEIpset {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEIpset(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            public PVENameItem get(Object name) {
                                return new PVENameItem(_client, _node, _vmid, name);
                            }

                            public class PVENameItem {

                                private final PveClient _client;
                                private final Object _node;
                                private final Object _vmid;
                                private final Object _name;

                                protected PVENameItem(PveClient client, Object node, Object vmid, Object name) {
                                    _client = client;
                                    _node = node;
                                    _vmid = vmid;
                                    _name = name;
                                }

                                public PVECidrItem get(Object cidr) {
                                    return new PVECidrItem(_client, _node, _vmid, _name, cidr);
                                }

                                public class PVECidrItem {

                                    private final PveClient _client;
                                    private final Object _node;
                                    private final Object _vmid;
                                    private final Object _name;
                                    private final Object _cidr;

                                    protected PVECidrItem(PveClient client, Object node, Object vmid, Object name, Object cidr) {
                                        _client = client;
                                        _node = node;
                                        _vmid = vmid;
                                        _name = name;
                                        _cidr = cidr;
                                    }

                                    /**
                                     * Remove IP or Network from IPSet.
                                     *
                                     * @param digest Prevent changes if current
                                     * configuration file has different SHA1
                                     * digest. This can be used to prevent
                                     * concurrent modifications.
                                     * @return Result
                                     * @throws JSONException
                                     */
                                    public Result removeIp(String digest) throws JSONException {
                                        Map<String, Object> parameters = new HashMap<>();
                                        parameters.put("digest", digest);
                                        return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", parameters);
                                    }

                                    /**
                                     * Remove IP or Network from IPSet.
                                     *
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result removeIp() throws JSONException {
                                        return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", null);
                                    }

                                    /**
                                     * Read IP or Network settings from IPSet.
                                     *
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result readIp() throws JSONException {
                                        return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", null);
                                    }

                                    /**
                                     * Update IP or Network settings
                                     *
                                     * @param comment
                                     * @param digest Prevent changes if current
                                     * configuration file has different SHA1
                                     * digest. This can be used to prevent
                                     * concurrent modifications.
                                     * @param nomatch
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result updateIp(String comment, String digest, Boolean nomatch) throws JSONException {
                                        Map<String, Object> parameters = new HashMap<>();
                                        parameters.put("comment", comment);
                                        parameters.put("digest", digest);
                                        parameters.put("nomatch", nomatch);
                                        return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", parameters);
                                    }

                                    /**
                                     * Update IP or Network settings
                                     *
                                     * @return Result
                                     * @throws JSONException
                                     */

                                    public Result updateIp() throws JSONException {
                                        return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "/" + _cidr + "", null);
                                    }

                                }

                                /**
                                 * Delete IPSet
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */
                                public Result deleteIpset() throws JSONException {
                                    return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "", null);
                                }

                                /**
                                 * List IPSet content
                                 *
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result getIpset() throws JSONException {
                                    return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "", null);
                                }

                                /**
                                 * Add IP or Network to IPSet.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @param comment
                                 * @param nomatch
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result createIp(String cidr, String comment, Boolean nomatch) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    parameters.put("comment", comment);
                                    parameters.put("nomatch", nomatch);
                                    return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "", parameters);
                                }

                                /**
                                 * Add IP or Network to IPSet.
                                 *
                                 * @param cidr Network/IP specification in CIDR
                                 * format.
                                 * @return Result
                                 * @throws JSONException
                                 */

                                public Result createIp(String cidr) throws JSONException {
                                    Map<String, Object> parameters = new HashMap<>();
                                    parameters.put("cidr", cidr);
                                    return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset/" + _name + "", parameters);
                                }

                            }

                            /**
                             * List IPSets
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result ipsetIndex() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset", null);
                            }

                            /**
                             * Create new IPSet
                             *
                             * @param name IP set name.
                             * @param comment
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param rename Rename an existing IPSet. You can
                             * set 'rename' to the same value as 'name' to
                             * update the 'comment' of an existing IPSet.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createIpset(String name, String comment, String digest, String rename) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("name", name);
                                parameters.put("comment", comment);
                                parameters.put("digest", digest);
                                parameters.put("rename", rename);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset", parameters);
                            }

                            /**
                             * Create new IPSet
                             *
                             * @param name IP set name.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result createIpset(String name) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("name", name);
                                return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/ipset", parameters);
                            }

                        }

                        public class PVEOptions {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVEOptions(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Get VM firewall options.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result getOptions() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/options", null);
                            }

                            /**
                             * Set Firewall options.
                             *
                             * @param delete A list of settings you want to
                             * delete.
                             * @param dhcp Enable DHCP.
                             * @param digest Prevent changes if current
                             * configuration file has different SHA1 digest.
                             * This can be used to prevent concurrent
                             * modifications.
                             * @param enable Enable/disable firewall rules.
                             * @param ipfilter Enable default IP filters. This
                             * is equivalent to adding an empty
                             * ipfilter-net&amp;lt;id&amp;gt; ipset for every
                             * interface. Such ipsets implicitly contain sane
                             * default restrictions such as restricting IPv6
                             * link local addresses to the one derived from the
                             * interface's MAC address. For containers the
                             * configured IP addresses will be implicitly added.
                             * @param log_level_in Log level for incoming
                             * traffic. Enum:
                             * emerg,alert,crit,err,warning,notice,info,debug,nolog
                             * @param log_level_out Log level for outgoing
                             * traffic. Enum:
                             * emerg,alert,crit,err,warning,notice,info,debug,nolog
                             * @param macfilter Enable/disable MAC address
                             * filter.
                             * @param ndp Enable NDP (Neighbor Discovery
                             * Protocol).
                             * @param policy_in Input policy. Enum:
                             * ACCEPT,REJECT,DROP
                             * @param policy_out Output policy. Enum:
                             * ACCEPT,REJECT,DROP
                             * @param radv Allow sending Router Advertisement.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result setOptions(String delete, Boolean dhcp, String digest, Boolean enable, Boolean ipfilter, String log_level_in, String log_level_out, Boolean macfilter, Boolean ndp, String policy_in, String policy_out, Boolean radv) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("delete", delete);
                                parameters.put("dhcp", dhcp);
                                parameters.put("digest", digest);
                                parameters.put("enable", enable);
                                parameters.put("ipfilter", ipfilter);
                                parameters.put("log_level_in", log_level_in);
                                parameters.put("log_level_out", log_level_out);
                                parameters.put("macfilter", macfilter);
                                parameters.put("ndp", ndp);
                                parameters.put("policy_in", policy_in);
                                parameters.put("policy_out", policy_out);
                                parameters.put("radv", radv);
                                return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/options", parameters);
                            }

                            /**
                             * Set Firewall options.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result setOptions() throws JSONException {
                                return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/options", null);
                            }

                        }

                        public class PVELog {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVELog(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Read firewall log
                             *
                             * @param limit
                             * @param start
                             * @return Result
                             * @throws JSONException
                             */
                            public Result log(Integer limit, Integer start) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("limit", limit);
                                parameters.put("start", start);
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/log", parameters);
                            }

                            /**
                             * Read firewall log
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result log() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/log", null);
                            }

                        }

                        public class PVERefs {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _vmid;

                            protected PVERefs(PveClient client, Object node, Object vmid) {
                                _client = client;
                                _node = node;
                                _vmid = vmid;
                            }

                            /**
                             * Lists possible IPSet/Alias reference which are
                             * allowed in source/dest properties.
                             *
                             * @param type Only list references of specified
                             * type. Enum: alias,ipset
                             * @return Result
                             * @throws JSONException
                             */
                            public Result refs(String type) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("type", type);
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/refs", parameters);
                            }

                            /**
                             * Lists possible IPSet/Alias reference which are
                             * allowed in source/dest properties.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result refs() throws JSONException {
                                return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall/refs", null);
                            }

                        }

                        /**
                         * Directory index.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result index() throws JSONException {
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/firewall", null);
                        }

                    }

                    public class PVERrd {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVERrd(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Read VM RRD statistics (returns PNG)
                         *
                         * @param ds The list of datasources you want to
                         * display.
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @param cf The RRD consolidation function Enum:
                         * AVERAGE,MAX
                         * @return Result
                         * @throws JSONException
                         */
                        public Result rrd(String ds, String timeframe, String cf) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("ds", ds);
                            parameters.put("timeframe", timeframe);
                            parameters.put("cf", cf);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/rrd", parameters);
                        }

                        /**
                         * Read VM RRD statistics (returns PNG)
                         *
                         * @param ds The list of datasources you want to
                         * display.
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @return Result
                         * @throws JSONException
                         */

                        public Result rrd(String ds, String timeframe) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("ds", ds);
                            parameters.put("timeframe", timeframe);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/rrd", parameters);
                        }

                    }

                    public class PVERrddata {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVERrddata(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Read VM RRD statistics
                         *
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @param cf The RRD consolidation function Enum:
                         * AVERAGE,MAX
                         * @return Result
                         * @throws JSONException
                         */
                        public Result rrddata(String timeframe, String cf) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("timeframe", timeframe);
                            parameters.put("cf", cf);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/rrddata", parameters);
                        }

                        /**
                         * Read VM RRD statistics
                         *
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @return Result
                         * @throws JSONException
                         */

                        public Result rrddata(String timeframe) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("timeframe", timeframe);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/rrddata", parameters);
                        }

                    }

                    public class PVEVncproxy {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEVncproxy(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Creates a TCP VNC proxy connections.
                         *
                         * @param height sets the height of the console in
                         * pixels.
                         * @param websocket use websocket instead of standard
                         * VNC.
                         * @param width sets the width of the console in pixels.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vncproxy(Integer height, Boolean websocket, Integer width) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("height", height);
                            parameters.put("websocket", websocket);
                            parameters.put("width", width);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/vncproxy", parameters);
                        }

                        /**
                         * Creates a TCP VNC proxy connections.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vncproxy() throws JSONException {
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/vncproxy", null);
                        }

                    }

                    public class PVETermproxy {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVETermproxy(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Creates a TCP proxy connection.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result termproxy() throws JSONException {
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/termproxy", null);
                        }

                    }

                    public class PVEVncwebsocket {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEVncwebsocket(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Opens a weksocket for VNC traffic.
                         *
                         * @param port Port number returned by previous vncproxy
                         * call.
                         * @param vncticket Ticket from previous call to
                         * vncproxy.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vncwebsocket(int port, String vncticket) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("port", port);
                            parameters.put("vncticket", vncticket);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/vncwebsocket", parameters);
                        }

                    }

                    public class PVESpiceproxy {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVESpiceproxy(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Returns a SPICE configuration to connect to the CT.
                         *
                         * @param proxy SPICE proxy server. This can be used by
                         * the client to specify the proxy server. All nodes in
                         * a cluster runs 'spiceproxy', so it is up to the
                         * client to choose one. By default, we return the node
                         * where the VM is currently running. As reasonable
                         * setting is to use same node you use to connect to the
                         * API (This is window.location.hostname for the JS
                         * GUI).
                         * @return Result
                         * @throws JSONException
                         */
                        public Result spiceproxy(String proxy) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("proxy", proxy);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/spiceproxy", parameters);
                        }

                        /**
                         * Returns a SPICE configuration to connect to the CT.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result spiceproxy() throws JSONException {
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/spiceproxy", null);
                        }

                    }

                    public class PVEMigrate {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEMigrate(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Migrate the container to another node. Creates a new
                         * migration task.
                         *
                         * @param target Target node.
                         * @param bwlimit Override I/O bandwidth limit (in
                         * KiB/s).
                         * @param online Use online/live migration.
                         * @param restart Use restart migration
                         * @param timeout Timeout in seconds for shutdown for
                         * restart migration
                         * @return Result
                         * @throws JSONException
                         */
                        public Result migrateVm(String target, Float bwlimit, Boolean online, Boolean restart, Integer timeout) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("target", target);
                            parameters.put("bwlimit", bwlimit);
                            parameters.put("online", online);
                            parameters.put("restart", restart);
                            parameters.put("timeout", timeout);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/migrate", parameters);
                        }

                        /**
                         * Migrate the container to another node. Creates a new
                         * migration task.
                         *
                         * @param target Target node.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result migrateVm(String target) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("target", target);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/migrate", parameters);
                        }

                    }

                    public class PVEFeature {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEFeature(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Check if feature for virtual machine is available.
                         *
                         * @param feature Feature to check. Enum:
                         * snapshot,clone,copy
                         * @param snapname The name of the snapshot.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmFeature(String feature, String snapname) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("feature", feature);
                            parameters.put("snapname", snapname);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/feature", parameters);
                        }

                        /**
                         * Check if feature for virtual machine is available.
                         *
                         * @param feature Feature to check. Enum:
                         * snapshot,clone,copy
                         * @return Result
                         * @throws JSONException
                         */

                        public Result vmFeature(String feature) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("feature", feature);
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/feature", parameters);
                        }

                    }

                    public class PVETemplate {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVETemplate(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Create a Template.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result template() throws JSONException {
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/template", null);
                        }

                    }

                    public class PVEClone {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEClone(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Create a container clone/copy
                         *
                         * @param newid VMID for the clone.
                         * @param bwlimit Override I/O bandwidth limit (in
                         * KiB/s).
                         * @param description Description for the new CT.
                         * @param full Create a full copy of all disks. This is
                         * always done when you clone a normal CT. For CT
                         * templates, we try to create a linked clone by
                         * default.
                         * @param hostname Set a hostname for the new CT.
                         * @param pool Add the new CT to the specified pool.
                         * @param snapname The name of the snapshot.
                         * @param storage Target storage for full clone.
                         * @param target Target node. Only allowed if the
                         * original VM is on shared storage.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result cloneVm(int newid, Float bwlimit, String description, Boolean full, String hostname, String pool, String snapname, String storage, String target) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("newid", newid);
                            parameters.put("bwlimit", bwlimit);
                            parameters.put("description", description);
                            parameters.put("full", full);
                            parameters.put("hostname", hostname);
                            parameters.put("pool", pool);
                            parameters.put("snapname", snapname);
                            parameters.put("storage", storage);
                            parameters.put("target", target);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/clone", parameters);
                        }

                        /**
                         * Create a container clone/copy
                         *
                         * @param newid VMID for the clone.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result cloneVm(int newid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("newid", newid);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/clone", parameters);
                        }

                    }

                    public class PVEResize {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEResize(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Resize a container mount point.
                         *
                         * @param disk The disk you want to resize. Enum:
                         * rootfs,mp0,mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12,mp13,mp14,mp15,mp16,mp17,mp18,mp19,mp20,mp21,mp22,mp23,mp24,mp25,mp26,mp27,mp28,mp29,mp30,mp31,mp32,mp33,mp34,mp35,mp36,mp37,mp38,mp39,mp40,mp41,mp42,mp43,mp44,mp45,mp46,mp47,mp48,mp49,mp50,mp51,mp52,mp53,mp54,mp55,mp56,mp57,mp58,mp59,mp60,mp61,mp62,mp63,mp64,mp65,mp66,mp67,mp68,mp69,mp70,mp71,mp72,mp73,mp74,mp75,mp76,mp77,mp78,mp79,mp80,mp81,mp82,mp83,mp84,mp85,mp86,mp87,mp88,mp89,mp90,mp91,mp92,mp93,mp94,mp95,mp96,mp97,mp98,mp99,mp100,mp101,mp102,mp103,mp104,mp105,mp106,mp107,mp108,mp109,mp110,mp111,mp112,mp113,mp114,mp115,mp116,mp117,mp118,mp119,mp120,mp121,mp122,mp123,mp124,mp125,mp126,mp127,mp128,mp129,mp130,mp131,mp132,mp133,mp134,mp135,mp136,mp137,mp138,mp139,mp140,mp141,mp142,mp143,mp144,mp145,mp146,mp147,mp148,mp149,mp150,mp151,mp152,mp153,mp154,mp155,mp156,mp157,mp158,mp159,mp160,mp161,mp162,mp163,mp164,mp165,mp166,mp167,mp168,mp169,mp170,mp171,mp172,mp173,mp174,mp175,mp176,mp177,mp178,mp179,mp180,mp181,mp182,mp183,mp184,mp185,mp186,mp187,mp188,mp189,mp190,mp191,mp192,mp193,mp194,mp195,mp196,mp197,mp198,mp199,mp200,mp201,mp202,mp203,mp204,mp205,mp206,mp207,mp208,mp209,mp210,mp211,mp212,mp213,mp214,mp215,mp216,mp217,mp218,mp219,mp220,mp221,mp222,mp223,mp224,mp225,mp226,mp227,mp228,mp229,mp230,mp231,mp232,mp233,mp234,mp235,mp236,mp237,mp238,mp239,mp240,mp241,mp242,mp243,mp244,mp245,mp246,mp247,mp248,mp249,mp250,mp251,mp252,mp253,mp254,mp255
                         * @param size The new size. With the '+' sign the value
                         * is added to the actual size of the volume and without
                         * it, the value is taken as an absolute one. Shrinking
                         * disk size is not supported.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result resizeVm(String disk, String size, String digest) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            parameters.put("size", size);
                            parameters.put("digest", digest);
                            return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/resize", parameters);
                        }

                        /**
                         * Resize a container mount point.
                         *
                         * @param disk The disk you want to resize. Enum:
                         * rootfs,mp0,mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12,mp13,mp14,mp15,mp16,mp17,mp18,mp19,mp20,mp21,mp22,mp23,mp24,mp25,mp26,mp27,mp28,mp29,mp30,mp31,mp32,mp33,mp34,mp35,mp36,mp37,mp38,mp39,mp40,mp41,mp42,mp43,mp44,mp45,mp46,mp47,mp48,mp49,mp50,mp51,mp52,mp53,mp54,mp55,mp56,mp57,mp58,mp59,mp60,mp61,mp62,mp63,mp64,mp65,mp66,mp67,mp68,mp69,mp70,mp71,mp72,mp73,mp74,mp75,mp76,mp77,mp78,mp79,mp80,mp81,mp82,mp83,mp84,mp85,mp86,mp87,mp88,mp89,mp90,mp91,mp92,mp93,mp94,mp95,mp96,mp97,mp98,mp99,mp100,mp101,mp102,mp103,mp104,mp105,mp106,mp107,mp108,mp109,mp110,mp111,mp112,mp113,mp114,mp115,mp116,mp117,mp118,mp119,mp120,mp121,mp122,mp123,mp124,mp125,mp126,mp127,mp128,mp129,mp130,mp131,mp132,mp133,mp134,mp135,mp136,mp137,mp138,mp139,mp140,mp141,mp142,mp143,mp144,mp145,mp146,mp147,mp148,mp149,mp150,mp151,mp152,mp153,mp154,mp155,mp156,mp157,mp158,mp159,mp160,mp161,mp162,mp163,mp164,mp165,mp166,mp167,mp168,mp169,mp170,mp171,mp172,mp173,mp174,mp175,mp176,mp177,mp178,mp179,mp180,mp181,mp182,mp183,mp184,mp185,mp186,mp187,mp188,mp189,mp190,mp191,mp192,mp193,mp194,mp195,mp196,mp197,mp198,mp199,mp200,mp201,mp202,mp203,mp204,mp205,mp206,mp207,mp208,mp209,mp210,mp211,mp212,mp213,mp214,mp215,mp216,mp217,mp218,mp219,mp220,mp221,mp222,mp223,mp224,mp225,mp226,mp227,mp228,mp229,mp230,mp231,mp232,mp233,mp234,mp235,mp236,mp237,mp238,mp239,mp240,mp241,mp242,mp243,mp244,mp245,mp246,mp247,mp248,mp249,mp250,mp251,mp252,mp253,mp254,mp255
                         * @param size The new size. With the '+' sign the value
                         * is added to the actual size of the volume and without
                         * it, the value is taken as an absolute one. Shrinking
                         * disk size is not supported.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result resizeVm(String disk, String size) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("disk", disk);
                            parameters.put("size", size);
                            return _client.set("/nodes/" + _node + "/lxc/" + _vmid + "/resize", parameters);
                        }

                    }

                    public class PVEMoveVolume {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEMoveVolume(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Move a rootfs-/mp-volume to a different storage or to
                         * a different container.
                         *
                         * @param volume Volume which will be moved. Enum:
                         * rootfs,mp0,mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12,mp13,mp14,mp15,mp16,mp17,mp18,mp19,mp20,mp21,mp22,mp23,mp24,mp25,mp26,mp27,mp28,mp29,mp30,mp31,mp32,mp33,mp34,mp35,mp36,mp37,mp38,mp39,mp40,mp41,mp42,mp43,mp44,mp45,mp46,mp47,mp48,mp49,mp50,mp51,mp52,mp53,mp54,mp55,mp56,mp57,mp58,mp59,mp60,mp61,mp62,mp63,mp64,mp65,mp66,mp67,mp68,mp69,mp70,mp71,mp72,mp73,mp74,mp75,mp76,mp77,mp78,mp79,mp80,mp81,mp82,mp83,mp84,mp85,mp86,mp87,mp88,mp89,mp90,mp91,mp92,mp93,mp94,mp95,mp96,mp97,mp98,mp99,mp100,mp101,mp102,mp103,mp104,mp105,mp106,mp107,mp108,mp109,mp110,mp111,mp112,mp113,mp114,mp115,mp116,mp117,mp118,mp119,mp120,mp121,mp122,mp123,mp124,mp125,mp126,mp127,mp128,mp129,mp130,mp131,mp132,mp133,mp134,mp135,mp136,mp137,mp138,mp139,mp140,mp141,mp142,mp143,mp144,mp145,mp146,mp147,mp148,mp149,mp150,mp151,mp152,mp153,mp154,mp155,mp156,mp157,mp158,mp159,mp160,mp161,mp162,mp163,mp164,mp165,mp166,mp167,mp168,mp169,mp170,mp171,mp172,mp173,mp174,mp175,mp176,mp177,mp178,mp179,mp180,mp181,mp182,mp183,mp184,mp185,mp186,mp187,mp188,mp189,mp190,mp191,mp192,mp193,mp194,mp195,mp196,mp197,mp198,mp199,mp200,mp201,mp202,mp203,mp204,mp205,mp206,mp207,mp208,mp209,mp210,mp211,mp212,mp213,mp214,mp215,mp216,mp217,mp218,mp219,mp220,mp221,mp222,mp223,mp224,mp225,mp226,mp227,mp228,mp229,mp230,mp231,mp232,mp233,mp234,mp235,mp236,mp237,mp238,mp239,mp240,mp241,mp242,mp243,mp244,mp245,mp246,mp247,mp248,mp249,mp250,mp251,mp252,mp253,mp254,mp255,unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99,unused100,unused101,unused102,unused103,unused104,unused105,unused106,unused107,unused108,unused109,unused110,unused111,unused112,unused113,unused114,unused115,unused116,unused117,unused118,unused119,unused120,unused121,unused122,unused123,unused124,unused125,unused126,unused127,unused128,unused129,unused130,unused131,unused132,unused133,unused134,unused135,unused136,unused137,unused138,unused139,unused140,unused141,unused142,unused143,unused144,unused145,unused146,unused147,unused148,unused149,unused150,unused151,unused152,unused153,unused154,unused155,unused156,unused157,unused158,unused159,unused160,unused161,unused162,unused163,unused164,unused165,unused166,unused167,unused168,unused169,unused170,unused171,unused172,unused173,unused174,unused175,unused176,unused177,unused178,unused179,unused180,unused181,unused182,unused183,unused184,unused185,unused186,unused187,unused188,unused189,unused190,unused191,unused192,unused193,unused194,unused195,unused196,unused197,unused198,unused199,unused200,unused201,unused202,unused203,unused204,unused205,unused206,unused207,unused208,unused209,unused210,unused211,unused212,unused213,unused214,unused215,unused216,unused217,unused218,unused219,unused220,unused221,unused222,unused223,unused224,unused225,unused226,unused227,unused228,unused229,unused230,unused231,unused232,unused233,unused234,unused235,unused236,unused237,unused238,unused239,unused240,unused241,unused242,unused243,unused244,unused245,unused246,unused247,unused248,unused249,unused250,unused251,unused252,unused253,unused254,unused255
                         * @param bwlimit Override I/O bandwidth limit (in
                         * KiB/s).
                         * @param delete Delete the original volume after
                         * successful copy. By default the original is kept as
                         * an unused volume entry.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 " . "digest.
                         * This can be used to prevent concurrent modifications.
                         * @param storage Target Storage.
                         * @param target_digest Prevent changes if current
                         * configuration file of the target " . "container has a
                         * different SHA1 digest. This can be used to prevent "
                         * . "concurrent modifications.
                         * @param target_vmid The (unique) ID of the VM.
                         * @param target_volume The config key the volume will
                         * be moved to. Default is the source volume key. Enum:
                         * rootfs,mp0,mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12,mp13,mp14,mp15,mp16,mp17,mp18,mp19,mp20,mp21,mp22,mp23,mp24,mp25,mp26,mp27,mp28,mp29,mp30,mp31,mp32,mp33,mp34,mp35,mp36,mp37,mp38,mp39,mp40,mp41,mp42,mp43,mp44,mp45,mp46,mp47,mp48,mp49,mp50,mp51,mp52,mp53,mp54,mp55,mp56,mp57,mp58,mp59,mp60,mp61,mp62,mp63,mp64,mp65,mp66,mp67,mp68,mp69,mp70,mp71,mp72,mp73,mp74,mp75,mp76,mp77,mp78,mp79,mp80,mp81,mp82,mp83,mp84,mp85,mp86,mp87,mp88,mp89,mp90,mp91,mp92,mp93,mp94,mp95,mp96,mp97,mp98,mp99,mp100,mp101,mp102,mp103,mp104,mp105,mp106,mp107,mp108,mp109,mp110,mp111,mp112,mp113,mp114,mp115,mp116,mp117,mp118,mp119,mp120,mp121,mp122,mp123,mp124,mp125,mp126,mp127,mp128,mp129,mp130,mp131,mp132,mp133,mp134,mp135,mp136,mp137,mp138,mp139,mp140,mp141,mp142,mp143,mp144,mp145,mp146,mp147,mp148,mp149,mp150,mp151,mp152,mp153,mp154,mp155,mp156,mp157,mp158,mp159,mp160,mp161,mp162,mp163,mp164,mp165,mp166,mp167,mp168,mp169,mp170,mp171,mp172,mp173,mp174,mp175,mp176,mp177,mp178,mp179,mp180,mp181,mp182,mp183,mp184,mp185,mp186,mp187,mp188,mp189,mp190,mp191,mp192,mp193,mp194,mp195,mp196,mp197,mp198,mp199,mp200,mp201,mp202,mp203,mp204,mp205,mp206,mp207,mp208,mp209,mp210,mp211,mp212,mp213,mp214,mp215,mp216,mp217,mp218,mp219,mp220,mp221,mp222,mp223,mp224,mp225,mp226,mp227,mp228,mp229,mp230,mp231,mp232,mp233,mp234,mp235,mp236,mp237,mp238,mp239,mp240,mp241,mp242,mp243,mp244,mp245,mp246,mp247,mp248,mp249,mp250,mp251,mp252,mp253,mp254,mp255,unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99,unused100,unused101,unused102,unused103,unused104,unused105,unused106,unused107,unused108,unused109,unused110,unused111,unused112,unused113,unused114,unused115,unused116,unused117,unused118,unused119,unused120,unused121,unused122,unused123,unused124,unused125,unused126,unused127,unused128,unused129,unused130,unused131,unused132,unused133,unused134,unused135,unused136,unused137,unused138,unused139,unused140,unused141,unused142,unused143,unused144,unused145,unused146,unused147,unused148,unused149,unused150,unused151,unused152,unused153,unused154,unused155,unused156,unused157,unused158,unused159,unused160,unused161,unused162,unused163,unused164,unused165,unused166,unused167,unused168,unused169,unused170,unused171,unused172,unused173,unused174,unused175,unused176,unused177,unused178,unused179,unused180,unused181,unused182,unused183,unused184,unused185,unused186,unused187,unused188,unused189,unused190,unused191,unused192,unused193,unused194,unused195,unused196,unused197,unused198,unused199,unused200,unused201,unused202,unused203,unused204,unused205,unused206,unused207,unused208,unused209,unused210,unused211,unused212,unused213,unused214,unused215,unused216,unused217,unused218,unused219,unused220,unused221,unused222,unused223,unused224,unused225,unused226,unused227,unused228,unused229,unused230,unused231,unused232,unused233,unused234,unused235,unused236,unused237,unused238,unused239,unused240,unused241,unused242,unused243,unused244,unused245,unused246,unused247,unused248,unused249,unused250,unused251,unused252,unused253,unused254,unused255
                         * @return Result
                         * @throws JSONException
                         */
                        public Result moveVolume(String volume, Float bwlimit, Boolean delete, String digest, String storage, String target_digest, Integer target_vmid, String target_volume) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("volume", volume);
                            parameters.put("bwlimit", bwlimit);
                            parameters.put("delete", delete);
                            parameters.put("digest", digest);
                            parameters.put("storage", storage);
                            parameters.put("target-digest", target_digest);
                            parameters.put("target-vmid", target_vmid);
                            parameters.put("target-volume", target_volume);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/move_volume", parameters);
                        }

                        /**
                         * Move a rootfs-/mp-volume to a different storage or to
                         * a different container.
                         *
                         * @param volume Volume which will be moved. Enum:
                         * rootfs,mp0,mp1,mp2,mp3,mp4,mp5,mp6,mp7,mp8,mp9,mp10,mp11,mp12,mp13,mp14,mp15,mp16,mp17,mp18,mp19,mp20,mp21,mp22,mp23,mp24,mp25,mp26,mp27,mp28,mp29,mp30,mp31,mp32,mp33,mp34,mp35,mp36,mp37,mp38,mp39,mp40,mp41,mp42,mp43,mp44,mp45,mp46,mp47,mp48,mp49,mp50,mp51,mp52,mp53,mp54,mp55,mp56,mp57,mp58,mp59,mp60,mp61,mp62,mp63,mp64,mp65,mp66,mp67,mp68,mp69,mp70,mp71,mp72,mp73,mp74,mp75,mp76,mp77,mp78,mp79,mp80,mp81,mp82,mp83,mp84,mp85,mp86,mp87,mp88,mp89,mp90,mp91,mp92,mp93,mp94,mp95,mp96,mp97,mp98,mp99,mp100,mp101,mp102,mp103,mp104,mp105,mp106,mp107,mp108,mp109,mp110,mp111,mp112,mp113,mp114,mp115,mp116,mp117,mp118,mp119,mp120,mp121,mp122,mp123,mp124,mp125,mp126,mp127,mp128,mp129,mp130,mp131,mp132,mp133,mp134,mp135,mp136,mp137,mp138,mp139,mp140,mp141,mp142,mp143,mp144,mp145,mp146,mp147,mp148,mp149,mp150,mp151,mp152,mp153,mp154,mp155,mp156,mp157,mp158,mp159,mp160,mp161,mp162,mp163,mp164,mp165,mp166,mp167,mp168,mp169,mp170,mp171,mp172,mp173,mp174,mp175,mp176,mp177,mp178,mp179,mp180,mp181,mp182,mp183,mp184,mp185,mp186,mp187,mp188,mp189,mp190,mp191,mp192,mp193,mp194,mp195,mp196,mp197,mp198,mp199,mp200,mp201,mp202,mp203,mp204,mp205,mp206,mp207,mp208,mp209,mp210,mp211,mp212,mp213,mp214,mp215,mp216,mp217,mp218,mp219,mp220,mp221,mp222,mp223,mp224,mp225,mp226,mp227,mp228,mp229,mp230,mp231,mp232,mp233,mp234,mp235,mp236,mp237,mp238,mp239,mp240,mp241,mp242,mp243,mp244,mp245,mp246,mp247,mp248,mp249,mp250,mp251,mp252,mp253,mp254,mp255,unused0,unused1,unused2,unused3,unused4,unused5,unused6,unused7,unused8,unused9,unused10,unused11,unused12,unused13,unused14,unused15,unused16,unused17,unused18,unused19,unused20,unused21,unused22,unused23,unused24,unused25,unused26,unused27,unused28,unused29,unused30,unused31,unused32,unused33,unused34,unused35,unused36,unused37,unused38,unused39,unused40,unused41,unused42,unused43,unused44,unused45,unused46,unused47,unused48,unused49,unused50,unused51,unused52,unused53,unused54,unused55,unused56,unused57,unused58,unused59,unused60,unused61,unused62,unused63,unused64,unused65,unused66,unused67,unused68,unused69,unused70,unused71,unused72,unused73,unused74,unused75,unused76,unused77,unused78,unused79,unused80,unused81,unused82,unused83,unused84,unused85,unused86,unused87,unused88,unused89,unused90,unused91,unused92,unused93,unused94,unused95,unused96,unused97,unused98,unused99,unused100,unused101,unused102,unused103,unused104,unused105,unused106,unused107,unused108,unused109,unused110,unused111,unused112,unused113,unused114,unused115,unused116,unused117,unused118,unused119,unused120,unused121,unused122,unused123,unused124,unused125,unused126,unused127,unused128,unused129,unused130,unused131,unused132,unused133,unused134,unused135,unused136,unused137,unused138,unused139,unused140,unused141,unused142,unused143,unused144,unused145,unused146,unused147,unused148,unused149,unused150,unused151,unused152,unused153,unused154,unused155,unused156,unused157,unused158,unused159,unused160,unused161,unused162,unused163,unused164,unused165,unused166,unused167,unused168,unused169,unused170,unused171,unused172,unused173,unused174,unused175,unused176,unused177,unused178,unused179,unused180,unused181,unused182,unused183,unused184,unused185,unused186,unused187,unused188,unused189,unused190,unused191,unused192,unused193,unused194,unused195,unused196,unused197,unused198,unused199,unused200,unused201,unused202,unused203,unused204,unused205,unused206,unused207,unused208,unused209,unused210,unused211,unused212,unused213,unused214,unused215,unused216,unused217,unused218,unused219,unused220,unused221,unused222,unused223,unused224,unused225,unused226,unused227,unused228,unused229,unused230,unused231,unused232,unused233,unused234,unused235,unused236,unused237,unused238,unused239,unused240,unused241,unused242,unused243,unused244,unused245,unused246,unused247,unused248,unused249,unused250,unused251,unused252,unused253,unused254,unused255
                         * @return Result
                         * @throws JSONException
                         */

                        public Result moveVolume(String volume) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("volume", volume);
                            return _client.create("/nodes/" + _node + "/lxc/" + _vmid + "/move_volume", parameters);
                        }

                    }

                    public class PVEPending {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _vmid;

                        protected PVEPending(PveClient client, Object node, Object vmid) {
                            _client = client;
                            _node = node;
                            _vmid = vmid;
                        }

                        /**
                         * Get container configuration, including pending
                         * changes.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result vmPending() throws JSONException {
                            return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "/pending", null);
                        }

                    }

                    /**
                     * Destroy the container (also delete all uses files).
                     *
                     * @param destroy_unreferenced_disks If set, destroy
                     * additionally all disks with the VMID from all enabled
                     * storages which are not referenced in the config.
                     * @param force Force destroy, even if running.
                     * @param purge Remove container from all related
                     * configurations. For example, backup jobs, replication
                     * jobs or HA. Related ACLs and Firewall entries will
                     * *always* be removed.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result destroyVm(Boolean destroy_unreferenced_disks, Boolean force, Boolean purge) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("destroy-unreferenced-disks", destroy_unreferenced_disks);
                        parameters.put("force", force);
                        parameters.put("purge", purge);
                        return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "", parameters);
                    }

                    /**
                     * Destroy the container (also delete all uses files).
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result destroyVm() throws JSONException {
                        return _client.delete("/nodes/" + _node + "/lxc/" + _vmid + "", null);
                    }

                    /**
                     * Directory index
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result vmdiridx() throws JSONException {
                        return _client.get("/nodes/" + _node + "/lxc/" + _vmid + "", null);
                    }

                }

                /**
                 * LXC container index (per node).
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result vmlist() throws JSONException {
                    return _client.get("/nodes/" + _node + "/lxc", null);
                }

                /**
                 * Create or restore a container.
                 *
                 * @param ostemplate The OS template or backup file.
                 * @param vmid The (unique) ID of the VM.
                 * @param arch OS architecture type. Enum:
                 * amd64,i386,arm64,armhf
                 * @param bwlimit Override I/O bandwidth limit (in KiB/s).
                 * @param cmode Console mode. By default, the console command
                 * tries to open a connection to one of the available tty
                 * devices. By setting cmode to 'console' it tries to attach to
                 * /dev/console instead. If you set cmode to 'shell', it simply
                 * invokes a shell inside the container (no login). Enum:
                 * shell,console,tty
                 * @param console Attach a console device (/dev/console) to the
                 * container.
                 * @param cores The number of cores assigned to the container. A
                 * container can use all available cores by default.
                 * @param cpulimit Limit of CPU usage. NOTE: If the computer has
                 * 2 CPUs, it has a total of '2' CPU time. Value '0' indicates
                 * no CPU limit.
                 * @param cpuunits CPU weight for a VM. Argument is used in the
                 * kernel fair scheduler. The larger the number is, the more CPU
                 * time this VM gets. Number is relative to the weights of all
                 * the other running VMs. NOTE: You can disable fair-scheduler
                 * configuration by setting this to 0.
                 * @param debug Try to be more verbose. For now this only
                 * enables debug log-level on start.
                 * @param description Description for the Container. Shown in
                 * the web-interface CT's summary. This is saved as comment
                 * inside the configuration file.
                 * @param features Allow containers access to advanced features.
                 * @param force Allow to overwrite existing container.
                 * @param hookscript Script that will be exectued during various
                 * steps in the containers lifetime.
                 * @param hostname Set a host name for the container.
                 * @param ignore_unpack_errors Ignore errors when extracting the
                 * template.
                 * @param lock_ Lock/unlock the VM. Enum:
                 * backup,create,destroyed,disk,fstrim,migrate,mounted,rollback,snapshot,snapshot-delete
                 * @param memory Amount of RAM for the VM in MB.
                 * @param mpN Use volume as container mount point. Use the
                 * special syntax STORAGE_ID:SIZE_IN_GiB to allocate a new
                 * volume.
                 * @param nameserver Sets DNS server IP address for a container.
                 * Create will automatically use the setting from the host if
                 * you neither set searchdomain nor nameserver.
                 * @param netN Specifies network interfaces for the container.
                 * @param onboot Specifies whether a VM will be started during
                 * system bootup.
                 * @param ostype OS type. This is used to setup configuration
                 * inside the container, and corresponds to lxc setup scripts in
                 * /usr/share/lxc/config/&amp;lt;ostype&amp;gt;.common.conf.
                 * Value 'unmanaged' can be used to skip and OS specific setup.
                 * Enum:
                 * debian,devuan,ubuntu,centos,fedora,opensuse,archlinux,alpine,gentoo,unmanaged
                 * @param password Sets root password inside container.
                 * @param pool Add the VM to the specified pool.
                 * @param protection Sets the protection flag of the container.
                 * This will prevent the CT or CT's disk remove/update
                 * operation.
                 * @param restore Mark this as restore task.
                 * @param rootfs Use volume as container root.
                 * @param searchdomain Sets DNS search domains for a container.
                 * Create will automatically use the setting from the host if
                 * you neither set searchdomain nor nameserver.
                 * @param ssh_public_keys Setup public SSH keys (one key per
                 * line, OpenSSH format).
                 * @param start Start the CT after its creation finished
                 * successfully.
                 * @param startup Startup and shutdown behavior. Order is a
                 * non-negative number defining the general startup order.
                 * Shutdown in done with reverse ordering. Additionally you can
                 * set the 'up' or 'down' delay in seconds, which specifies a
                 * delay to wait before the next VM is started or stopped.
                 * @param storage Default Storage.
                 * @param swap Amount of SWAP for the VM in MB.
                 * @param tags Tags of the Container. This is only meta
                 * information.
                 * @param template Enable/disable Template.
                 * @param timezone Time zone to use in the container. If option
                 * isn't set, then nothing will be done. Can be set to 'host' to
                 * match the host time zone, or an arbitrary time zone option
                 * from /usr/share/zoneinfo/zone.tab
                 * @param tty Specify the number of tty available to the
                 * container
                 * @param unique Assign a unique random ethernet address.
                 * @param unprivileged Makes the container run as unprivileged
                 * user. (Should not be modified manually.)
                 * @param unusedN Reference to unused volumes. This is used
                 * internally, and should not be modified manually.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createVm(String ostemplate, int vmid, String arch, Float bwlimit, String cmode, Boolean console, Integer cores, Float cpulimit, Integer cpuunits, Boolean debug, String description, String features, Boolean force, String hookscript, String hostname, Boolean ignore_unpack_errors, String lock_, Integer memory, Map<Integer, String> mpN, String nameserver, Map<Integer, String> netN, Boolean onboot, String ostype, String password, String pool, Boolean protection, Boolean restore, String rootfs, String searchdomain, String ssh_public_keys, Boolean start, String startup, String storage, Integer swap, String tags, Boolean template, String timezone, Integer tty, Boolean unique, Boolean unprivileged, Map<Integer, String> unusedN) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ostemplate", ostemplate);
                    parameters.put("vmid", vmid);
                    parameters.put("arch", arch);
                    parameters.put("bwlimit", bwlimit);
                    parameters.put("cmode", cmode);
                    parameters.put("console", console);
                    parameters.put("cores", cores);
                    parameters.put("cpulimit", cpulimit);
                    parameters.put("cpuunits", cpuunits);
                    parameters.put("debug", debug);
                    parameters.put("description", description);
                    parameters.put("features", features);
                    parameters.put("force", force);
                    parameters.put("hookscript", hookscript);
                    parameters.put("hostname", hostname);
                    parameters.put("ignore-unpack-errors", ignore_unpack_errors);
                    parameters.put("lock", lock_);
                    parameters.put("memory", memory);
                    parameters.put("nameserver", nameserver);
                    parameters.put("onboot", onboot);
                    parameters.put("ostype", ostype);
                    parameters.put("password", password);
                    parameters.put("pool", pool);
                    parameters.put("protection", protection);
                    parameters.put("restore", restore);
                    parameters.put("rootfs", rootfs);
                    parameters.put("searchdomain", searchdomain);
                    parameters.put("ssh-public-keys", ssh_public_keys);
                    parameters.put("start", start);
                    parameters.put("startup", startup);
                    parameters.put("storage", storage);
                    parameters.put("swap", swap);
                    parameters.put("tags", tags);
                    parameters.put("template", template);
                    parameters.put("timezone", timezone);
                    parameters.put("tty", tty);
                    parameters.put("unique", unique);
                    parameters.put("unprivileged", unprivileged);
                    addIndexedParameter(parameters, "mp", mpN);
                    addIndexedParameter(parameters, "net", netN);
                    addIndexedParameter(parameters, "unused", unusedN);
                    return _client.create("/nodes/" + _node + "/lxc", parameters);
                }

                /**
                 * Create or restore a container.
                 *
                 * @param ostemplate The OS template or backup file.
                 * @param vmid The (unique) ID of the VM.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createVm(String ostemplate, int vmid) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ostemplate", ostemplate);
                    parameters.put("vmid", vmid);
                    return _client.create("/nodes/" + _node + "/lxc", parameters);
                }

            }

            public class PVECeph {

                private final PveClient _client;
                private final Object _node;

                protected PVECeph(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEOsd _osd;

                public PVEOsd getOsd() {
                    return _osd == null ? (_osd = new PVEOsd(_client, _node)) : _osd;
                }
                private PVEMds _mds;

                public PVEMds getMds() {
                    return _mds == null ? (_mds = new PVEMds(_client, _node)) : _mds;
                }
                private PVEMgr _mgr;

                public PVEMgr getMgr() {
                    return _mgr == null ? (_mgr = new PVEMgr(_client, _node)) : _mgr;
                }
                private PVEMon _mon;

                public PVEMon getMon() {
                    return _mon == null ? (_mon = new PVEMon(_client, _node)) : _mon;
                }
                private PVEFs _fs;

                public PVEFs getFs() {
                    return _fs == null ? (_fs = new PVEFs(_client, _node)) : _fs;
                }
                private PVEPools _pools;

                public PVEPools getPools() {
                    return _pools == null ? (_pools = new PVEPools(_client, _node)) : _pools;
                }
                private PVEConfig _config;

                public PVEConfig getConfig() {
                    return _config == null ? (_config = new PVEConfig(_client, _node)) : _config;
                }
                private PVEConfigdb _configdb;

                public PVEConfigdb getConfigdb() {
                    return _configdb == null ? (_configdb = new PVEConfigdb(_client, _node)) : _configdb;
                }
                private PVEInit _init;

                public PVEInit getInit() {
                    return _init == null ? (_init = new PVEInit(_client, _node)) : _init;
                }
                private PVEStop _stop;

                public PVEStop getStop() {
                    return _stop == null ? (_stop = new PVEStop(_client, _node)) : _stop;
                }
                private PVEStart _start;

                public PVEStart getStart() {
                    return _start == null ? (_start = new PVEStart(_client, _node)) : _start;
                }
                private PVERestart _restart;

                public PVERestart getRestart() {
                    return _restart == null ? (_restart = new PVERestart(_client, _node)) : _restart;
                }
                private PVEStatus _status;

                public PVEStatus getStatus() {
                    return _status == null ? (_status = new PVEStatus(_client, _node)) : _status;
                }
                private PVECrush _crush;

                public PVECrush getCrush() {
                    return _crush == null ? (_crush = new PVECrush(_client, _node)) : _crush;
                }
                private PVELog _log;

                public PVELog getLog() {
                    return _log == null ? (_log = new PVELog(_client, _node)) : _log;
                }
                private PVERules _rules;

                public PVERules getRules() {
                    return _rules == null ? (_rules = new PVERules(_client, _node)) : _rules;
                }

                public class PVEOsd {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEOsd(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVEOsdidItem get(Object osdid) {
                        return new PVEOsdidItem(_client, _node, osdid);
                    }

                    public class PVEOsdidItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _osdid;

                        protected PVEOsdidItem(PveClient client, Object node, Object osdid) {
                            _client = client;
                            _node = node;
                            _osdid = osdid;
                        }

                        private PVEIn _in;

                        public PVEIn getIn() {
                            return _in == null ? (_in = new PVEIn(_client, _node, _osdid)) : _in;
                        }
                        private PVEOut _out;

                        public PVEOut getOut() {
                            return _out == null ? (_out = new PVEOut(_client, _node, _osdid)) : _out;
                        }
                        private PVEScrub _scrub;

                        public PVEScrub getScrub() {
                            return _scrub == null ? (_scrub = new PVEScrub(_client, _node, _osdid)) : _scrub;
                        }

                        public class PVEIn {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _osdid;

                            protected PVEIn(PveClient client, Object node, Object osdid) {
                                _client = client;
                                _node = node;
                                _osdid = osdid;
                            }

                            /**
                             * ceph osd in
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result in() throws JSONException {
                                return _client.create("/nodes/" + _node + "/ceph/osd/" + _osdid + "/in", null);
                            }

                        }

                        public class PVEOut {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _osdid;

                            protected PVEOut(PveClient client, Object node, Object osdid) {
                                _client = client;
                                _node = node;
                                _osdid = osdid;
                            }

                            /**
                             * ceph osd out
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result out() throws JSONException {
                                return _client.create("/nodes/" + _node + "/ceph/osd/" + _osdid + "/out", null);
                            }

                        }

                        public class PVEScrub {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _osdid;

                            protected PVEScrub(PveClient client, Object node, Object osdid) {
                                _client = client;
                                _node = node;
                                _osdid = osdid;
                            }

                            /**
                             * Instruct the OSD to scrub.
                             *
                             * @param deep If set, instructs a deep scrub
                             * instead of a normal one.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result scrub(Boolean deep) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("deep", deep);
                                return _client.create("/nodes/" + _node + "/ceph/osd/" + _osdid + "/scrub", parameters);
                            }

                            /**
                             * Instruct the OSD to scrub.
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result scrub() throws JSONException {
                                return _client.create("/nodes/" + _node + "/ceph/osd/" + _osdid + "/scrub", null);
                            }

                        }

                        /**
                         * Destroy OSD
                         *
                         * @param cleanup If set, we remove partition table
                         * entries.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result destroyosd(Boolean cleanup) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("cleanup", cleanup);
                            return _client.delete("/nodes/" + _node + "/ceph/osd/" + _osdid + "", parameters);
                        }

                        /**
                         * Destroy OSD
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result destroyosd() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/ceph/osd/" + _osdid + "", null);
                        }

                    }

                    /**
                     * Get Ceph osd list/tree.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/osd", null);
                    }

                    /**
                     * Create OSD
                     *
                     * @param dev Block device name.
                     * @param crush_device_class Set the device class of the OSD
                     * in crush.
                     * @param db_dev Block device name for block.db.
                     * @param db_dev_size Size in GiB for block.db.
                     * @param encrypted Enables encryption of the OSD.
                     * @param wal_dev Block device name for block.wal.
                     * @param wal_dev_size Size in GiB for block.wal.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createosd(String dev, String crush_device_class, String db_dev, Float db_dev_size, Boolean encrypted, String wal_dev, Float wal_dev_size) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("dev", dev);
                        parameters.put("crush-device-class", crush_device_class);
                        parameters.put("db_dev", db_dev);
                        parameters.put("db_dev_size", db_dev_size);
                        parameters.put("encrypted", encrypted);
                        parameters.put("wal_dev", wal_dev);
                        parameters.put("wal_dev_size", wal_dev_size);
                        return _client.create("/nodes/" + _node + "/ceph/osd", parameters);
                    }

                    /**
                     * Create OSD
                     *
                     * @param dev Block device name.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createosd(String dev) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("dev", dev);
                        return _client.create("/nodes/" + _node + "/ceph/osd", parameters);
                    }

                }

                public class PVEMds {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEMds(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Destroy Ceph Metadata Server
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result destroymds() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/ceph/mds/" + _name + "", null);
                        }

                        /**
                         * Create Ceph Metadata Server (MDS)
                         *
                         * @param hotstandby Determines whether a ceph-mds
                         * daemon should poll and replay the log of an active
                         * MDS. Faster switch on MDS failure, but needs more
                         * idle resources.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result createmds(Boolean hotstandby) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("hotstandby", hotstandby);
                            return _client.create("/nodes/" + _node + "/ceph/mds/" + _name + "", parameters);
                        }

                        /**
                         * Create Ceph Metadata Server (MDS)
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result createmds() throws JSONException {
                            return _client.create("/nodes/" + _node + "/ceph/mds/" + _name + "", null);
                        }

                    }

                    /**
                     * MDS directory index.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/mds", null);
                    }

                }

                public class PVEMgr {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEMgr(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVEIdItem get(Object id) {
                        return new PVEIdItem(_client, _node, id);
                    }

                    public class PVEIdItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _id;

                        protected PVEIdItem(PveClient client, Object node, Object id) {
                            _client = client;
                            _node = node;
                            _id = id;
                        }

                        /**
                         * Destroy Ceph Manager.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result destroymgr() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/ceph/mgr/" + _id + "", null);
                        }

                        /**
                         * Create Ceph Manager
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result createmgr() throws JSONException {
                            return _client.create("/nodes/" + _node + "/ceph/mgr/" + _id + "", null);
                        }

                    }

                    /**
                     * MGR directory index.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/mgr", null);
                    }

                }

                public class PVEMon {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEMon(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVEMonidItem get(Object monid) {
                        return new PVEMonidItem(_client, _node, monid);
                    }

                    public class PVEMonidItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _monid;

                        protected PVEMonidItem(PveClient client, Object node, Object monid) {
                            _client = client;
                            _node = node;
                            _monid = monid;
                        }

                        /**
                         * Destroy Ceph Monitor and Manager.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result destroymon() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/ceph/mon/" + _monid + "", null);
                        }

                        /**
                         * Create Ceph Monitor and Manager
                         *
                         * @param mon_address Overwrites autodetected monitor IP
                         * address(es). Must be in the public network(s) of
                         * Ceph.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result createmon(String mon_address) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("mon-address", mon_address);
                            return _client.create("/nodes/" + _node + "/ceph/mon/" + _monid + "", parameters);
                        }

                        /**
                         * Create Ceph Monitor and Manager
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result createmon() throws JSONException {
                            return _client.create("/nodes/" + _node + "/ceph/mon/" + _monid + "", null);
                        }

                    }

                    /**
                     * Get Ceph monitor list.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result listmon() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/mon", null);
                    }

                }

                public class PVEFs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEFs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Create a Ceph filesystem
                         *
                         * @param add_storage Configure the created CephFS as
                         * storage for this cluster.
                         * @param pg_num Number of placement groups for the
                         * backing data pool. The metadata pool will use a
                         * quarter of this.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result createfs(Boolean add_storage, Integer pg_num) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("add-storage", add_storage);
                            parameters.put("pg_num", pg_num);
                            return _client.create("/nodes/" + _node + "/ceph/fs/" + _name + "", parameters);
                        }

                        /**
                         * Create a Ceph filesystem
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result createfs() throws JSONException {
                            return _client.create("/nodes/" + _node + "/ceph/fs/" + _name + "", null);
                        }

                    }

                    /**
                     * Directory index.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/fs", null);
                    }

                }

                public class PVEPools {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEPools(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Destroy pool
                         *
                         * @param force If true, destroys pool even if in use
                         * @param remove_storages Remove all pveceph-managed
                         * storages configured for this pool
                         * @return Result
                         * @throws JSONException
                         */
                        public Result destroypool(Boolean force, Boolean remove_storages) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("force", force);
                            parameters.put("remove_storages", remove_storages);
                            return _client.delete("/nodes/" + _node + "/ceph/pools/" + _name + "", parameters);
                        }

                        /**
                         * Destroy pool
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result destroypool() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/ceph/pools/" + _name + "", null);
                        }

                        /**
                         * List pool settings.
                         *
                         * @param verbose If enabled, will display additional
                         * data(eg. statistics).
                         * @return Result
                         * @throws JSONException
                         */

                        public Result getpool(Boolean verbose) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("verbose", verbose);
                            return _client.get("/nodes/" + _node + "/ceph/pools/" + _name + "", parameters);
                        }

                        /**
                         * List pool settings.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result getpool() throws JSONException {
                            return _client.get("/nodes/" + _node + "/ceph/pools/" + _name + "", null);
                        }

                        /**
                         * Change POOL settings
                         *
                         * @param application The application of the pool. Enum:
                         * rbd,cephfs,rgw
                         * @param crush_rule The rule to use for mapping object
                         * placement in the cluster.
                         * @param min_size Minimum number of replicas per object
                         * @param pg_autoscale_mode The automatic PG scaling
                         * mode of the pool. Enum: on,off,warn
                         * @param pg_num Number of placement groups.
                         * @param pg_num_min Minimal number of placement groups.
                         * @param size Number of replicas per object
                         * @param target_size The estimated target size of the
                         * pool for the PG autoscaler.
                         * @param target_size_ratio The estimated target ratio
                         * of the pool for the PG autoscaler.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result setpool(String application, String crush_rule, Integer min_size, String pg_autoscale_mode, Integer pg_num, Integer pg_num_min, Integer size, String target_size, Float target_size_ratio) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("application", application);
                            parameters.put("crush_rule", crush_rule);
                            parameters.put("min_size", min_size);
                            parameters.put("pg_autoscale_mode", pg_autoscale_mode);
                            parameters.put("pg_num", pg_num);
                            parameters.put("pg_num_min", pg_num_min);
                            parameters.put("size", size);
                            parameters.put("target_size", target_size);
                            parameters.put("target_size_ratio", target_size_ratio);
                            return _client.set("/nodes/" + _node + "/ceph/pools/" + _name + "", parameters);
                        }

                        /**
                         * Change POOL settings
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result setpool() throws JSONException {
                            return _client.set("/nodes/" + _node + "/ceph/pools/" + _name + "", null);
                        }

                    }

                    /**
                     * List all pools.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result lspools() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/pools", null);
                    }

                    /**
                     * Create POOL
                     *
                     * @param name The name of the pool. It must be unique.
                     * @param add_storages Configure VM and CT storage using the
                     * new pool.
                     * @param application The application of the pool. Enum:
                     * rbd,cephfs,rgw
                     * @param crush_rule The rule to use for mapping object
                     * placement in the cluster.
                     * @param min_size Minimum number of replicas per object
                     * @param pg_autoscale_mode The automatic PG scaling mode of
                     * the pool. Enum: on,off,warn
                     * @param pg_num Number of placement groups.
                     * @param pg_num_min Minimal number of placement groups.
                     * @param size Number of replicas per object
                     * @param target_size The estimated target size of the pool
                     * for the PG autoscaler.
                     * @param target_size_ratio The estimated target ratio of
                     * the pool for the PG autoscaler.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createpool(String name, Boolean add_storages, String application, String crush_rule, Integer min_size, String pg_autoscale_mode, Integer pg_num, Integer pg_num_min, Integer size, String target_size, Float target_size_ratio) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("name", name);
                        parameters.put("add_storages", add_storages);
                        parameters.put("application", application);
                        parameters.put("crush_rule", crush_rule);
                        parameters.put("min_size", min_size);
                        parameters.put("pg_autoscale_mode", pg_autoscale_mode);
                        parameters.put("pg_num", pg_num);
                        parameters.put("pg_num_min", pg_num_min);
                        parameters.put("size", size);
                        parameters.put("target_size", target_size);
                        parameters.put("target_size_ratio", target_size_ratio);
                        return _client.create("/nodes/" + _node + "/ceph/pools", parameters);
                    }

                    /**
                     * Create POOL
                     *
                     * @param name The name of the pool. It must be unique.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createpool(String name) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("name", name);
                        return _client.create("/nodes/" + _node + "/ceph/pools", parameters);
                    }

                }

                public class PVEConfig {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEConfig(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get Ceph configuration.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result config() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/config", null);
                    }

                }

                public class PVEConfigdb {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEConfigdb(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get Ceph configuration database.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result configdb() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/configdb", null);
                    }

                }

                public class PVEInit {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEInit(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Create initial ceph default configuration and setup
                     * symlinks.
                     *
                     * @param cluster_network Declare a separate cluster
                     * network, OSDs will routeheartbeat, object replication and
                     * recovery traffic over it
                     * @param disable_cephx Disable cephx authentication.
                     * WARNING: cephx is a security feature protecting against
                     * man-in-the-middle attacks. Only consider disabling cephx
                     * if your network is private!
                     * @param min_size Minimum number of available replicas per
                     * object to allow I/O
                     * @param network Use specific network for all ceph related
                     * traffic
                     * @param pg_bits Placement group bits, used to specify the
                     * default number of placement groups. NOTE: 'osd pool
                     * default pg num' does not work for default pools.
                     * @param size Targeted number of replicas per object
                     * @return Result
                     * @throws JSONException
                     */
                    public Result init(String cluster_network, Boolean disable_cephx, Integer min_size, String network, Integer pg_bits, Integer size) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("cluster-network", cluster_network);
                        parameters.put("disable_cephx", disable_cephx);
                        parameters.put("min_size", min_size);
                        parameters.put("network", network);
                        parameters.put("pg_bits", pg_bits);
                        parameters.put("size", size);
                        return _client.create("/nodes/" + _node + "/ceph/init", parameters);
                    }

                    /**
                     * Create initial ceph default configuration and setup
                     * symlinks.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result init() throws JSONException {
                        return _client.create("/nodes/" + _node + "/ceph/init", null);
                    }

                }

                public class PVEStop {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEStop(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Stop ceph services.
                     *
                     * @param service Ceph service name.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result stop(String service) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("service", service);
                        return _client.create("/nodes/" + _node + "/ceph/stop", parameters);
                    }

                    /**
                     * Stop ceph services.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result stop() throws JSONException {
                        return _client.create("/nodes/" + _node + "/ceph/stop", null);
                    }

                }

                public class PVEStart {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEStart(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Start ceph services.
                     *
                     * @param service Ceph service name.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result start(String service) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("service", service);
                        return _client.create("/nodes/" + _node + "/ceph/start", parameters);
                    }

                    /**
                     * Start ceph services.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result start() throws JSONException {
                        return _client.create("/nodes/" + _node + "/ceph/start", null);
                    }

                }

                public class PVERestart {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVERestart(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Restart ceph services.
                     *
                     * @param service Ceph service name.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result restart(String service) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("service", service);
                        return _client.create("/nodes/" + _node + "/ceph/restart", parameters);
                    }

                    /**
                     * Restart ceph services.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result restart() throws JSONException {
                        return _client.create("/nodes/" + _node + "/ceph/restart", null);
                    }

                }

                public class PVEStatus {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEStatus(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get ceph status.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result status() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/status", null);
                    }

                }

                public class PVECrush {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVECrush(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get OSD crush map
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result crush() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/crush", null);
                    }

                }

                public class PVELog {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVELog(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Read ceph log
                     *
                     * @param limit
                     * @param start
                     * @return Result
                     * @throws JSONException
                     */
                    public Result log(Integer limit, Integer start) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("limit", limit);
                        parameters.put("start", start);
                        return _client.get("/nodes/" + _node + "/ceph/log", parameters);
                    }

                    /**
                     * Read ceph log
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result log() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/log", null);
                    }

                }

                public class PVERules {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVERules(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * List ceph rules.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result rules() throws JSONException {
                        return _client.get("/nodes/" + _node + "/ceph/rules", null);
                    }

                }

                /**
                 * Directory index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/ceph", null);
                }

            }

            public class PVEVzdump {

                private final PveClient _client;
                private final Object _node;

                protected PVEVzdump(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEDefaults _defaults;

                public PVEDefaults getDefaults() {
                    return _defaults == null ? (_defaults = new PVEDefaults(_client, _node)) : _defaults;
                }
                private PVEExtractconfig _extractconfig;

                public PVEExtractconfig getExtractconfig() {
                    return _extractconfig == null ? (_extractconfig = new PVEExtractconfig(_client, _node)) : _extractconfig;
                }

                public class PVEDefaults {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEDefaults(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get the currently configured vzdump defaults.
                     *
                     * @param storage The storage identifier.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result defaults(String storage) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("storage", storage);
                        return _client.get("/nodes/" + _node + "/vzdump/defaults", parameters);
                    }

                    /**
                     * Get the currently configured vzdump defaults.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result defaults() throws JSONException {
                        return _client.get("/nodes/" + _node + "/vzdump/defaults", null);
                    }

                }

                public class PVEExtractconfig {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEExtractconfig(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Extract configuration from vzdump backup archive.
                     *
                     * @param volume Volume identifier
                     * @return Result
                     * @throws JSONException
                     */
                    public Result extractconfig(String volume) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("volume", volume);
                        return _client.get("/nodes/" + _node + "/vzdump/extractconfig", parameters);
                    }

                }

                /**
                 * Create backup.
                 *
                 * @param all Backup all known guest systems on this host.
                 * @param bwlimit Limit I/O bandwidth (KBytes per second).
                 * @param compress Compress dump file. Enum: 0,1,gzip,lzo,zstd
                 * @param dumpdir Store resulting files to specified directory.
                 * @param exclude Exclude specified guest systems (assumes
                 * --all)
                 * @param exclude_path Exclude certain files/directories (shell
                 * globs). Paths starting with '/' are anchored to the
                 * container's root, other paths match relative to each
                 * subdirectory.
                 * @param ionice Set CFQ ionice priority.
                 * @param lockwait Maximal time to wait for the global lock
                 * (minutes).
                 * @param mailnotification Specify when to send an email Enum:
                 * always,failure
                 * @param mailto Comma-separated list of email addresses or
                 * users that should receive email notifications.
                 * @param maxfiles Deprecated: use 'prune-backups' instead.
                 * Maximal number of backup files per guest system.
                 * @param mode Backup mode. Enum: snapshot,suspend,stop
                 * @param pigz Use pigz instead of gzip when N&amp;gt;0. N=1
                 * uses half of cores, N&amp;gt;1 uses N as thread count.
                 * @param pool Backup all known guest systems included in the
                 * specified pool.
                 * @param prune_backups Use these retention options instead of
                 * those from the storage configuration.
                 * @param quiet Be quiet.
                 * @param remove Prune older backups according to
                 * 'prune-backups'.
                 * @param script Use specified hook script.
                 * @param stdexcludes Exclude temporary files and logs.
                 * @param stdout Write tar to stdout, not to a file.
                 * @param stop Stop running backup jobs on this host.
                 * @param stopwait Maximal time to wait until a guest system is
                 * stopped (minutes).
                 * @param storage Store resulting file to this storage.
                 * @param tmpdir Store temporary files to specified directory.
                 * @param vmid The ID of the guest system you want to backup.
                 * @param zstd Zstd threads. N=0 uses half of the available
                 * cores, N&amp;gt;0 uses N as thread count.
                 * @return Result
                 * @throws JSONException
                 */
                public Result vzdump(Boolean all, Integer bwlimit, String compress, String dumpdir, String exclude, String exclude_path, Integer ionice, Integer lockwait, String mailnotification, String mailto, Integer maxfiles, String mode, Integer pigz, String pool, String prune_backups, Boolean quiet, Boolean remove, String script, Boolean stdexcludes, Boolean stdout, Boolean stop, Integer stopwait, String storage, String tmpdir, String vmid, Integer zstd) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("all", all);
                    parameters.put("bwlimit", bwlimit);
                    parameters.put("compress", compress);
                    parameters.put("dumpdir", dumpdir);
                    parameters.put("exclude", exclude);
                    parameters.put("exclude-path", exclude_path);
                    parameters.put("ionice", ionice);
                    parameters.put("lockwait", lockwait);
                    parameters.put("mailnotification", mailnotification);
                    parameters.put("mailto", mailto);
                    parameters.put("maxfiles", maxfiles);
                    parameters.put("mode", mode);
                    parameters.put("pigz", pigz);
                    parameters.put("pool", pool);
                    parameters.put("prune-backups", prune_backups);
                    parameters.put("quiet", quiet);
                    parameters.put("remove", remove);
                    parameters.put("script", script);
                    parameters.put("stdexcludes", stdexcludes);
                    parameters.put("stdout", stdout);
                    parameters.put("stop", stop);
                    parameters.put("stopwait", stopwait);
                    parameters.put("storage", storage);
                    parameters.put("tmpdir", tmpdir);
                    parameters.put("vmid", vmid);
                    parameters.put("zstd", zstd);
                    return _client.create("/nodes/" + _node + "/vzdump", parameters);
                }

                /**
                 * Create backup.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result vzdump() throws JSONException {
                    return _client.create("/nodes/" + _node + "/vzdump", null);
                }

            }

            public class PVEServices {

                private final PveClient _client;
                private final Object _node;

                protected PVEServices(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEServiceItem get(Object service) {
                    return new PVEServiceItem(_client, _node, service);
                }

                public class PVEServiceItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _service;

                    protected PVEServiceItem(PveClient client, Object node, Object service) {
                        _client = client;
                        _node = node;
                        _service = service;
                    }

                    private PVEState _state;

                    public PVEState getState() {
                        return _state == null ? (_state = new PVEState(_client, _node, _service)) : _state;
                    }
                    private PVEStart _start;

                    public PVEStart getStart() {
                        return _start == null ? (_start = new PVEStart(_client, _node, _service)) : _start;
                    }
                    private PVEStop _stop;

                    public PVEStop getStop() {
                        return _stop == null ? (_stop = new PVEStop(_client, _node, _service)) : _stop;
                    }
                    private PVERestart _restart;

                    public PVERestart getRestart() {
                        return _restart == null ? (_restart = new PVERestart(_client, _node, _service)) : _restart;
                    }
                    private PVEReload _reload;

                    public PVEReload getReload() {
                        return _reload == null ? (_reload = new PVEReload(_client, _node, _service)) : _reload;
                    }

                    public class PVEState {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _service;

                        protected PVEState(PveClient client, Object node, Object service) {
                            _client = client;
                            _node = node;
                            _service = service;
                        }

                        /**
                         * Read service properties
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result serviceState() throws JSONException {
                            return _client.get("/nodes/" + _node + "/services/" + _service + "/state", null);
                        }

                    }

                    public class PVEStart {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _service;

                        protected PVEStart(PveClient client, Object node, Object service) {
                            _client = client;
                            _node = node;
                            _service = service;
                        }

                        /**
                         * Start service.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result serviceStart() throws JSONException {
                            return _client.create("/nodes/" + _node + "/services/" + _service + "/start", null);
                        }

                    }

                    public class PVEStop {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _service;

                        protected PVEStop(PveClient client, Object node, Object service) {
                            _client = client;
                            _node = node;
                            _service = service;
                        }

                        /**
                         * Stop service.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result serviceStop() throws JSONException {
                            return _client.create("/nodes/" + _node + "/services/" + _service + "/stop", null);
                        }

                    }

                    public class PVERestart {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _service;

                        protected PVERestart(PveClient client, Object node, Object service) {
                            _client = client;
                            _node = node;
                            _service = service;
                        }

                        /**
                         * Hard restart service. Use reload if you want to
                         * reduce interruptions.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result serviceRestart() throws JSONException {
                            return _client.create("/nodes/" + _node + "/services/" + _service + "/restart", null);
                        }

                    }

                    public class PVEReload {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _service;

                        protected PVEReload(PveClient client, Object node, Object service) {
                            _client = client;
                            _node = node;
                            _service = service;
                        }

                        /**
                         * Reload service. Falls back to restart if service
                         * cannot be reloaded.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result serviceReload() throws JSONException {
                            return _client.create("/nodes/" + _node + "/services/" + _service + "/reload", null);
                        }

                    }

                    /**
                     * Directory index
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result srvcmdidx() throws JSONException {
                        return _client.get("/nodes/" + _node + "/services/" + _service + "", null);
                    }

                }

                /**
                 * Service list.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/services", null);
                }

            }

            public class PVESubscription {

                private final PveClient _client;
                private final Object _node;

                protected PVESubscription(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Delete subscription key of this node.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result delete() throws JSONException {
                    return _client.delete("/nodes/" + _node + "/subscription", null);
                }

                /**
                 * Read subscription info.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result get() throws JSONException {
                    return _client.get("/nodes/" + _node + "/subscription", null);
                }

                /**
                 * Update subscription info.
                 *
                 * @param force Always connect to server, even if we have up to
                 * date info inside local cache.
                 * @return Result
                 * @throws JSONException
                 */

                public Result update(Boolean force) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("force", force);
                    return _client.create("/nodes/" + _node + "/subscription", parameters);
                }

                /**
                 * Update subscription info.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result update() throws JSONException {
                    return _client.create("/nodes/" + _node + "/subscription", null);
                }

                /**
                 * Set subscription key.
                 *
                 * @param key Proxmox VE subscription key
                 * @return Result
                 * @throws JSONException
                 */

                public Result set(String key) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("key", key);
                    return _client.set("/nodes/" + _node + "/subscription", parameters);
                }

            }

            public class PVENetwork {

                private final PveClient _client;
                private final Object _node;

                protected PVENetwork(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEIfaceItem get(Object iface) {
                    return new PVEIfaceItem(_client, _node, iface);
                }

                public class PVEIfaceItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _iface;

                    protected PVEIfaceItem(PveClient client, Object node, Object iface) {
                        _client = client;
                        _node = node;
                        _iface = iface;
                    }

                    /**
                     * Delete network device configuration
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deleteNetwork() throws JSONException {
                        return _client.delete("/nodes/" + _node + "/network/" + _iface + "", null);
                    }

                    /**
                     * Read network device configuration
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result networkConfig() throws JSONException {
                        return _client.get("/nodes/" + _node + "/network/" + _iface + "", null);
                    }

                    /**
                     * Update network device configuration
                     *
                     * @param type Network interface type Enum:
                     * bridge,bond,eth,alias,vlan,OVSBridge,OVSBond,OVSPort,OVSIntPort,unknown
                     * @param address IP address.
                     * @param address6 IP address.
                     * @param autostart Automatically start interface on boot.
                     * @param bond_primary Specify the primary interface for
                     * active-backup bond.
                     * @param bond_mode Bonding mode. Enum:
                     * balance-rr,active-backup,balance-xor,broadcast,802.3ad,balance-tlb,balance-alb,balance-slb,lacp-balance-slb,lacp-balance-tcp
                     * @param bond_xmit_hash_policy Selects the transmit hash
                     * policy to use for slave selection in balance-xor and
                     * 802.3ad modes. Enum: layer2,layer2+3,layer3+4
                     * @param bridge_ports Specify the interfaces you want to
                     * add to your bridge.
                     * @param bridge_vlan_aware Enable bridge vlan support.
                     * @param cidr IPv4 CIDR.
                     * @param cidr6 IPv6 CIDR.
                     * @param comments Comments
                     * @param comments6 Comments
                     * @param delete A list of settings you want to delete.
                     * @param gateway Default gateway address.
                     * @param gateway6 Default ipv6 gateway address.
                     * @param mtu MTU.
                     * @param netmask Network mask.
                     * @param netmask6 Network mask.
                     * @param ovs_bonds Specify the interfaces used by the
                     * bonding device.
                     * @param ovs_bridge The OVS bridge associated with a OVS
                     * port. This is required when you create an OVS port.
                     * @param ovs_options OVS interface options.
                     * @param ovs_ports Specify the interfaces you want to add
                     * to your bridge.
                     * @param ovs_tag Specify a VLan tag (used by OVSPort,
                     * OVSIntPort, OVSBond)
                     * @param slaves Specify the interfaces used by the bonding
                     * device.
                     * @param vlan_id vlan-id for a custom named vlan interface
                     * (ifupdown2 only).
                     * @param vlan_raw_device Specify the raw interface for the
                     * vlan interface.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateNetwork(String type, String address, String address6, Boolean autostart, String bond_primary, String bond_mode, String bond_xmit_hash_policy, String bridge_ports, Boolean bridge_vlan_aware, String cidr, String cidr6, String comments, String comments6, String delete, String gateway, String gateway6, Integer mtu, String netmask, Integer netmask6, String ovs_bonds, String ovs_bridge, String ovs_options, String ovs_ports, Integer ovs_tag, String slaves, Integer vlan_id, String vlan_raw_device) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("type", type);
                        parameters.put("address", address);
                        parameters.put("address6", address6);
                        parameters.put("autostart", autostart);
                        parameters.put("bond-primary", bond_primary);
                        parameters.put("bond_mode", bond_mode);
                        parameters.put("bond_xmit_hash_policy", bond_xmit_hash_policy);
                        parameters.put("bridge_ports", bridge_ports);
                        parameters.put("bridge_vlan_aware", bridge_vlan_aware);
                        parameters.put("cidr", cidr);
                        parameters.put("cidr6", cidr6);
                        parameters.put("comments", comments);
                        parameters.put("comments6", comments6);
                        parameters.put("delete", delete);
                        parameters.put("gateway", gateway);
                        parameters.put("gateway6", gateway6);
                        parameters.put("mtu", mtu);
                        parameters.put("netmask", netmask);
                        parameters.put("netmask6", netmask6);
                        parameters.put("ovs_bonds", ovs_bonds);
                        parameters.put("ovs_bridge", ovs_bridge);
                        parameters.put("ovs_options", ovs_options);
                        parameters.put("ovs_ports", ovs_ports);
                        parameters.put("ovs_tag", ovs_tag);
                        parameters.put("slaves", slaves);
                        parameters.put("vlan-id", vlan_id);
                        parameters.put("vlan-raw-device", vlan_raw_device);
                        return _client.set("/nodes/" + _node + "/network/" + _iface + "", parameters);
                    }

                    /**
                     * Update network device configuration
                     *
                     * @param type Network interface type Enum:
                     * bridge,bond,eth,alias,vlan,OVSBridge,OVSBond,OVSPort,OVSIntPort,unknown
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateNetwork(String type) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("type", type);
                        return _client.set("/nodes/" + _node + "/network/" + _iface + "", parameters);
                    }

                }

                /**
                 * Revert network configuration changes.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result revertNetworkChanges() throws JSONException {
                    return _client.delete("/nodes/" + _node + "/network", null);
                }

                /**
                 * List available networks
                 *
                 * @param type Only list specific interface types. Enum:
                 * bridge,bond,eth,alias,vlan,OVSBridge,OVSBond,OVSPort,OVSIntPort,any_bridge
                 * @return Result
                 * @throws JSONException
                 */

                public Result index(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.get("/nodes/" + _node + "/network", parameters);
                }

                /**
                 * List available networks
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/network", null);
                }

                /**
                 * Create network device configuration
                 *
                 * @param iface Network interface name.
                 * @param type Network interface type Enum:
                 * bridge,bond,eth,alias,vlan,OVSBridge,OVSBond,OVSPort,OVSIntPort,unknown
                 * @param address IP address.
                 * @param address6 IP address.
                 * @param autostart Automatically start interface on boot.
                 * @param bond_primary Specify the primary interface for
                 * active-backup bond.
                 * @param bond_mode Bonding mode. Enum:
                 * balance-rr,active-backup,balance-xor,broadcast,802.3ad,balance-tlb,balance-alb,balance-slb,lacp-balance-slb,lacp-balance-tcp
                 * @param bond_xmit_hash_policy Selects the transmit hash policy
                 * to use for slave selection in balance-xor and 802.3ad modes.
                 * Enum: layer2,layer2+3,layer3+4
                 * @param bridge_ports Specify the interfaces you want to add to
                 * your bridge.
                 * @param bridge_vlan_aware Enable bridge vlan support.
                 * @param cidr IPv4 CIDR.
                 * @param cidr6 IPv6 CIDR.
                 * @param comments Comments
                 * @param comments6 Comments
                 * @param gateway Default gateway address.
                 * @param gateway6 Default ipv6 gateway address.
                 * @param mtu MTU.
                 * @param netmask Network mask.
                 * @param netmask6 Network mask.
                 * @param ovs_bonds Specify the interfaces used by the bonding
                 * device.
                 * @param ovs_bridge The OVS bridge associated with a OVS port.
                 * This is required when you create an OVS port.
                 * @param ovs_options OVS interface options.
                 * @param ovs_ports Specify the interfaces you want to add to
                 * your bridge.
                 * @param ovs_tag Specify a VLan tag (used by OVSPort,
                 * OVSIntPort, OVSBond)
                 * @param slaves Specify the interfaces used by the bonding
                 * device.
                 * @param vlan_id vlan-id for a custom named vlan interface
                 * (ifupdown2 only).
                 * @param vlan_raw_device Specify the raw interface for the vlan
                 * interface.
                 * @return Result
                 * @throws JSONException
                 */

                public Result createNetwork(String iface, String type, String address, String address6, Boolean autostart, String bond_primary, String bond_mode, String bond_xmit_hash_policy, String bridge_ports, Boolean bridge_vlan_aware, String cidr, String cidr6, String comments, String comments6, String gateway, String gateway6, Integer mtu, String netmask, Integer netmask6, String ovs_bonds, String ovs_bridge, String ovs_options, String ovs_ports, Integer ovs_tag, String slaves, Integer vlan_id, String vlan_raw_device) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("iface", iface);
                    parameters.put("type", type);
                    parameters.put("address", address);
                    parameters.put("address6", address6);
                    parameters.put("autostart", autostart);
                    parameters.put("bond-primary", bond_primary);
                    parameters.put("bond_mode", bond_mode);
                    parameters.put("bond_xmit_hash_policy", bond_xmit_hash_policy);
                    parameters.put("bridge_ports", bridge_ports);
                    parameters.put("bridge_vlan_aware", bridge_vlan_aware);
                    parameters.put("cidr", cidr);
                    parameters.put("cidr6", cidr6);
                    parameters.put("comments", comments);
                    parameters.put("comments6", comments6);
                    parameters.put("gateway", gateway);
                    parameters.put("gateway6", gateway6);
                    parameters.put("mtu", mtu);
                    parameters.put("netmask", netmask);
                    parameters.put("netmask6", netmask6);
                    parameters.put("ovs_bonds", ovs_bonds);
                    parameters.put("ovs_bridge", ovs_bridge);
                    parameters.put("ovs_options", ovs_options);
                    parameters.put("ovs_ports", ovs_ports);
                    parameters.put("ovs_tag", ovs_tag);
                    parameters.put("slaves", slaves);
                    parameters.put("vlan-id", vlan_id);
                    parameters.put("vlan-raw-device", vlan_raw_device);
                    return _client.create("/nodes/" + _node + "/network", parameters);
                }

                /**
                 * Create network device configuration
                 *
                 * @param iface Network interface name.
                 * @param type Network interface type Enum:
                 * bridge,bond,eth,alias,vlan,OVSBridge,OVSBond,OVSPort,OVSIntPort,unknown
                 * @return Result
                 * @throws JSONException
                 */

                public Result createNetwork(String iface, String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("iface", iface);
                    parameters.put("type", type);
                    return _client.create("/nodes/" + _node + "/network", parameters);
                }

                /**
                 * Reload network configuration
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result reloadNetworkConfig() throws JSONException {
                    return _client.set("/nodes/" + _node + "/network", null);
                }

            }

            public class PVETasks {

                private final PveClient _client;
                private final Object _node;

                protected PVETasks(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEUpidItem get(Object upid) {
                    return new PVEUpidItem(_client, _node, upid);
                }

                public class PVEUpidItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _upid;

                    protected PVEUpidItem(PveClient client, Object node, Object upid) {
                        _client = client;
                        _node = node;
                        _upid = upid;
                    }

                    private PVELog _log;

                    public PVELog getLog() {
                        return _log == null ? (_log = new PVELog(_client, _node, _upid)) : _log;
                    }
                    private PVEStatus _status;

                    public PVEStatus getStatus() {
                        return _status == null ? (_status = new PVEStatus(_client, _node, _upid)) : _status;
                    }

                    public class PVELog {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _upid;

                        protected PVELog(PveClient client, Object node, Object upid) {
                            _client = client;
                            _node = node;
                            _upid = upid;
                        }

                        /**
                         * Read task log.
                         *
                         * @param limit The maximum amount of lines that should
                         * be printed.
                         * @param start The line number to start printing at.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result readTaskLog(Integer limit, Integer start) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("limit", limit);
                            parameters.put("start", start);
                            return _client.get("/nodes/" + _node + "/tasks/" + _upid + "/log", parameters);
                        }

                        /**
                         * Read task log.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result readTaskLog() throws JSONException {
                            return _client.get("/nodes/" + _node + "/tasks/" + _upid + "/log", null);
                        }

                    }

                    public class PVEStatus {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _upid;

                        protected PVEStatus(PveClient client, Object node, Object upid) {
                            _client = client;
                            _node = node;
                            _upid = upid;
                        }

                        /**
                         * Read task status.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result readTaskStatus() throws JSONException {
                            return _client.get("/nodes/" + _node + "/tasks/" + _upid + "/status", null);
                        }

                    }

                    /**
                     * Stop a task.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result stopTask() throws JSONException {
                        return _client.delete("/nodes/" + _node + "/tasks/" + _upid + "", null);
                    }

                    /**
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result upidIndex() throws JSONException {
                        return _client.get("/nodes/" + _node + "/tasks/" + _upid + "", null);
                    }

                }

                /**
                 * Read task list for one node (finished tasks).
                 *
                 * @param errors Only list tasks with a status of ERROR.
                 * @param limit Only list this amount of tasks.
                 * @param since Only list tasks since this UNIX epoch.
                 * @param source List archived, active or all tasks. Enum:
                 * archive,active,all
                 * @param start List tasks beginning from this offset.
                 * @param statusfilter List of Task States that should be
                 * returned.
                 * @param typefilter Only list tasks of this type (e.g.,
                 * vzstart, vzdump).
                 * @param until Only list tasks until this UNIX epoch.
                 * @param userfilter Only list tasks from this user.
                 * @param vmid Only list tasks for this VM.
                 * @return Result
                 * @throws JSONException
                 */
                public Result nodeTasks(Boolean errors, Integer limit, Integer since, String source, Integer start, String statusfilter, String typefilter, Integer until, String userfilter, Integer vmid) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("errors", errors);
                    parameters.put("limit", limit);
                    parameters.put("since", since);
                    parameters.put("source", source);
                    parameters.put("start", start);
                    parameters.put("statusfilter", statusfilter);
                    parameters.put("typefilter", typefilter);
                    parameters.put("until", until);
                    parameters.put("userfilter", userfilter);
                    parameters.put("vmid", vmid);
                    return _client.get("/nodes/" + _node + "/tasks", parameters);
                }

                /**
                 * Read task list for one node (finished tasks).
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result nodeTasks() throws JSONException {
                    return _client.get("/nodes/" + _node + "/tasks", null);
                }

            }

            public class PVEScan {

                private final PveClient _client;
                private final Object _node;

                protected PVEScan(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVENfs _nfs;

                public PVENfs getNfs() {
                    return _nfs == null ? (_nfs = new PVENfs(_client, _node)) : _nfs;
                }
                private PVECifs _cifs;

                public PVECifs getCifs() {
                    return _cifs == null ? (_cifs = new PVECifs(_client, _node)) : _cifs;
                }
                private PVEPbs _pbs;

                public PVEPbs getPbs() {
                    return _pbs == null ? (_pbs = new PVEPbs(_client, _node)) : _pbs;
                }
                private PVEGlusterfs _glusterfs;

                public PVEGlusterfs getGlusterfs() {
                    return _glusterfs == null ? (_glusterfs = new PVEGlusterfs(_client, _node)) : _glusterfs;
                }
                private PVEIscsi _iscsi;

                public PVEIscsi getIscsi() {
                    return _iscsi == null ? (_iscsi = new PVEIscsi(_client, _node)) : _iscsi;
                }
                private PVELvm _lvm;

                public PVELvm getLvm() {
                    return _lvm == null ? (_lvm = new PVELvm(_client, _node)) : _lvm;
                }
                private PVELvmthin _lvmthin;

                public PVELvmthin getLvmthin() {
                    return _lvmthin == null ? (_lvmthin = new PVELvmthin(_client, _node)) : _lvmthin;
                }
                private PVEZfs _zfs;

                public PVEZfs getZfs() {
                    return _zfs == null ? (_zfs = new PVEZfs(_client, _node)) : _zfs;
                }

                public class PVENfs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVENfs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Scan remote NFS server.
                     *
                     * @param server The server address (name or IP).
                     * @return Result
                     * @throws JSONException
                     */
                    public Result nfsscan(String server) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("server", server);
                        return _client.get("/nodes/" + _node + "/scan/nfs", parameters);
                    }

                }

                public class PVECifs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVECifs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Scan remote CIFS server.
                     *
                     * @param server The server address (name or IP).
                     * @param domain SMB domain (Workgroup).
                     * @param password User password.
                     * @param username User name.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result cifsscan(String server, String domain, String password, String username) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("server", server);
                        parameters.put("domain", domain);
                        parameters.put("password", password);
                        parameters.put("username", username);
                        return _client.get("/nodes/" + _node + "/scan/cifs", parameters);
                    }

                    /**
                     * Scan remote CIFS server.
                     *
                     * @param server The server address (name or IP).
                     * @return Result
                     * @throws JSONException
                     */

                    public Result cifsscan(String server) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("server", server);
                        return _client.get("/nodes/" + _node + "/scan/cifs", parameters);
                    }

                }

                public class PVEPbs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEPbs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Scan remote Proxmox Backup Server.
                     *
                     * @param password User password or API token secret.
                     * @param server The server address (name or IP).
                     * @param username User-name or API token-ID.
                     * @param fingerprint Certificate SHA 256 fingerprint.
                     * @param port Optional port.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result pbsscan(String password, String server, String username, String fingerprint, Integer port) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("password", password);
                        parameters.put("server", server);
                        parameters.put("username", username);
                        parameters.put("fingerprint", fingerprint);
                        parameters.put("port", port);
                        return _client.get("/nodes/" + _node + "/scan/pbs", parameters);
                    }

                    /**
                     * Scan remote Proxmox Backup Server.
                     *
                     * @param password User password or API token secret.
                     * @param server The server address (name or IP).
                     * @param username User-name or API token-ID.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result pbsscan(String password, String server, String username) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("password", password);
                        parameters.put("server", server);
                        parameters.put("username", username);
                        return _client.get("/nodes/" + _node + "/scan/pbs", parameters);
                    }

                }

                public class PVEGlusterfs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEGlusterfs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Scan remote GlusterFS server.
                     *
                     * @param server The server address (name or IP).
                     * @return Result
                     * @throws JSONException
                     */
                    public Result glusterfsscan(String server) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("server", server);
                        return _client.get("/nodes/" + _node + "/scan/glusterfs", parameters);
                    }

                }

                public class PVEIscsi {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEIscsi(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Scan remote iSCSI server.
                     *
                     * @param portal The iSCSI portal (IP or DNS name with
                     * optional port).
                     * @return Result
                     * @throws JSONException
                     */
                    public Result iscsiscan(String portal) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("portal", portal);
                        return _client.get("/nodes/" + _node + "/scan/iscsi", parameters);
                    }

                }

                public class PVELvm {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVELvm(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * List local LVM volume groups.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result lvmscan() throws JSONException {
                        return _client.get("/nodes/" + _node + "/scan/lvm", null);
                    }

                }

                public class PVELvmthin {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVELvmthin(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * List local LVM Thin Pools.
                     *
                     * @param vg
                     * @return Result
                     * @throws JSONException
                     */
                    public Result lvmthinscan(String vg) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("vg", vg);
                        return _client.get("/nodes/" + _node + "/scan/lvmthin", parameters);
                    }

                }

                public class PVEZfs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEZfs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Scan zfs pool list on local node.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result zfsscan() throws JSONException {
                        return _client.get("/nodes/" + _node + "/scan/zfs", null);
                    }

                }

                /**
                 * Index of available scan methods
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/scan", null);
                }

            }

            public class PVEHardware {

                private final PveClient _client;
                private final Object _node;

                protected PVEHardware(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEPci _pci;

                public PVEPci getPci() {
                    return _pci == null ? (_pci = new PVEPci(_client, _node)) : _pci;
                }
                private PVEUsb _usb;

                public PVEUsb getUsb() {
                    return _usb == null ? (_usb = new PVEUsb(_client, _node)) : _usb;
                }

                public class PVEPci {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEPci(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVEPciidItem get(Object pciid) {
                        return new PVEPciidItem(_client, _node, pciid);
                    }

                    public class PVEPciidItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _pciid;

                        protected PVEPciidItem(PveClient client, Object node, Object pciid) {
                            _client = client;
                            _node = node;
                            _pciid = pciid;
                        }

                        private PVEMdev _mdev;

                        public PVEMdev getMdev() {
                            return _mdev == null ? (_mdev = new PVEMdev(_client, _node, _pciid)) : _mdev;
                        }

                        public class PVEMdev {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _pciid;

                            protected PVEMdev(PveClient client, Object node, Object pciid) {
                                _client = client;
                                _node = node;
                                _pciid = pciid;
                            }

                            /**
                             * List mediated device types for given PCI device.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result mdevscan() throws JSONException {
                                return _client.get("/nodes/" + _node + "/hardware/pci/" + _pciid + "/mdev", null);
                            }

                        }

                        /**
                         * Index of available pci methods
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result pciindex() throws JSONException {
                            return _client.get("/nodes/" + _node + "/hardware/pci/" + _pciid + "", null);
                        }

                    }

                    /**
                     * List local PCI devices.
                     *
                     * @param pci_class_blacklist A list of blacklisted PCI
                     * classes, which will not be returned. Following are
                     * filtered by default: Memory Controller (05), Bridge (06)
                     * and Processor (0b).
                     * @param verbose If disabled, does only print the PCI IDs.
                     * Otherwise, additional information like vendor and device
                     * will be returned.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result pciscan(String pci_class_blacklist, Boolean verbose) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("pci-class-blacklist", pci_class_blacklist);
                        parameters.put("verbose", verbose);
                        return _client.get("/nodes/" + _node + "/hardware/pci", parameters);
                    }

                    /**
                     * List local PCI devices.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result pciscan() throws JSONException {
                        return _client.get("/nodes/" + _node + "/hardware/pci", null);
                    }

                }

                public class PVEUsb {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEUsb(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * List local USB devices.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result usbscan() throws JSONException {
                        return _client.get("/nodes/" + _node + "/hardware/usb", null);
                    }

                }

                /**
                 * Index of hardware types
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/hardware", null);
                }

            }

            public class PVECapabilities {

                private final PveClient _client;
                private final Object _node;

                protected PVECapabilities(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEQemu _qemu;

                public PVEQemu getQemu() {
                    return _qemu == null ? (_qemu = new PVEQemu(_client, _node)) : _qemu;
                }

                public class PVEQemu {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEQemu(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    private PVECpu _cpu;

                    public PVECpu getCpu() {
                        return _cpu == null ? (_cpu = new PVECpu(_client, _node)) : _cpu;
                    }
                    private PVEMachines _machines;

                    public PVEMachines getMachines() {
                        return _machines == null ? (_machines = new PVEMachines(_client, _node)) : _machines;
                    }

                    public class PVECpu {

                        private final PveClient _client;
                        private final Object _node;

                        protected PVECpu(PveClient client, Object node) {
                            _client = client;
                            _node = node;
                        }

                        /**
                         * List all custom and default CPU models.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result index() throws JSONException {
                            return _client.get("/nodes/" + _node + "/capabilities/qemu/cpu", null);
                        }

                    }

                    public class PVEMachines {

                        private final PveClient _client;
                        private final Object _node;

                        protected PVEMachines(PveClient client, Object node) {
                            _client = client;
                            _node = node;
                        }

                        /**
                         * Get available QEMU/KVM machine types.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result types() throws JSONException {
                            return _client.get("/nodes/" + _node + "/capabilities/qemu/machines", null);
                        }

                    }

                    /**
                     * QEMU capabilities index.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result qemuCapsIndex() throws JSONException {
                        return _client.get("/nodes/" + _node + "/capabilities/qemu", null);
                    }

                }

                /**
                 * Node capabilities index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/capabilities", null);
                }

            }

            public class PVEStorage {

                private final PveClient _client;
                private final Object _node;

                protected PVEStorage(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEStorageItem get(Object storage) {
                    return new PVEStorageItem(_client, _node, storage);
                }

                public class PVEStorageItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _storage;

                    protected PVEStorageItem(PveClient client, Object node, Object storage) {
                        _client = client;
                        _node = node;
                        _storage = storage;
                    }

                    private PVEPrunebackups _prunebackups;

                    public PVEPrunebackups getPrunebackups() {
                        return _prunebackups == null ? (_prunebackups = new PVEPrunebackups(_client, _node, _storage)) : _prunebackups;
                    }
                    private PVEContent _content;

                    public PVEContent getContent() {
                        return _content == null ? (_content = new PVEContent(_client, _node, _storage)) : _content;
                    }
                    private PVEFileRestore _fileRestore;

                    public PVEFileRestore getFileRestore() {
                        return _fileRestore == null ? (_fileRestore = new PVEFileRestore(_client, _node, _storage)) : _fileRestore;
                    }
                    private PVEStatus _status;

                    public PVEStatus getStatus() {
                        return _status == null ? (_status = new PVEStatus(_client, _node, _storage)) : _status;
                    }
                    private PVERrd _rrd;

                    public PVERrd getRrd() {
                        return _rrd == null ? (_rrd = new PVERrd(_client, _node, _storage)) : _rrd;
                    }
                    private PVERrddata _rrddata;

                    public PVERrddata getRrddata() {
                        return _rrddata == null ? (_rrddata = new PVERrddata(_client, _node, _storage)) : _rrddata;
                    }
                    private PVEUpload _upload;

                    public PVEUpload getUpload() {
                        return _upload == null ? (_upload = new PVEUpload(_client, _node, _storage)) : _upload;
                    }
                    private PVEDownloadUrl _downloadUrl;

                    public PVEDownloadUrl getDownloadUrl() {
                        return _downloadUrl == null ? (_downloadUrl = new PVEDownloadUrl(_client, _node, _storage)) : _downloadUrl;
                    }

                    public class PVEPrunebackups {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVEPrunebackups(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        /**
                         * Prune backups. Only those using the standard naming
                         * scheme are considered.
                         *
                         * @param prune_backups Use these retention options
                         * instead of those from the storage configuration.
                         * @param type Either 'qemu' or 'lxc'. Only consider
                         * backups for guests of this type. Enum: qemu,lxc
                         * @param vmid Only prune backups for this VM.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result delete(String prune_backups, String type, Integer vmid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("prune-backups", prune_backups);
                            parameters.put("type", type);
                            parameters.put("vmid", vmid);
                            return _client.delete("/nodes/" + _node + "/storage/" + _storage + "/prunebackups", parameters);
                        }

                        /**
                         * Prune backups. Only those using the standard naming
                         * scheme are considered.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result delete() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/storage/" + _storage + "/prunebackups", null);
                        }

                        /**
                         * Get prune information for backups. NOTE: this is only
                         * a preview and might not be what a subsequent prune
                         * call does if backups are removed/added in the
                         * meantime.
                         *
                         * @param prune_backups Use these retention options
                         * instead of those from the storage configuration.
                         * @param type Either 'qemu' or 'lxc'. Only consider
                         * backups for guests of this type. Enum: qemu,lxc
                         * @param vmid Only consider backups for this guest.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result dryrun(String prune_backups, String type, Integer vmid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("prune-backups", prune_backups);
                            parameters.put("type", type);
                            parameters.put("vmid", vmid);
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/prunebackups", parameters);
                        }

                        /**
                         * Get prune information for backups. NOTE: this is only
                         * a preview and might not be what a subsequent prune
                         * call does if backups are removed/added in the
                         * meantime.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result dryrun() throws JSONException {
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/prunebackups", null);
                        }

                    }

                    public class PVEContent {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVEContent(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        public PVEVolumeItem get(Object volume) {
                            return new PVEVolumeItem(_client, _node, _storage, volume);
                        }

                        public class PVEVolumeItem {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _storage;
                            private final Object _volume;

                            protected PVEVolumeItem(PveClient client, Object node, Object storage, Object volume) {
                                _client = client;
                                _node = node;
                                _storage = storage;
                                _volume = volume;
                            }

                            /**
                             * Delete volume
                             *
                             * @param delay Time to wait for the task to finish.
                             * We return 'null' if the task finish within that
                             * time.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result delete(Integer delay) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("delay", delay);
                                return _client.delete("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", parameters);
                            }

                            /**
                             * Delete volume
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result delete() throws JSONException {
                                return _client.delete("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", null);
                            }

                            /**
                             * Get volume attributes
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result info() throws JSONException {
                                return _client.get("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", null);
                            }

                            /**
                             * Copy a volume. This is experimental code - do not
                             * use.
                             *
                             * @param target Target volume identifier
                             * @param target_node Target node. Default is local
                             * node.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result copy(String target, String target_node) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("target", target);
                                parameters.put("target_node", target_node);
                                return _client.create("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", parameters);
                            }

                            /**
                             * Copy a volume. This is experimental code - do not
                             * use.
                             *
                             * @param target Target volume identifier
                             * @return Result
                             * @throws JSONException
                             */

                            public Result copy(String target) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("target", target);
                                return _client.create("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", parameters);
                            }

                            /**
                             * Update volume attributes
                             *
                             * @param notes The new notes.
                             * @param protected_ Protection status. Currently
                             * only supported for backups.
                             * @return Result
                             * @throws JSONException
                             */

                            public Result updateattributes(String notes, Boolean protected_) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("notes", notes);
                                parameters.put("protected", protected_);
                                return _client.set("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", parameters);
                            }

                            /**
                             * Update volume attributes
                             *
                             * @return Result
                             * @throws JSONException
                             */

                            public Result updateattributes() throws JSONException {
                                return _client.set("/nodes/" + _node + "/storage/" + _storage + "/content/" + _volume + "", null);
                            }

                        }

                        /**
                         * List storage content.
                         *
                         * @param content Only list content of this type.
                         * @param vmid Only list images for this VM
                         * @return Result
                         * @throws JSONException
                         */
                        public Result index(String content, Integer vmid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("content", content);
                            parameters.put("vmid", vmid);
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/content", parameters);
                        }

                        /**
                         * List storage content.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result index() throws JSONException {
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/content", null);
                        }

                        /**
                         * Allocate disk images.
                         *
                         * @param filename The name of the file to create.
                         * @param size Size in kilobyte (1024 bytes). Optional
                         * suffixes 'M' (megabyte, 1024K) and 'G' (gigabyte,
                         * 1024M)
                         * @param vmid Specify owner VM
                         * @param format Enum: raw,qcow2,subvol
                         * @return Result
                         * @throws JSONException
                         */

                        public Result create(String filename, String size, int vmid, String format) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("filename", filename);
                            parameters.put("size", size);
                            parameters.put("vmid", vmid);
                            parameters.put("format", format);
                            return _client.create("/nodes/" + _node + "/storage/" + _storage + "/content", parameters);
                        }

                        /**
                         * Allocate disk images.
                         *
                         * @param filename The name of the file to create.
                         * @param size Size in kilobyte (1024 bytes). Optional
                         * suffixes 'M' (megabyte, 1024K) and 'G' (gigabyte,
                         * 1024M)
                         * @param vmid Specify owner VM
                         * @return Result
                         * @throws JSONException
                         */

                        public Result create(String filename, String size, int vmid) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("filename", filename);
                            parameters.put("size", size);
                            parameters.put("vmid", vmid);
                            return _client.create("/nodes/" + _node + "/storage/" + _storage + "/content", parameters);
                        }

                    }

                    public class PVEFileRestore {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVEFileRestore(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        private PVEList _list;

                        public PVEList getList() {
                            return _list == null ? (_list = new PVEList(_client, _node, _storage)) : _list;
                        }
                        private PVEDownload _download;

                        public PVEDownload getDownload() {
                            return _download == null ? (_download = new PVEDownload(_client, _node, _storage)) : _download;
                        }

                        public class PVEList {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _storage;

                            protected PVEList(PveClient client, Object node, Object storage) {
                                _client = client;
                                _node = node;
                                _storage = storage;
                            }

                            /**
                             * List files and directories for single file
                             * restore under the given path.
                             *
                             * @param filepath base64-path to the directory or
                             * file being listed, or "/".
                             * @param volume Backup volume ID or name. Currently
                             * only PBS snapshots are supported.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result list(String filepath, String volume) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("filepath", filepath);
                                parameters.put("volume", volume);
                                return _client.get("/nodes/" + _node + "/storage/" + _storage + "/file-restore/list", parameters);
                            }

                        }

                        public class PVEDownload {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _storage;

                            protected PVEDownload(PveClient client, Object node, Object storage) {
                                _client = client;
                                _node = node;
                                _storage = storage;
                            }

                            /**
                             * Extract a file or directory (as zip archive) from
                             * a PBS backup.
                             *
                             * @param filepath base64-path to the directory or
                             * file to download.
                             * @param volume Backup volume ID or name. Currently
                             * only PBS snapshots are supported.
                             * @return Result
                             * @throws JSONException
                             */
                            public Result download(String filepath, String volume) throws JSONException {
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("filepath", filepath);
                                parameters.put("volume", volume);
                                return _client.get("/nodes/" + _node + "/storage/" + _storage + "/file-restore/download", parameters);
                            }

                        }

                    }

                    public class PVEStatus {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVEStatus(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        /**
                         * Read storage status.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result readStatus() throws JSONException {
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/status", null);
                        }

                    }

                    public class PVERrd {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVERrd(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        /**
                         * Read storage RRD statistics (returns PNG).
                         *
                         * @param ds The list of datasources you want to
                         * display.
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @param cf The RRD consolidation function Enum:
                         * AVERAGE,MAX
                         * @return Result
                         * @throws JSONException
                         */
                        public Result rrd(String ds, String timeframe, String cf) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("ds", ds);
                            parameters.put("timeframe", timeframe);
                            parameters.put("cf", cf);
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/rrd", parameters);
                        }

                        /**
                         * Read storage RRD statistics (returns PNG).
                         *
                         * @param ds The list of datasources you want to
                         * display.
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @return Result
                         * @throws JSONException
                         */

                        public Result rrd(String ds, String timeframe) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("ds", ds);
                            parameters.put("timeframe", timeframe);
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/rrd", parameters);
                        }

                    }

                    public class PVERrddata {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVERrddata(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        /**
                         * Read storage RRD statistics.
                         *
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @param cf The RRD consolidation function Enum:
                         * AVERAGE,MAX
                         * @return Result
                         * @throws JSONException
                         */
                        public Result rrddata(String timeframe, String cf) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("timeframe", timeframe);
                            parameters.put("cf", cf);
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/rrddata", parameters);
                        }

                        /**
                         * Read storage RRD statistics.
                         *
                         * @param timeframe Specify the time frame you are
                         * interested in. Enum: hour,day,week,month,year
                         * @return Result
                         * @throws JSONException
                         */

                        public Result rrddata(String timeframe) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("timeframe", timeframe);
                            return _client.get("/nodes/" + _node + "/storage/" + _storage + "/rrddata", parameters);
                        }

                    }

                    public class PVEUpload {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVEUpload(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        /**
                         * Upload templates and ISO images.
                         *
                         * @param content Content type. Enum: iso,vztmpl
                         * @param filename The name of the file to create.
                         * Caution: This will be normalized!
                         * @param checksum The expected checksum of the file.
                         * @param checksum_algorithm The algorithm to calculate
                         * the checksum of the file. Enum:
                         * md5,sha1,sha224,sha256,sha384,sha512
                         * @param tmpfilename The source file name. This
                         * parameter is usually set by the REST handler. You can
                         * only overwrite it when connecting to the trusted port
                         * on localhost.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result upload(String content, String filename, String checksum, String checksum_algorithm, String tmpfilename) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("content", content);
                            parameters.put("filename", filename);
                            parameters.put("checksum", checksum);
                            parameters.put("checksum-algorithm", checksum_algorithm);
                            parameters.put("tmpfilename", tmpfilename);
                            return _client.create("/nodes/" + _node + "/storage/" + _storage + "/upload", parameters);
                        }

                        /**
                         * Upload templates and ISO images.
                         *
                         * @param content Content type. Enum: iso,vztmpl
                         * @param filename The name of the file to create.
                         * Caution: This will be normalized!
                         * @return Result
                         * @throws JSONException
                         */

                        public Result upload(String content, String filename) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("content", content);
                            parameters.put("filename", filename);
                            return _client.create("/nodes/" + _node + "/storage/" + _storage + "/upload", parameters);
                        }

                    }

                    public class PVEDownloadUrl {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _storage;

                        protected PVEDownloadUrl(PveClient client, Object node, Object storage) {
                            _client = client;
                            _node = node;
                            _storage = storage;
                        }

                        /**
                         * Download templates and ISO images by using an URL.
                         *
                         * @param content Content type. Enum: iso,vztmpl
                         * @param filename The name of the file to create.
                         * Caution: This will be normalized!
                         * @param url The URL to download the file from.
                         * @param checksum The expected checksum of the file.
                         * @param checksum_algorithm The algorithm to calculate
                         * the checksum of the file. Enum:
                         * md5,sha1,sha224,sha256,sha384,sha512
                         * @param verify_certificates If false, no SSL/TLS
                         * certificates will be verified.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result downloadUrl(String content, String filename, String url, String checksum, String checksum_algorithm, Boolean verify_certificates) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("content", content);
                            parameters.put("filename", filename);
                            parameters.put("url", url);
                            parameters.put("checksum", checksum);
                            parameters.put("checksum-algorithm", checksum_algorithm);
                            parameters.put("verify-certificates", verify_certificates);
                            return _client.create("/nodes/" + _node + "/storage/" + _storage + "/download-url", parameters);
                        }

                        /**
                         * Download templates and ISO images by using an URL.
                         *
                         * @param content Content type. Enum: iso,vztmpl
                         * @param filename The name of the file to create.
                         * Caution: This will be normalized!
                         * @param url The URL to download the file from.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result downloadUrl(String content, String filename, String url) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("content", content);
                            parameters.put("filename", filename);
                            parameters.put("url", url);
                            return _client.create("/nodes/" + _node + "/storage/" + _storage + "/download-url", parameters);
                        }

                    }

                    /**
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result diridx() throws JSONException {
                        return _client.get("/nodes/" + _node + "/storage/" + _storage + "", null);
                    }

                }

                /**
                 * Get status for all datastores.
                 *
                 * @param content Only list stores which support this content
                 * type.
                 * @param enabled Only list stores which are enabled (not
                 * disabled in config).
                 * @param format Include information about formats
                 * @param storage Only list status for specified storage
                 * @param target If target is different to 'node', we only lists
                 * shared storages which content is accessible on this 'node'
                 * and the specified 'target' node.
                 * @return Result
                 * @throws JSONException
                 */
                public Result index(String content, Boolean enabled, Boolean format, String storage, String target) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("content", content);
                    parameters.put("enabled", enabled);
                    parameters.put("format", format);
                    parameters.put("storage", storage);
                    parameters.put("target", target);
                    return _client.get("/nodes/" + _node + "/storage", parameters);
                }

                /**
                 * Get status for all datastores.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/storage", null);
                }

            }

            public class PVEDisks {

                private final PveClient _client;
                private final Object _node;

                protected PVEDisks(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVELvm _lvm;

                public PVELvm getLvm() {
                    return _lvm == null ? (_lvm = new PVELvm(_client, _node)) : _lvm;
                }
                private PVELvmthin _lvmthin;

                public PVELvmthin getLvmthin() {
                    return _lvmthin == null ? (_lvmthin = new PVELvmthin(_client, _node)) : _lvmthin;
                }
                private PVEDirectory _directory;

                public PVEDirectory getDirectory() {
                    return _directory == null ? (_directory = new PVEDirectory(_client, _node)) : _directory;
                }
                private PVEZfs _zfs;

                public PVEZfs getZfs() {
                    return _zfs == null ? (_zfs = new PVEZfs(_client, _node)) : _zfs;
                }
                private PVEList _list;

                public PVEList getList() {
                    return _list == null ? (_list = new PVEList(_client, _node)) : _list;
                }
                private PVESmart _smart;

                public PVESmart getSmart() {
                    return _smart == null ? (_smart = new PVESmart(_client, _node)) : _smart;
                }
                private PVEInitgpt _initgpt;

                public PVEInitgpt getInitgpt() {
                    return _initgpt == null ? (_initgpt = new PVEInitgpt(_client, _node)) : _initgpt;
                }
                private PVEWipedisk _wipedisk;

                public PVEWipedisk getWipedisk() {
                    return _wipedisk == null ? (_wipedisk = new PVEWipedisk(_client, _node)) : _wipedisk;
                }

                public class PVELvm {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVELvm(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Remove an LVM Volume Group.
                         *
                         * @param cleanup_config Marks associated storage(s) as
                         * not available on this node anymore or removes them
                         * from the configuration (if configured for this node
                         * only).
                         * @param cleanup_disks Also wipe disks so they can be
                         * repurposed afterwards.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result delete(Boolean cleanup_config, Boolean cleanup_disks) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("cleanup-config", cleanup_config);
                            parameters.put("cleanup-disks", cleanup_disks);
                            return _client.delete("/nodes/" + _node + "/disks/lvm/" + _name + "", parameters);
                        }

                        /**
                         * Remove an LVM Volume Group.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result delete() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/disks/lvm/" + _name + "", null);
                        }

                    }

                    /**
                     * List LVM Volume Groups
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/disks/lvm", null);
                    }

                    /**
                     * Create an LVM Volume Group
                     *
                     * @param device The block device you want to create the
                     * volume group on
                     * @param name The storage identifier.
                     * @param add_storage Configure storage using the Volume
                     * Group
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String device, String name, Boolean add_storage) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("device", device);
                        parameters.put("name", name);
                        parameters.put("add_storage", add_storage);
                        return _client.create("/nodes/" + _node + "/disks/lvm", parameters);
                    }

                    /**
                     * Create an LVM Volume Group
                     *
                     * @param device The block device you want to create the
                     * volume group on
                     * @param name The storage identifier.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String device, String name) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("device", device);
                        parameters.put("name", name);
                        return _client.create("/nodes/" + _node + "/disks/lvm", parameters);
                    }

                }

                public class PVELvmthin {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVELvmthin(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Remove an LVM thin pool.
                         *
                         * @param volume_group The storage identifier.
                         * @param cleanup_config Marks associated storage(s) as
                         * not available on this node anymore or removes them
                         * from the configuration (if configured for this node
                         * only).
                         * @param cleanup_disks Also wipe disks so they can be
                         * repurposed afterwards.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result delete(String volume_group, Boolean cleanup_config, Boolean cleanup_disks) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("volume-group", volume_group);
                            parameters.put("cleanup-config", cleanup_config);
                            parameters.put("cleanup-disks", cleanup_disks);
                            return _client.delete("/nodes/" + _node + "/disks/lvmthin/" + _name + "", parameters);
                        }

                        /**
                         * Remove an LVM thin pool.
                         *
                         * @param volume_group The storage identifier.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result delete(String volume_group) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("volume-group", volume_group);
                            return _client.delete("/nodes/" + _node + "/disks/lvmthin/" + _name + "", parameters);
                        }

                    }

                    /**
                     * List LVM thinpools
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/disks/lvmthin", null);
                    }

                    /**
                     * Create an LVM thinpool
                     *
                     * @param device The block device you want to create the
                     * thinpool on.
                     * @param name The storage identifier.
                     * @param add_storage Configure storage using the thinpool.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String device, String name, Boolean add_storage) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("device", device);
                        parameters.put("name", name);
                        parameters.put("add_storage", add_storage);
                        return _client.create("/nodes/" + _node + "/disks/lvmthin", parameters);
                    }

                    /**
                     * Create an LVM thinpool
                     *
                     * @param device The block device you want to create the
                     * thinpool on.
                     * @param name The storage identifier.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String device, String name) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("device", device);
                        parameters.put("name", name);
                        return _client.create("/nodes/" + _node + "/disks/lvmthin", parameters);
                    }

                }

                public class PVEDirectory {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEDirectory(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Unmounts the storage and removes the mount unit.
                         *
                         * @param cleanup_config Marks associated storage(s) as
                         * not available on this node anymore or removes them
                         * from the configuration (if configured for this node
                         * only).
                         * @param cleanup_disks Also wipe disk so it can be
                         * repurposed afterwards.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result delete(Boolean cleanup_config, Boolean cleanup_disks) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("cleanup-config", cleanup_config);
                            parameters.put("cleanup-disks", cleanup_disks);
                            return _client.delete("/nodes/" + _node + "/disks/directory/" + _name + "", parameters);
                        }

                        /**
                         * Unmounts the storage and removes the mount unit.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result delete() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/disks/directory/" + _name + "", null);
                        }

                    }

                    /**
                     * PVE Managed Directory storages.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/disks/directory", null);
                    }

                    /**
                     * Create a Filesystem on an unused disk. Will be mounted
                     * under '/mnt/pve/NAME'.
                     *
                     * @param device The block device you want to create the
                     * filesystem on.
                     * @param name The storage identifier.
                     * @param add_storage Configure storage using the directory.
                     * @param filesystem The desired filesystem. Enum: ext4,xfs
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String device, String name, Boolean add_storage, String filesystem) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("device", device);
                        parameters.put("name", name);
                        parameters.put("add_storage", add_storage);
                        parameters.put("filesystem", filesystem);
                        return _client.create("/nodes/" + _node + "/disks/directory", parameters);
                    }

                    /**
                     * Create a Filesystem on an unused disk. Will be mounted
                     * under '/mnt/pve/NAME'.
                     *
                     * @param device The block device you want to create the
                     * filesystem on.
                     * @param name The storage identifier.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String device, String name) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("device", device);
                        parameters.put("name", name);
                        return _client.create("/nodes/" + _node + "/disks/directory", parameters);
                    }

                }

                public class PVEZfs {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEZfs(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVENameItem get(Object name) {
                        return new PVENameItem(_client, _node, name);
                    }

                    public class PVENameItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _name;

                        protected PVENameItem(PveClient client, Object node, Object name) {
                            _client = client;
                            _node = node;
                            _name = name;
                        }

                        /**
                         * Destroy a ZFS pool.
                         *
                         * @param cleanup_config Marks associated storage(s) as
                         * not available on this node anymore or removes them
                         * from the configuration (if configured for this node
                         * only).
                         * @param cleanup_disks Also wipe disks so they can be
                         * repurposed afterwards.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result delete(Boolean cleanup_config, Boolean cleanup_disks) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("cleanup-config", cleanup_config);
                            parameters.put("cleanup-disks", cleanup_disks);
                            return _client.delete("/nodes/" + _node + "/disks/zfs/" + _name + "", parameters);
                        }

                        /**
                         * Destroy a ZFS pool.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result delete() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/disks/zfs/" + _name + "", null);
                        }

                        /**
                         * Get details about a zpool.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result detail() throws JSONException {
                            return _client.get("/nodes/" + _node + "/disks/zfs/" + _name + "", null);
                        }

                    }

                    /**
                     * List Zpools.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/disks/zfs", null);
                    }

                    /**
                     * Create a ZFS pool.
                     *
                     * @param devices The block devices you want to create the
                     * zpool on.
                     * @param name The storage identifier.
                     * @param raidlevel The RAID level to use. Enum:
                     * single,mirror,raid10,raidz,raidz2,raidz3
                     * @param add_storage Configure storage using the zpool.
                     * @param ashift Pool sector size exponent.
                     * @param compression The compression algorithm to use.
                     * Enum: on,off,gzip,lz4,lzjb,zle,zstd
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String devices, String name, String raidlevel, Boolean add_storage, Integer ashift, String compression) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("devices", devices);
                        parameters.put("name", name);
                        parameters.put("raidlevel", raidlevel);
                        parameters.put("add_storage", add_storage);
                        parameters.put("ashift", ashift);
                        parameters.put("compression", compression);
                        return _client.create("/nodes/" + _node + "/disks/zfs", parameters);
                    }

                    /**
                     * Create a ZFS pool.
                     *
                     * @param devices The block devices you want to create the
                     * zpool on.
                     * @param name The storage identifier.
                     * @param raidlevel The RAID level to use. Enum:
                     * single,mirror,raid10,raidz,raidz2,raidz3
                     * @return Result
                     * @throws JSONException
                     */

                    public Result create(String devices, String name, String raidlevel) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("devices", devices);
                        parameters.put("name", name);
                        parameters.put("raidlevel", raidlevel);
                        return _client.create("/nodes/" + _node + "/disks/zfs", parameters);
                    }

                }

                public class PVEList {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEList(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * List local disks.
                     *
                     * @param include_partitions Also include partitions.
                     * @param skipsmart Skip smart checks.
                     * @param type Only list specific types of disks. Enum:
                     * unused,journal_disks
                     * @return Result
                     * @throws JSONException
                     */
                    public Result list(Boolean include_partitions, Boolean skipsmart, String type) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("include-partitions", include_partitions);
                        parameters.put("skipsmart", skipsmart);
                        parameters.put("type", type);
                        return _client.get("/nodes/" + _node + "/disks/list", parameters);
                    }

                    /**
                     * List local disks.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result list() throws JSONException {
                        return _client.get("/nodes/" + _node + "/disks/list", null);
                    }

                }

                public class PVESmart {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVESmart(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get SMART Health of a disk.
                     *
                     * @param disk Block device name
                     * @param healthonly If true returns only the health status
                     * @return Result
                     * @throws JSONException
                     */
                    public Result smart(String disk, Boolean healthonly) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("disk", disk);
                        parameters.put("healthonly", healthonly);
                        return _client.get("/nodes/" + _node + "/disks/smart", parameters);
                    }

                    /**
                     * Get SMART Health of a disk.
                     *
                     * @param disk Block device name
                     * @return Result
                     * @throws JSONException
                     */

                    public Result smart(String disk) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("disk", disk);
                        return _client.get("/nodes/" + _node + "/disks/smart", parameters);
                    }

                }

                public class PVEInitgpt {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEInitgpt(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Initialize Disk with GPT
                     *
                     * @param disk Block device name
                     * @param uuid UUID for the GPT table
                     * @return Result
                     * @throws JSONException
                     */
                    public Result initgpt(String disk, String uuid) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("disk", disk);
                        parameters.put("uuid", uuid);
                        return _client.create("/nodes/" + _node + "/disks/initgpt", parameters);
                    }

                    /**
                     * Initialize Disk with GPT
                     *
                     * @param disk Block device name
                     * @return Result
                     * @throws JSONException
                     */

                    public Result initgpt(String disk) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("disk", disk);
                        return _client.create("/nodes/" + _node + "/disks/initgpt", parameters);
                    }

                }

                public class PVEWipedisk {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEWipedisk(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Wipe a disk or partition.
                     *
                     * @param disk Block device name
                     * @return Result
                     * @throws JSONException
                     */
                    public Result wipeDisk(String disk) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("disk", disk);
                        return _client.set("/nodes/" + _node + "/disks/wipedisk", parameters);
                    }

                }

                /**
                 * Node index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/disks", null);
                }

            }

            public class PVEApt {

                private final PveClient _client;
                private final Object _node;

                protected PVEApt(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEUpdate _update;

                public PVEUpdate getUpdate() {
                    return _update == null ? (_update = new PVEUpdate(_client, _node)) : _update;
                }
                private PVEChangelog _changelog;

                public PVEChangelog getChangelog() {
                    return _changelog == null ? (_changelog = new PVEChangelog(_client, _node)) : _changelog;
                }
                private PVERepositories _repositories;

                public PVERepositories getRepositories() {
                    return _repositories == null ? (_repositories = new PVERepositories(_client, _node)) : _repositories;
                }
                private PVEVersions _versions;

                public PVEVersions getVersions() {
                    return _versions == null ? (_versions = new PVEVersions(_client, _node)) : _versions;
                }

                public class PVEUpdate {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEUpdate(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * List available updates.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result listUpdates() throws JSONException {
                        return _client.get("/nodes/" + _node + "/apt/update", null);
                    }

                    /**
                     * This is used to resynchronize the package index files
                     * from their sources (apt-get update).
                     *
                     * @param notify Send notification mail about new packages
                     * (to email address specified for user 'root@pam').
                     * @param quiet Only produces output suitable for logging,
                     * omitting progress indicators.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateDatabase(Boolean notify, Boolean quiet) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("notify", notify);
                        parameters.put("quiet", quiet);
                        return _client.create("/nodes/" + _node + "/apt/update", parameters);
                    }

                    /**
                     * This is used to resynchronize the package index files
                     * from their sources (apt-get update).
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateDatabase() throws JSONException {
                        return _client.create("/nodes/" + _node + "/apt/update", null);
                    }

                }

                public class PVEChangelog {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEChangelog(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get package changelogs.
                     *
                     * @param name Package name.
                     * @param version Package version.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result changelog(String name, String version) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("name", name);
                        parameters.put("version", version);
                        return _client.get("/nodes/" + _node + "/apt/changelog", parameters);
                    }

                    /**
                     * Get package changelogs.
                     *
                     * @param name Package name.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result changelog(String name) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("name", name);
                        return _client.get("/nodes/" + _node + "/apt/changelog", parameters);
                    }

                }

                public class PVERepositories {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVERepositories(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get APT repository information.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result repositories() throws JSONException {
                        return _client.get("/nodes/" + _node + "/apt/repositories", null);
                    }

                    /**
                     * Change the properties of a repository. Currently only
                     * allows enabling/disabling.
                     *
                     * @param index Index within the file (starting from 0).
                     * @param path Path to the containing file.
                     * @param digest Digest to detect modifications.
                     * @param enabled Whether the repository should be enabled
                     * or not.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result changeRepository(int index, String path, String digest, Boolean enabled) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("index", index);
                        parameters.put("path", path);
                        parameters.put("digest", digest);
                        parameters.put("enabled", enabled);
                        return _client.create("/nodes/" + _node + "/apt/repositories", parameters);
                    }

                    /**
                     * Change the properties of a repository. Currently only
                     * allows enabling/disabling.
                     *
                     * @param index Index within the file (starting from 0).
                     * @param path Path to the containing file.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result changeRepository(int index, String path) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("index", index);
                        parameters.put("path", path);
                        return _client.create("/nodes/" + _node + "/apt/repositories", parameters);
                    }

                    /**
                     * Add a standard repository to the configuration
                     *
                     * @param handle Handle that identifies a repository.
                     * @param digest Digest to detect modifications.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result addRepository(String handle, String digest) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("handle", handle);
                        parameters.put("digest", digest);
                        return _client.set("/nodes/" + _node + "/apt/repositories", parameters);
                    }

                    /**
                     * Add a standard repository to the configuration
                     *
                     * @param handle Handle that identifies a repository.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result addRepository(String handle) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("handle", handle);
                        return _client.set("/nodes/" + _node + "/apt/repositories", parameters);
                    }

                }

                public class PVEVersions {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEVersions(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get package information for important Proxmox packages.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result versions() throws JSONException {
                        return _client.get("/nodes/" + _node + "/apt/versions", null);
                    }

                }

                /**
                 * Directory index for apt (Advanced Package Tool).
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/apt", null);
                }

            }

            public class PVEFirewall {

                private final PveClient _client;
                private final Object _node;

                protected PVEFirewall(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVERules _rules;

                public PVERules getRules() {
                    return _rules == null ? (_rules = new PVERules(_client, _node)) : _rules;
                }
                private PVEOptions _options;

                public PVEOptions getOptions() {
                    return _options == null ? (_options = new PVEOptions(_client, _node)) : _options;
                }
                private PVELog _log;

                public PVELog getLog() {
                    return _log == null ? (_log = new PVELog(_client, _node)) : _log;
                }

                public class PVERules {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVERules(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVEPosItem get(Object pos) {
                        return new PVEPosItem(_client, _node, pos);
                    }

                    public class PVEPosItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _pos;

                        protected PVEPosItem(PveClient client, Object node, Object pos) {
                            _client = client;
                            _node = node;
                            _pos = pos;
                        }

                        /**
                         * Delete rule.
                         *
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @return Result
                         * @throws JSONException
                         */
                        public Result deleteRule(String digest) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("digest", digest);
                            return _client.delete("/nodes/" + _node + "/firewall/rules/" + _pos + "", parameters);
                        }

                        /**
                         * Delete rule.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result deleteRule() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/firewall/rules/" + _pos + "", null);
                        }

                        /**
                         * Get single rule data.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result getRule() throws JSONException {
                            return _client.get("/nodes/" + _node + "/firewall/rules/" + _pos + "", null);
                        }

                        /**
                         * Modify rule data.
                         *
                         * @param action Rule action ('ACCEPT', 'DROP',
                         * 'REJECT') or security group name.
                         * @param comment Descriptive comment.
                         * @param delete A list of settings you want to delete.
                         * @param dest Restrict packet destination address. This
                         * can refer to a single IP address, an IP set
                         * ('+ipsetname') or an IP alias definition. You can
                         * also specify an address range like
                         * '20.34.101.207-201.3.9.99', or a list of IP addresses
                         * and networks (entries are separated by comma). Please
                         * do not mix IPv4 and IPv6 addresses inside such lists.
                         * @param digest Prevent changes if current
                         * configuration file has different SHA1 digest. This
                         * can be used to prevent concurrent modifications.
                         * @param dport Restrict TCP/UDP destination port. You
                         * can use service names or simple numbers (0-65535), as
                         * defined in '/etc/services'. Port ranges can be
                         * specified with '\d+:\d+', for example '80:85', and
                         * you can use comma separated list to match several
                         * ports or ranges.
                         * @param enable Flag to enable/disable a rule.
                         * @param icmp_type Specify icmp-type. Only valid if
                         * proto equals 'icmp'.
                         * @param iface Network interface name. You have to use
                         * network configuration key names for VMs and
                         * containers ('net\d+'). Host related rules can use
                         * arbitrary strings.
                         * @param log Log level for firewall rule. Enum:
                         * emerg,alert,crit,err,warning,notice,info,debug,nolog
                         * @param macro Use predefined standard macro.
                         * @param moveto Move rule to new position
                         * &amp;lt;moveto&amp;gt;. Other arguments are ignored.
                         * @param proto IP protocol. You can use protocol names
                         * ('tcp'/'udp') or simple numbers, as defined in
                         * '/etc/protocols'.
                         * @param source Restrict packet source address. This
                         * can refer to a single IP address, an IP set
                         * ('+ipsetname') or an IP alias definition. You can
                         * also specify an address range like
                         * '20.34.101.207-201.3.9.99', or a list of IP addresses
                         * and networks (entries are separated by comma). Please
                         * do not mix IPv4 and IPv6 addresses inside such lists.
                         * @param sport Restrict TCP/UDP source port. You can
                         * use service names or simple numbers (0-65535), as
                         * defined in '/etc/services'. Port ranges can be
                         * specified with '\d+:\d+', for example '80:85', and
                         * you can use comma separated list to match several
                         * ports or ranges.
                         * @param type Rule type. Enum: in,out,group
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateRule(String action, String comment, String delete, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer moveto, String proto, String source, String sport, String type) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("action", action);
                            parameters.put("comment", comment);
                            parameters.put("delete", delete);
                            parameters.put("dest", dest);
                            parameters.put("digest", digest);
                            parameters.put("dport", dport);
                            parameters.put("enable", enable);
                            parameters.put("icmp-type", icmp_type);
                            parameters.put("iface", iface);
                            parameters.put("log", log);
                            parameters.put("macro", macro);
                            parameters.put("moveto", moveto);
                            parameters.put("proto", proto);
                            parameters.put("source", source);
                            parameters.put("sport", sport);
                            parameters.put("type", type);
                            return _client.set("/nodes/" + _node + "/firewall/rules/" + _pos + "", parameters);
                        }

                        /**
                         * Modify rule data.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateRule() throws JSONException {
                            return _client.set("/nodes/" + _node + "/firewall/rules/" + _pos + "", null);
                        }

                    }

                    /**
                     * List rules.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result getRules() throws JSONException {
                        return _client.get("/nodes/" + _node + "/firewall/rules", null);
                    }

                    /**
                     * Create new rule.
                     *
                     * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                     * security group name.
                     * @param type Rule type. Enum: in,out,group
                     * @param comment Descriptive comment.
                     * @param dest Restrict packet destination address. This can
                     * refer to a single IP address, an IP set ('+ipsetname') or
                     * an IP alias definition. You can also specify an address
                     * range like '20.34.101.207-201.3.9.99', or a list of IP
                     * addresses and networks (entries are separated by comma).
                     * Please do not mix IPv4 and IPv6 addresses inside such
                     * lists.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param dport Restrict TCP/UDP destination port. You can
                     * use service names or simple numbers (0-65535), as defined
                     * in '/etc/services'. Port ranges can be specified with
                     * '\d+:\d+', for example '80:85', and you can use comma
                     * separated list to match several ports or ranges.
                     * @param enable Flag to enable/disable a rule.
                     * @param icmp_type Specify icmp-type. Only valid if proto
                     * equals 'icmp'.
                     * @param iface Network interface name. You have to use
                     * network configuration key names for VMs and containers
                     * ('net\d+'). Host related rules can use arbitrary strings.
                     * @param log Log level for firewall rule. Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param macro Use predefined standard macro.
                     * @param pos Update rule at position &amp;lt;pos&amp;gt;.
                     * @param proto IP protocol. You can use protocol names
                     * ('tcp'/'udp') or simple numbers, as defined in
                     * '/etc/protocols'.
                     * @param source Restrict packet source address. This can
                     * refer to a single IP address, an IP set ('+ipsetname') or
                     * an IP alias definition. You can also specify an address
                     * range like '20.34.101.207-201.3.9.99', or a list of IP
                     * addresses and networks (entries are separated by comma).
                     * Please do not mix IPv4 and IPv6 addresses inside such
                     * lists.
                     * @param sport Restrict TCP/UDP source port. You can use
                     * service names or simple numbers (0-65535), as defined in
                     * '/etc/services'. Port ranges can be specified with
                     * '\d+:\d+', for example '80:85', and you can use comma
                     * separated list to match several ports or ranges.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createRule(String action, String type, String comment, String dest, String digest, String dport, Integer enable, String icmp_type, String iface, String log, String macro, Integer pos, String proto, String source, String sport) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("action", action);
                        parameters.put("type", type);
                        parameters.put("comment", comment);
                        parameters.put("dest", dest);
                        parameters.put("digest", digest);
                        parameters.put("dport", dport);
                        parameters.put("enable", enable);
                        parameters.put("icmp-type", icmp_type);
                        parameters.put("iface", iface);
                        parameters.put("log", log);
                        parameters.put("macro", macro);
                        parameters.put("pos", pos);
                        parameters.put("proto", proto);
                        parameters.put("source", source);
                        parameters.put("sport", sport);
                        return _client.create("/nodes/" + _node + "/firewall/rules", parameters);
                    }

                    /**
                     * Create new rule.
                     *
                     * @param action Rule action ('ACCEPT', 'DROP', 'REJECT') or
                     * security group name.
                     * @param type Rule type. Enum: in,out,group
                     * @return Result
                     * @throws JSONException
                     */

                    public Result createRule(String action, String type) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("action", action);
                        parameters.put("type", type);
                        return _client.create("/nodes/" + _node + "/firewall/rules", parameters);
                    }

                }

                public class PVEOptions {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEOptions(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get host firewall options.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result getOptions() throws JSONException {
                        return _client.get("/nodes/" + _node + "/firewall/options", null);
                    }

                    /**
                     * Set Firewall options.
                     *
                     * @param delete A list of settings you want to delete.
                     * @param digest Prevent changes if current configuration
                     * file has different SHA1 digest. This can be used to
                     * prevent concurrent modifications.
                     * @param enable Enable host firewall rules.
                     * @param log_level_in Log level for incoming traffic. Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param log_level_out Log level for outgoing traffic.
                     * Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param log_nf_conntrack Enable logging of conntrack
                     * information.
                     * @param ndp Enable NDP (Neighbor Discovery Protocol).
                     * @param nf_conntrack_allow_invalid Allow invalid packets
                     * on connection tracking.
                     * @param nf_conntrack_max Maximum number of tracked
                     * connections.
                     * @param nf_conntrack_tcp_timeout_established Conntrack
                     * established timeout.
                     * @param nf_conntrack_tcp_timeout_syn_recv Conntrack syn
                     * recv timeout.
                     * @param nosmurfs Enable SMURFS filter.
                     * @param protection_synflood Enable synflood protection
                     * @param protection_synflood_burst Synflood protection rate
                     * burst by ip src.
                     * @param protection_synflood_rate Synflood protection rate
                     * syn/sec by ip src.
                     * @param smurf_log_level Log level for SMURFS filter. Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param tcp_flags_log_level Log level for illegal tcp
                     * flags filter. Enum:
                     * emerg,alert,crit,err,warning,notice,info,debug,nolog
                     * @param tcpflags Filter illegal combinations of TCP flags.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result setOptions(String delete, String digest, Boolean enable, String log_level_in, String log_level_out, Boolean log_nf_conntrack, Boolean ndp, Boolean nf_conntrack_allow_invalid, Integer nf_conntrack_max, Integer nf_conntrack_tcp_timeout_established, Integer nf_conntrack_tcp_timeout_syn_recv, Boolean nosmurfs, Boolean protection_synflood, Integer protection_synflood_burst, Integer protection_synflood_rate, String smurf_log_level, String tcp_flags_log_level, Boolean tcpflags) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("delete", delete);
                        parameters.put("digest", digest);
                        parameters.put("enable", enable);
                        parameters.put("log_level_in", log_level_in);
                        parameters.put("log_level_out", log_level_out);
                        parameters.put("log_nf_conntrack", log_nf_conntrack);
                        parameters.put("ndp", ndp);
                        parameters.put("nf_conntrack_allow_invalid", nf_conntrack_allow_invalid);
                        parameters.put("nf_conntrack_max", nf_conntrack_max);
                        parameters.put("nf_conntrack_tcp_timeout_established", nf_conntrack_tcp_timeout_established);
                        parameters.put("nf_conntrack_tcp_timeout_syn_recv", nf_conntrack_tcp_timeout_syn_recv);
                        parameters.put("nosmurfs", nosmurfs);
                        parameters.put("protection_synflood", protection_synflood);
                        parameters.put("protection_synflood_burst", protection_synflood_burst);
                        parameters.put("protection_synflood_rate", protection_synflood_rate);
                        parameters.put("smurf_log_level", smurf_log_level);
                        parameters.put("tcp_flags_log_level", tcp_flags_log_level);
                        parameters.put("tcpflags", tcpflags);
                        return _client.set("/nodes/" + _node + "/firewall/options", parameters);
                    }

                    /**
                     * Set Firewall options.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result setOptions() throws JSONException {
                        return _client.set("/nodes/" + _node + "/firewall/options", null);
                    }

                }

                public class PVELog {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVELog(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Read firewall log
                     *
                     * @param limit
                     * @param start
                     * @return Result
                     * @throws JSONException
                     */
                    public Result log(Integer limit, Integer start) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("limit", limit);
                        parameters.put("start", start);
                        return _client.get("/nodes/" + _node + "/firewall/log", parameters);
                    }

                    /**
                     * Read firewall log
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result log() throws JSONException {
                        return _client.get("/nodes/" + _node + "/firewall/log", null);
                    }

                }

                /**
                 * Directory index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/firewall", null);
                }

            }

            public class PVEReplication {

                private final PveClient _client;
                private final Object _node;

                protected PVEReplication(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                public PVEIdItem get(Object id) {
                    return new PVEIdItem(_client, _node, id);
                }

                public class PVEIdItem {

                    private final PveClient _client;
                    private final Object _node;
                    private final Object _id;

                    protected PVEIdItem(PveClient client, Object node, Object id) {
                        _client = client;
                        _node = node;
                        _id = id;
                    }

                    private PVEStatus _status;

                    public PVEStatus getStatus() {
                        return _status == null ? (_status = new PVEStatus(_client, _node, _id)) : _status;
                    }
                    private PVELog _log;

                    public PVELog getLog() {
                        return _log == null ? (_log = new PVELog(_client, _node, _id)) : _log;
                    }
                    private PVEScheduleNow _scheduleNow;

                    public PVEScheduleNow getScheduleNow() {
                        return _scheduleNow == null ? (_scheduleNow = new PVEScheduleNow(_client, _node, _id)) : _scheduleNow;
                    }

                    public class PVEStatus {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _id;

                        protected PVEStatus(PveClient client, Object node, Object id) {
                            _client = client;
                            _node = node;
                            _id = id;
                        }

                        /**
                         * Get replication job status.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result jobStatus() throws JSONException {
                            return _client.get("/nodes/" + _node + "/replication/" + _id + "/status", null);
                        }

                    }

                    public class PVELog {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _id;

                        protected PVELog(PveClient client, Object node, Object id) {
                            _client = client;
                            _node = node;
                            _id = id;
                        }

                        /**
                         * Read replication job log.
                         *
                         * @param limit
                         * @param start
                         * @return Result
                         * @throws JSONException
                         */
                        public Result readJobLog(Integer limit, Integer start) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("limit", limit);
                            parameters.put("start", start);
                            return _client.get("/nodes/" + _node + "/replication/" + _id + "/log", parameters);
                        }

                        /**
                         * Read replication job log.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result readJobLog() throws JSONException {
                            return _client.get("/nodes/" + _node + "/replication/" + _id + "/log", null);
                        }

                    }

                    public class PVEScheduleNow {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _id;

                        protected PVEScheduleNow(PveClient client, Object node, Object id) {
                            _client = client;
                            _node = node;
                            _id = id;
                        }

                        /**
                         * Schedule replication job to start as soon as
                         * possible.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result scheduleNow() throws JSONException {
                            return _client.create("/nodes/" + _node + "/replication/" + _id + "/schedule_now", null);
                        }

                    }

                    /**
                     * Directory index.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/replication/" + _id + "", null);
                    }

                }

                /**
                 * List status of all replication jobs on this node.
                 *
                 * @param guest Only list replication jobs for this guest.
                 * @return Result
                 * @throws JSONException
                 */
                public Result status(Integer guest) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("guest", guest);
                    return _client.get("/nodes/" + _node + "/replication", parameters);
                }

                /**
                 * List status of all replication jobs on this node.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result status() throws JSONException {
                    return _client.get("/nodes/" + _node + "/replication", null);
                }

            }

            public class PVECertificates {

                private final PveClient _client;
                private final Object _node;

                protected PVECertificates(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEAcme _acme;

                public PVEAcme getAcme() {
                    return _acme == null ? (_acme = new PVEAcme(_client, _node)) : _acme;
                }
                private PVEInfo _info;

                public PVEInfo getInfo() {
                    return _info == null ? (_info = new PVEInfo(_client, _node)) : _info;
                }
                private PVECustom _custom;

                public PVECustom getCustom() {
                    return _custom == null ? (_custom = new PVECustom(_client, _node)) : _custom;
                }

                public class PVEAcme {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEAcme(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    private PVECertificate _certificate;

                    public PVECertificate getCertificate() {
                        return _certificate == null ? (_certificate = new PVECertificate(_client, _node)) : _certificate;
                    }

                    public class PVECertificate {

                        private final PveClient _client;
                        private final Object _node;

                        protected PVECertificate(PveClient client, Object node) {
                            _client = client;
                            _node = node;
                        }

                        /**
                         * Revoke existing certificate from CA.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result revokeCertificate() throws JSONException {
                            return _client.delete("/nodes/" + _node + "/certificates/acme/certificate", null);
                        }

                        /**
                         * Order a new certificate from ACME-compatible CA.
                         *
                         * @param force Overwrite existing custom certificate.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result newCertificate(Boolean force) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("force", force);
                            return _client.create("/nodes/" + _node + "/certificates/acme/certificate", parameters);
                        }

                        /**
                         * Order a new certificate from ACME-compatible CA.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result newCertificate() throws JSONException {
                            return _client.create("/nodes/" + _node + "/certificates/acme/certificate", null);
                        }

                        /**
                         * Renew existing certificate from CA.
                         *
                         * @param force Force renewal even if expiry is more
                         * than 30 days away.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result renewCertificate(Boolean force) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("force", force);
                            return _client.set("/nodes/" + _node + "/certificates/acme/certificate", parameters);
                        }

                        /**
                         * Renew existing certificate from CA.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result renewCertificate() throws JSONException {
                            return _client.set("/nodes/" + _node + "/certificates/acme/certificate", null);
                        }

                    }

                    /**
                     * ACME index.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/certificates/acme", null);
                    }

                }

                public class PVEInfo {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEInfo(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * Get information about node's certificates.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result info() throws JSONException {
                        return _client.get("/nodes/" + _node + "/certificates/info", null);
                    }

                }

                public class PVECustom {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVECustom(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    /**
                     * DELETE custom certificate chain and key.
                     *
                     * @param restart Restart pveproxy.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result removeCustomCert(Boolean restart) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("restart", restart);
                        return _client.delete("/nodes/" + _node + "/certificates/custom", parameters);
                    }

                    /**
                     * DELETE custom certificate chain and key.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result removeCustomCert() throws JSONException {
                        return _client.delete("/nodes/" + _node + "/certificates/custom", null);
                    }

                    /**
                     * Upload or update custom certificate chain and key.
                     *
                     * @param certificates PEM encoded certificate (chain).
                     * @param force Overwrite existing custom or ACME
                     * certificate files.
                     * @param key PEM encoded private key.
                     * @param restart Restart pveproxy.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result uploadCustomCert(String certificates, Boolean force, String key, Boolean restart) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("certificates", certificates);
                        parameters.put("force", force);
                        parameters.put("key", key);
                        parameters.put("restart", restart);
                        return _client.create("/nodes/" + _node + "/certificates/custom", parameters);
                    }

                    /**
                     * Upload or update custom certificate chain and key.
                     *
                     * @param certificates PEM encoded certificate (chain).
                     * @return Result
                     * @throws JSONException
                     */

                    public Result uploadCustomCert(String certificates) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("certificates", certificates);
                        return _client.create("/nodes/" + _node + "/certificates/custom", parameters);
                    }

                }

                /**
                 * Node index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result index() throws JSONException {
                    return _client.get("/nodes/" + _node + "/certificates", null);
                }

            }

            public class PVEConfig {

                private final PveClient _client;
                private final Object _node;

                protected PVEConfig(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Get node configuration options.
                 *
                 * @param property Return only a specific property from the node
                 * configuration. Enum:
                 * acme,acmedomain0,acmedomain1,acmedomain2,acmedomain3,acmedomain4,acmedomain5,description,startall-onboot-delay,wakeonlan
                 * @return Result
                 * @throws JSONException
                 */
                public Result getConfig(String property) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("property", property);
                    return _client.get("/nodes/" + _node + "/config", parameters);
                }

                /**
                 * Get node configuration options.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result getConfig() throws JSONException {
                    return _client.get("/nodes/" + _node + "/config", null);
                }

                /**
                 * Set node configuration options.
                 *
                 * @param acme Node specific ACME settings.
                 * @param acmedomainN ACME domain and validation plugin
                 * @param delete A list of settings you want to delete.
                 * @param description Description for the Node. Shown in the
                 * web-interface node notes panel. This is saved as comment
                 * inside the configuration file.
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param startall_onboot_delay Initial delay in seconds, before
                 * starting all the Virtual Guests with on-boot enabled.
                 * @param wakeonlan MAC address for wake on LAN
                 * @return Result
                 * @throws JSONException
                 */

                public Result setOptions(String acme, Map<Integer, String> acmedomainN, String delete, String description, String digest, Integer startall_onboot_delay, String wakeonlan) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("acme", acme);
                    parameters.put("delete", delete);
                    parameters.put("description", description);
                    parameters.put("digest", digest);
                    parameters.put("startall-onboot-delay", startall_onboot_delay);
                    parameters.put("wakeonlan", wakeonlan);
                    addIndexedParameter(parameters, "acmedomain", acmedomainN);
                    return _client.set("/nodes/" + _node + "/config", parameters);
                }

                /**
                 * Set node configuration options.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result setOptions() throws JSONException {
                    return _client.set("/nodes/" + _node + "/config", null);
                }

            }

            public class PVESdn {

                private final PveClient _client;
                private final Object _node;

                protected PVESdn(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                private PVEZones _zones;

                public PVEZones getZones() {
                    return _zones == null ? (_zones = new PVEZones(_client, _node)) : _zones;
                }

                public class PVEZones {

                    private final PveClient _client;
                    private final Object _node;

                    protected PVEZones(PveClient client, Object node) {
                        _client = client;
                        _node = node;
                    }

                    public PVEZoneItem get(Object zone) {
                        return new PVEZoneItem(_client, _node, zone);
                    }

                    public class PVEZoneItem {

                        private final PveClient _client;
                        private final Object _node;
                        private final Object _zone;

                        protected PVEZoneItem(PveClient client, Object node, Object zone) {
                            _client = client;
                            _node = node;
                            _zone = zone;
                        }

                        private PVEContent _content;

                        public PVEContent getContent() {
                            return _content == null ? (_content = new PVEContent(_client, _node, _zone)) : _content;
                        }

                        public class PVEContent {

                            private final PveClient _client;
                            private final Object _node;
                            private final Object _zone;

                            protected PVEContent(PveClient client, Object node, Object zone) {
                                _client = client;
                                _node = node;
                                _zone = zone;
                            }

                            /**
                             * List zone content.
                             *
                             * @return Result
                             * @throws JSONException
                             */
                            public Result index() throws JSONException {
                                return _client.get("/nodes/" + _node + "/sdn/zones/" + _zone + "/content", null);
                            }

                        }

                        /**
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result diridx() throws JSONException {
                            return _client.get("/nodes/" + _node + "/sdn/zones/" + _zone + "", null);
                        }

                    }

                    /**
                     * Get status for all zones.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result index() throws JSONException {
                        return _client.get("/nodes/" + _node + "/sdn/zones", null);
                    }

                }

                /**
                 * SDN index.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result sdnindex() throws JSONException {
                    return _client.get("/nodes/" + _node + "/sdn", null);
                }

            }

            public class PVEVersion {

                private final PveClient _client;
                private final Object _node;

                protected PVEVersion(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * API version details
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result version() throws JSONException {
                    return _client.get("/nodes/" + _node + "/version", null);
                }

            }

            public class PVEStatus {

                private final PveClient _client;
                private final Object _node;

                protected PVEStatus(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read node status
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result status() throws JSONException {
                    return _client.get("/nodes/" + _node + "/status", null);
                }

                /**
                 * Reboot or shutdown a node.
                 *
                 * @param command Specify the command. Enum: reboot,shutdown
                 * @return Result
                 * @throws JSONException
                 */

                public Result nodeCmd(String command) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("command", command);
                    return _client.create("/nodes/" + _node + "/status", parameters);
                }

            }

            public class PVENetstat {

                private final PveClient _client;
                private final Object _node;

                protected PVENetstat(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read tap/vm network device interface counters
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result netstat() throws JSONException {
                    return _client.get("/nodes/" + _node + "/netstat", null);
                }

            }

            public class PVEExecute {

                private final PveClient _client;
                private final Object _node;

                protected PVEExecute(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Execute multiple commands in order.
                 *
                 * @param commands JSON encoded array of commands.
                 * @return Result
                 * @throws JSONException
                 */
                public Result execute(String commands) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("commands", commands);
                    return _client.create("/nodes/" + _node + "/execute", parameters);
                }

            }

            public class PVEWakeonlan {

                private final PveClient _client;
                private final Object _node;

                protected PVEWakeonlan(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Try to wake a node via 'wake on LAN' network packet.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result wakeonlan() throws JSONException {
                    return _client.create("/nodes/" + _node + "/wakeonlan", null);
                }

            }

            public class PVERrd {

                private final PveClient _client;
                private final Object _node;

                protected PVERrd(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read node RRD statistics (returns PNG)
                 *
                 * @param ds The list of datasources you want to display.
                 * @param timeframe Specify the time frame you are interested
                 * in. Enum: hour,day,week,month,year
                 * @param cf The RRD consolidation function Enum: AVERAGE,MAX
                 * @return Result
                 * @throws JSONException
                 */
                public Result rrd(String ds, String timeframe, String cf) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ds", ds);
                    parameters.put("timeframe", timeframe);
                    parameters.put("cf", cf);
                    return _client.get("/nodes/" + _node + "/rrd", parameters);
                }

                /**
                 * Read node RRD statistics (returns PNG)
                 *
                 * @param ds The list of datasources you want to display.
                 * @param timeframe Specify the time frame you are interested
                 * in. Enum: hour,day,week,month,year
                 * @return Result
                 * @throws JSONException
                 */

                public Result rrd(String ds, String timeframe) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ds", ds);
                    parameters.put("timeframe", timeframe);
                    return _client.get("/nodes/" + _node + "/rrd", parameters);
                }

            }

            public class PVERrddata {

                private final PveClient _client;
                private final Object _node;

                protected PVERrddata(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read node RRD statistics
                 *
                 * @param timeframe Specify the time frame you are interested
                 * in. Enum: hour,day,week,month,year
                 * @param cf The RRD consolidation function Enum: AVERAGE,MAX
                 * @return Result
                 * @throws JSONException
                 */
                public Result rrddata(String timeframe, String cf) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("timeframe", timeframe);
                    parameters.put("cf", cf);
                    return _client.get("/nodes/" + _node + "/rrddata", parameters);
                }

                /**
                 * Read node RRD statistics
                 *
                 * @param timeframe Specify the time frame you are interested
                 * in. Enum: hour,day,week,month,year
                 * @return Result
                 * @throws JSONException
                 */

                public Result rrddata(String timeframe) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("timeframe", timeframe);
                    return _client.get("/nodes/" + _node + "/rrddata", parameters);
                }

            }

            public class PVESyslog {

                private final PveClient _client;
                private final Object _node;

                protected PVESyslog(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read system log
                 *
                 * @param limit
                 * @param service Service ID
                 * @param since Display all log since this date-time string.
                 * @param start
                 * @param until Display all log until this date-time string.
                 * @return Result
                 * @throws JSONException
                 */
                public Result syslog(Integer limit, String service, String since, Integer start, String until) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("limit", limit);
                    parameters.put("service", service);
                    parameters.put("since", since);
                    parameters.put("start", start);
                    parameters.put("until", until);
                    return _client.get("/nodes/" + _node + "/syslog", parameters);
                }

                /**
                 * Read system log
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result syslog() throws JSONException {
                    return _client.get("/nodes/" + _node + "/syslog", null);
                }

            }

            public class PVEJournal {

                private final PveClient _client;
                private final Object _node;

                protected PVEJournal(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read Journal
                 *
                 * @param endcursor End before the given Cursor. Conflicts with
                 * 'until'
                 * @param lastentries Limit to the last X lines. Conflicts with
                 * a range.
                 * @param since Display all log since this UNIX epoch. Conflicts
                 * with 'startcursor'.
                 * @param startcursor Start after the given Cursor. Conflicts
                 * with 'since'
                 * @param until Display all log until this UNIX epoch. Conflicts
                 * with 'endcursor'.
                 * @return Result
                 * @throws JSONException
                 */
                public Result journal(String endcursor, Integer lastentries, Integer since, String startcursor, Integer until) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("endcursor", endcursor);
                    parameters.put("lastentries", lastentries);
                    parameters.put("since", since);
                    parameters.put("startcursor", startcursor);
                    parameters.put("until", until);
                    return _client.get("/nodes/" + _node + "/journal", parameters);
                }

                /**
                 * Read Journal
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result journal() throws JSONException {
                    return _client.get("/nodes/" + _node + "/journal", null);
                }

            }

            public class PVEVncshell {

                private final PveClient _client;
                private final Object _node;

                protected PVEVncshell(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Creates a VNC Shell proxy.
                 *
                 * @param cmd Run specific command or default to login. Enum:
                 * ceph_install,upgrade,login
                 * @param cmd_opts Add parameters to a command. Encoded as null
                 * terminated strings.
                 * @param height sets the height of the console in pixels.
                 * @param websocket use websocket instead of standard vnc.
                 * @param width sets the width of the console in pixels.
                 * @return Result
                 * @throws JSONException
                 */
                public Result vncshell(String cmd, String cmd_opts, Integer height, Boolean websocket, Integer width) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("cmd", cmd);
                    parameters.put("cmd-opts", cmd_opts);
                    parameters.put("height", height);
                    parameters.put("websocket", websocket);
                    parameters.put("width", width);
                    return _client.create("/nodes/" + _node + "/vncshell", parameters);
                }

                /**
                 * Creates a VNC Shell proxy.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result vncshell() throws JSONException {
                    return _client.create("/nodes/" + _node + "/vncshell", null);
                }

            }

            public class PVETermproxy {

                private final PveClient _client;
                private final Object _node;

                protected PVETermproxy(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Creates a VNC Shell proxy.
                 *
                 * @param cmd Run specific command or default to login. Enum:
                 * ceph_install,upgrade,login
                 * @param cmd_opts Add parameters to a command. Encoded as null
                 * terminated strings.
                 * @return Result
                 * @throws JSONException
                 */
                public Result termproxy(String cmd, String cmd_opts) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("cmd", cmd);
                    parameters.put("cmd-opts", cmd_opts);
                    return _client.create("/nodes/" + _node + "/termproxy", parameters);
                }

                /**
                 * Creates a VNC Shell proxy.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result termproxy() throws JSONException {
                    return _client.create("/nodes/" + _node + "/termproxy", null);
                }

            }

            public class PVEVncwebsocket {

                private final PveClient _client;
                private final Object _node;

                protected PVEVncwebsocket(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Opens a websocket for VNC traffic.
                 *
                 * @param port Port number returned by previous vncproxy call.
                 * @param vncticket Ticket from previous call to vncproxy.
                 * @return Result
                 * @throws JSONException
                 */
                public Result vncwebsocket(int port, String vncticket) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("port", port);
                    parameters.put("vncticket", vncticket);
                    return _client.get("/nodes/" + _node + "/vncwebsocket", parameters);
                }

            }

            public class PVESpiceshell {

                private final PveClient _client;
                private final Object _node;

                protected PVESpiceshell(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Creates a SPICE shell.
                 *
                 * @param cmd Run specific command or default to login. Enum:
                 * ceph_install,upgrade,login
                 * @param cmd_opts Add parameters to a command. Encoded as null
                 * terminated strings.
                 * @param proxy SPICE proxy server. This can be used by the
                 * client to specify the proxy server. All nodes in a cluster
                 * runs 'spiceproxy', so it is up to the client to choose one.
                 * By default, we return the node where the VM is currently
                 * running. As reasonable setting is to use same node you use to
                 * connect to the API (This is window.location.hostname for the
                 * JS GUI).
                 * @return Result
                 * @throws JSONException
                 */
                public Result spiceshell(String cmd, String cmd_opts, String proxy) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("cmd", cmd);
                    parameters.put("cmd-opts", cmd_opts);
                    parameters.put("proxy", proxy);
                    return _client.create("/nodes/" + _node + "/spiceshell", parameters);
                }

                /**
                 * Creates a SPICE shell.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result spiceshell() throws JSONException {
                    return _client.create("/nodes/" + _node + "/spiceshell", null);
                }

            }

            public class PVEDns {

                private final PveClient _client;
                private final Object _node;

                protected PVEDns(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read DNS settings.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result dns() throws JSONException {
                    return _client.get("/nodes/" + _node + "/dns", null);
                }

                /**
                 * Write DNS settings.
                 *
                 * @param search Search domain for host-name lookup.
                 * @param dns1 First name server IP address.
                 * @param dns2 Second name server IP address.
                 * @param dns3 Third name server IP address.
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateDns(String search, String dns1, String dns2, String dns3) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("search", search);
                    parameters.put("dns1", dns1);
                    parameters.put("dns2", dns2);
                    parameters.put("dns3", dns3);
                    return _client.set("/nodes/" + _node + "/dns", parameters);
                }

                /**
                 * Write DNS settings.
                 *
                 * @param search Search domain for host-name lookup.
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateDns(String search) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("search", search);
                    return _client.set("/nodes/" + _node + "/dns", parameters);
                }

            }

            public class PVETime {

                private final PveClient _client;
                private final Object _node;

                protected PVETime(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Read server time and time zone settings.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result time() throws JSONException {
                    return _client.get("/nodes/" + _node + "/time", null);
                }

                /**
                 * Set time zone.
                 *
                 * @param timezone Time zone. The file
                 * '/usr/share/zoneinfo/zone.tab' contains the list of valid
                 * names.
                 * @return Result
                 * @throws JSONException
                 */

                public Result setTimezone(String timezone) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("timezone", timezone);
                    return _client.set("/nodes/" + _node + "/time", parameters);
                }

            }

            public class PVEAplinfo {

                private final PveClient _client;
                private final Object _node;

                protected PVEAplinfo(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Get list of appliances.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result aplinfo() throws JSONException {
                    return _client.get("/nodes/" + _node + "/aplinfo", null);
                }

                /**
                 * Download appliance templates.
                 *
                 * @param storage The storage where the template will be stored
                 * @param template The template which will downloaded
                 * @return Result
                 * @throws JSONException
                 */

                public Result aplDownload(String storage, String template) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("storage", storage);
                    parameters.put("template", template);
                    return _client.create("/nodes/" + _node + "/aplinfo", parameters);
                }

            }

            public class PVEQueryUrlMetadata {

                private final PveClient _client;
                private final Object _node;

                protected PVEQueryUrlMetadata(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Query metadata of an URL: file size, file name and mime type.
                 *
                 * @param url The URL to query the metadata from.
                 * @param verify_certificates If false, no SSL/TLS certificates
                 * will be verified.
                 * @return Result
                 * @throws JSONException
                 */
                public Result queryUrlMetadata(String url, Boolean verify_certificates) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("url", url);
                    parameters.put("verify-certificates", verify_certificates);
                    return _client.get("/nodes/" + _node + "/query-url-metadata", parameters);
                }

                /**
                 * Query metadata of an URL: file size, file name and mime type.
                 *
                 * @param url The URL to query the metadata from.
                 * @return Result
                 * @throws JSONException
                 */

                public Result queryUrlMetadata(String url) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("url", url);
                    return _client.get("/nodes/" + _node + "/query-url-metadata", parameters);
                }

            }

            public class PVEReport {

                private final PveClient _client;
                private final Object _node;

                protected PVEReport(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Gather various systems information about a node
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result report() throws JSONException {
                    return _client.get("/nodes/" + _node + "/report", null);
                }

            }

            public class PVEStartall {

                private final PveClient _client;
                private final Object _node;

                protected PVEStartall(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Start all VMs and containers located on this node (by default
                 * only those with onboot=1).
                 *
                 * @param force Issue start command even if virtual guest have
                 * 'onboot' not set or set to off.
                 * @param vms Only consider guests from this comma separated
                 * list of VMIDs.
                 * @return Result
                 * @throws JSONException
                 */
                public Result startall(Boolean force, String vms) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("force", force);
                    parameters.put("vms", vms);
                    return _client.create("/nodes/" + _node + "/startall", parameters);
                }

                /**
                 * Start all VMs and containers located on this node (by default
                 * only those with onboot=1).
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result startall() throws JSONException {
                    return _client.create("/nodes/" + _node + "/startall", null);
                }

            }

            public class PVEStopall {

                private final PveClient _client;
                private final Object _node;

                protected PVEStopall(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Stop all VMs and Containers.
                 *
                 * @param vms Only consider Guests with these IDs.
                 * @return Result
                 * @throws JSONException
                 */
                public Result stopall(String vms) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("vms", vms);
                    return _client.create("/nodes/" + _node + "/stopall", parameters);
                }

                /**
                 * Stop all VMs and Containers.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result stopall() throws JSONException {
                    return _client.create("/nodes/" + _node + "/stopall", null);
                }

            }

            public class PVEMigrateall {

                private final PveClient _client;
                private final Object _node;

                protected PVEMigrateall(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Migrate all VMs and Containers.
                 *
                 * @param target Target node.
                 * @param maxworkers Maximal number of parallel migration job.
                 * If not set use 'max_workers' from datacenter.cfg, one of both
                 * must be set!
                 * @param vms Only consider Guests with these IDs.
                 * @param with_local_disks Enable live storage migration for
                 * local disk
                 * @return Result
                 * @throws JSONException
                 */
                public Result migrateall(String target, Integer maxworkers, String vms, Boolean with_local_disks) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("target", target);
                    parameters.put("maxworkers", maxworkers);
                    parameters.put("vms", vms);
                    parameters.put("with-local-disks", with_local_disks);
                    return _client.create("/nodes/" + _node + "/migrateall", parameters);
                }

                /**
                 * Migrate all VMs and Containers.
                 *
                 * @param target Target node.
                 * @return Result
                 * @throws JSONException
                 */

                public Result migrateall(String target) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("target", target);
                    return _client.create("/nodes/" + _node + "/migrateall", parameters);
                }

            }

            public class PVEHosts {

                private final PveClient _client;
                private final Object _node;

                protected PVEHosts(PveClient client, Object node) {
                    _client = client;
                    _node = node;
                }

                /**
                 * Get the content of /etc/hosts.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result getEtcHosts() throws JSONException {
                    return _client.get("/nodes/" + _node + "/hosts", null);
                }

                /**
                 * Write /etc/hosts.
                 *
                 * @param data The target content of /etc/hosts.
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @return Result
                 * @throws JSONException
                 */

                public Result writeEtcHosts(String data, String digest) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("data", data);
                    parameters.put("digest", digest);
                    return _client.create("/nodes/" + _node + "/hosts", parameters);
                }

                /**
                 * Write /etc/hosts.
                 *
                 * @param data The target content of /etc/hosts.
                 * @return Result
                 * @throws JSONException
                 */

                public Result writeEtcHosts(String data) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("data", data);
                    return _client.create("/nodes/" + _node + "/hosts", parameters);
                }

            }

            /**
             * Node index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/nodes/" + _node + "", null);
            }

        }

        /**
         * Cluster node index.
         *
         * @return Result
         * @throws JSONException
         */
        public Result index() throws JSONException {
            return _client.get("/nodes", null);
        }

    }

    public class PVEStorage {

        private final PveClient _client;

        protected PVEStorage(PveClient client) {
            _client = client;

        }

        public PVEStorageItem get(Object storage) {
            return new PVEStorageItem(_client, storage);
        }

        public class PVEStorageItem {

            private final PveClient _client;
            private final Object _storage;

            protected PVEStorageItem(PveClient client, Object storage) {
                _client = client;
                _storage = storage;
            }

            /**
             * Delete storage configuration.
             *
             * @return Result
             * @throws JSONException
             */
            public Result delete() throws JSONException {
                return _client.delete("/storage/" + _storage + "", null);
            }

            /**
             * Read storage configuration.
             *
             * @return Result
             * @throws JSONException
             */

            public Result read() throws JSONException {
                return _client.get("/storage/" + _storage + "", null);
            }

            /**
             * Update storage configuration.
             *
             * @param blocksize block size
             * @param bwlimit Set bandwidth/io limits various operations.
             * @param comstar_hg host group for comstar views
             * @param comstar_tg target group for comstar views
             * @param content Allowed content types. NOTE: the value 'rootdir'
             * is used for Containers, and value 'images' for VMs.
             * @param delete A list of settings you want to delete.
             * @param digest Prevent changes if current configuration file has
             * different SHA1 digest. This can be used to prevent concurrent
             * modifications.
             * @param disable Flag to disable the storage.
             * @param domain CIFS domain.
             * @param encryption_key Encryption key. Use 'autogen' to generate
             * one automatically without passphrase.
             * @param fingerprint Certificate SHA 256 fingerprint.
             * @param format Default image format.
             * @param fs_name The Ceph filesystem name.
             * @param fuse Mount CephFS through FUSE.
             * @param is_mountpoint Assume the given path is an externally
             * managed mountpoint and consider the storage offline if it is not
             * mounted. Using a boolean (yes/no) value serves as a shortcut to
             * using the target path in this field.
             * @param keyring Client keyring contents (for external clusters).
             * @param krbd Always access rbd through krbd kernel module.
             * @param lio_tpg target portal group for Linux LIO targets
             * @param master_pubkey Base64-encoded, PEM-formatted public RSA
             * key. Used to encrypt a copy of the encryption-key which will be
             * added to each encrypted backup.
             * @param maxfiles Deprecated: use 'prune-backups' instead. Maximal
             * number of backup files per VM. Use '0' for unlimited.
             * @param mkdir Create the directory if it doesn't exist.
             * @param monhost IP addresses of monitors (for external clusters).
             * @param mountpoint mount point
             * @param namespace_ RBD Namespace.
             * @param nocow Set the NOCOW flag on files. Disables data
             * checksumming and causes data errors to be unrecoverable from
             * while allowing direct I/O. Only use this if data does not need to
             * be any more safe than on a single ext4 formatted disk with no
             * underlying raid system.
             * @param nodes List of cluster node names.
             * @param nowritecache disable write caching on the target
             * @param options NFS mount options (see 'man nfs')
             * @param password Password for accessing the share/datastore.
             * @param pool Pool.
             * @param port For non default port.
             * @param preallocation Preallocation mode for raw and qcow2 images.
             * Using 'metadata' on raw images results in preallocation=off.
             * Enum: off,metadata,falloc,full
             * @param prune_backups The retention options with shorter intervals
             * are processed first with --keep-last being the very first one.
             * Each option covers a specific period of time. We say that backups
             * within this period are covered by this option. The next option
             * does not take care of already covered backups and only considers
             * older backups.
             * @param saferemove Zero-out data when removing LVs.
             * @param saferemove_throughput Wipe throughput (cstream -t
             * parameter value).
             * @param server Server IP or DNS name.
             * @param server2 Backup volfile server IP or DNS name.
             * @param shared Mark storage as shared.
             * @param smbversion SMB protocol version. 'default' if not set,
             * negotiates the highest SMB2+ version supported by both the client
             * and server. Enum: default,2.0,2.1,3,3.0,3.11
             * @param sparse use sparse volumes
             * @param subdir Subdir to mount.
             * @param tagged_only Only use logical volumes tagged with
             * 'pve-vm-ID'.
             * @param transport Gluster transport: tcp or rdma Enum:
             * tcp,rdma,unix
             * @param username RBD Id.
             * @return Result
             * @throws JSONException
             */

            public Result update(String blocksize, String bwlimit, String comstar_hg, String comstar_tg, String content, String delete, String digest, Boolean disable, String domain, String encryption_key, String fingerprint, String format, String fs_name, Boolean fuse, String is_mountpoint, String keyring, Boolean krbd, String lio_tpg, String master_pubkey, Integer maxfiles, Boolean mkdir, String monhost, String mountpoint, String namespace_, Boolean nocow, String nodes, Boolean nowritecache, String options, String password, String pool, Integer port, String preallocation, String prune_backups, Boolean saferemove, String saferemove_throughput, String server, String server2, Boolean shared, String smbversion, Boolean sparse, String subdir, Boolean tagged_only, String transport, String username) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("blocksize", blocksize);
                parameters.put("bwlimit", bwlimit);
                parameters.put("comstar_hg", comstar_hg);
                parameters.put("comstar_tg", comstar_tg);
                parameters.put("content", content);
                parameters.put("delete", delete);
                parameters.put("digest", digest);
                parameters.put("disable", disable);
                parameters.put("domain", domain);
                parameters.put("encryption-key", encryption_key);
                parameters.put("fingerprint", fingerprint);
                parameters.put("format", format);
                parameters.put("fs-name", fs_name);
                parameters.put("fuse", fuse);
                parameters.put("is_mountpoint", is_mountpoint);
                parameters.put("keyring", keyring);
                parameters.put("krbd", krbd);
                parameters.put("lio_tpg", lio_tpg);
                parameters.put("master-pubkey", master_pubkey);
                parameters.put("maxfiles", maxfiles);
                parameters.put("mkdir", mkdir);
                parameters.put("monhost", monhost);
                parameters.put("mountpoint", mountpoint);
                parameters.put("namespace", namespace_);
                parameters.put("nocow", nocow);
                parameters.put("nodes", nodes);
                parameters.put("nowritecache", nowritecache);
                parameters.put("options", options);
                parameters.put("password", password);
                parameters.put("pool", pool);
                parameters.put("port", port);
                parameters.put("preallocation", preallocation);
                parameters.put("prune-backups", prune_backups);
                parameters.put("saferemove", saferemove);
                parameters.put("saferemove_throughput", saferemove_throughput);
                parameters.put("server", server);
                parameters.put("server2", server2);
                parameters.put("shared", shared);
                parameters.put("smbversion", smbversion);
                parameters.put("sparse", sparse);
                parameters.put("subdir", subdir);
                parameters.put("tagged_only", tagged_only);
                parameters.put("transport", transport);
                parameters.put("username", username);
                return _client.set("/storage/" + _storage + "", parameters);
            }

            /**
             * Update storage configuration.
             *
             * @return Result
             * @throws JSONException
             */

            public Result update() throws JSONException {
                return _client.set("/storage/" + _storage + "", null);
            }

        }

        /**
         * Storage index.
         *
         * @param type Only list storage of specific type Enum:
         * btrfs,cephfs,cifs,dir,glusterfs,iscsi,iscsidirect,lvm,lvmthin,nfs,pbs,rbd,zfs,zfspool
         * @return Result
         * @throws JSONException
         */
        public Result index(String type) throws JSONException {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("type", type);
            return _client.get("/storage", parameters);
        }

        /**
         * Storage index.
         *
         * @return Result
         * @throws JSONException
         */

        public Result index() throws JSONException {
            return _client.get("/storage", null);
        }

        /**
         * Create a new storage.
         *
         * @param storage The storage identifier.
         * @param type Storage type. Enum:
         * btrfs,cephfs,cifs,dir,glusterfs,iscsi,iscsidirect,lvm,lvmthin,nfs,pbs,rbd,zfs,zfspool
         * @param authsupported Authsupported.
         * @param base_ Base volume. This volume is automatically activated.
         * @param blocksize block size
         * @param bwlimit Set bandwidth/io limits various operations.
         * @param comstar_hg host group for comstar views
         * @param comstar_tg target group for comstar views
         * @param content Allowed content types. NOTE: the value 'rootdir' is
         * used for Containers, and value 'images' for VMs.
         * @param datastore Proxmox Backup Server datastore name.
         * @param disable Flag to disable the storage.
         * @param domain CIFS domain.
         * @param encryption_key Encryption key. Use 'autogen' to generate one
         * automatically without passphrase.
         * @param export NFS export path.
         * @param fingerprint Certificate SHA 256 fingerprint.
         * @param format Default image format.
         * @param fs_name The Ceph filesystem name.
         * @param fuse Mount CephFS through FUSE.
         * @param is_mountpoint Assume the given path is an externally managed
         * mountpoint and consider the storage offline if it is not mounted.
         * Using a boolean (yes/no) value serves as a shortcut to using the
         * target path in this field.
         * @param iscsiprovider iscsi provider
         * @param keyring Client keyring contents (for external clusters).
         * @param krbd Always access rbd through krbd kernel module.
         * @param lio_tpg target portal group for Linux LIO targets
         * @param master_pubkey Base64-encoded, PEM-formatted public RSA key.
         * Used to encrypt a copy of the encryption-key which will be added to
         * each encrypted backup.
         * @param maxfiles Deprecated: use 'prune-backups' instead. Maximal
         * number of backup files per VM. Use '0' for unlimited.
         * @param mkdir Create the directory if it doesn't exist.
         * @param monhost IP addresses of monitors (for external clusters).
         * @param mountpoint mount point
         * @param namespace_ RBD Namespace.
         * @param nocow Set the NOCOW flag on files. Disables data checksumming
         * and causes data errors to be unrecoverable from while allowing direct
         * I/O. Only use this if data does not need to be any more safe than on
         * a single ext4 formatted disk with no underlying raid system.
         * @param nodes List of cluster node names.
         * @param nowritecache disable write caching on the target
         * @param options NFS mount options (see 'man nfs')
         * @param password Password for accessing the share/datastore.
         * @param path File system path.
         * @param pool Pool.
         * @param port For non default port.
         * @param portal iSCSI portal (IP or DNS name with optional port).
         * @param preallocation Preallocation mode for raw and qcow2 images.
         * Using 'metadata' on raw images results in preallocation=off. Enum:
         * off,metadata,falloc,full
         * @param prune_backups The retention options with shorter intervals are
         * processed first with --keep-last being the very first one. Each
         * option covers a specific period of time. We say that backups within
         * this period are covered by this option. The next option does not take
         * care of already covered backups and only considers older backups.
         * @param saferemove Zero-out data when removing LVs.
         * @param saferemove_throughput Wipe throughput (cstream -t parameter
         * value).
         * @param server Server IP or DNS name.
         * @param server2 Backup volfile server IP or DNS name.
         * @param share CIFS share.
         * @param shared Mark storage as shared.
         * @param smbversion SMB protocol version. 'default' if not set,
         * negotiates the highest SMB2+ version supported by both the client and
         * server. Enum: default,2.0,2.1,3,3.0,3.11
         * @param sparse use sparse volumes
         * @param subdir Subdir to mount.
         * @param tagged_only Only use logical volumes tagged with 'pve-vm-ID'.
         * @param target iSCSI target.
         * @param thinpool LVM thin pool LV name.
         * @param transport Gluster transport: tcp or rdma Enum: tcp,rdma,unix
         * @param username RBD Id.
         * @param vgname Volume group name.
         * @param volume Glusterfs Volume.
         * @return Result
         * @throws JSONException
         */

        public Result create(String storage, String type, String authsupported, String base_, String blocksize, String bwlimit, String comstar_hg, String comstar_tg, String content, String datastore, Boolean disable, String domain, String encryption_key, String export, String fingerprint, String format, String fs_name, Boolean fuse, String is_mountpoint, String iscsiprovider, String keyring, Boolean krbd, String lio_tpg, String master_pubkey, Integer maxfiles, Boolean mkdir, String monhost, String mountpoint, String namespace_, Boolean nocow, String nodes, Boolean nowritecache, String options, String password, String path, String pool, Integer port, String portal, String preallocation, String prune_backups, Boolean saferemove, String saferemove_throughput, String server, String server2, String share, Boolean shared, String smbversion, Boolean sparse, String subdir, Boolean tagged_only, String target, String thinpool, String transport, String username, String vgname, String volume) throws JSONException {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("storage", storage);
            parameters.put("type", type);
            parameters.put("authsupported", authsupported);
            parameters.put("base", base_);
            parameters.put("blocksize", blocksize);
            parameters.put("bwlimit", bwlimit);
            parameters.put("comstar_hg", comstar_hg);
            parameters.put("comstar_tg", comstar_tg);
            parameters.put("content", content);
            parameters.put("datastore", datastore);
            parameters.put("disable", disable);
            parameters.put("domain", domain);
            parameters.put("encryption-key", encryption_key);
            parameters.put("export", export);
            parameters.put("fingerprint", fingerprint);
            parameters.put("format", format);
            parameters.put("fs-name", fs_name);
            parameters.put("fuse", fuse);
            parameters.put("is_mountpoint", is_mountpoint);
            parameters.put("iscsiprovider", iscsiprovider);
            parameters.put("keyring", keyring);
            parameters.put("krbd", krbd);
            parameters.put("lio_tpg", lio_tpg);
            parameters.put("master-pubkey", master_pubkey);
            parameters.put("maxfiles", maxfiles);
            parameters.put("mkdir", mkdir);
            parameters.put("monhost", monhost);
            parameters.put("mountpoint", mountpoint);
            parameters.put("namespace", namespace_);
            parameters.put("nocow", nocow);
            parameters.put("nodes", nodes);
            parameters.put("nowritecache", nowritecache);
            parameters.put("options", options);
            parameters.put("password", password);
            parameters.put("path", path);
            parameters.put("pool", pool);
            parameters.put("port", port);
            parameters.put("portal", portal);
            parameters.put("preallocation", preallocation);
            parameters.put("prune-backups", prune_backups);
            parameters.put("saferemove", saferemove);
            parameters.put("saferemove_throughput", saferemove_throughput);
            parameters.put("server", server);
            parameters.put("server2", server2);
            parameters.put("share", share);
            parameters.put("shared", shared);
            parameters.put("smbversion", smbversion);
            parameters.put("sparse", sparse);
            parameters.put("subdir", subdir);
            parameters.put("tagged_only", tagged_only);
            parameters.put("target", target);
            parameters.put("thinpool", thinpool);
            parameters.put("transport", transport);
            parameters.put("username", username);
            parameters.put("vgname", vgname);
            parameters.put("volume", volume);
            return _client.create("/storage", parameters);
        }

        /**
         * Create a new storage.
         *
         * @param storage The storage identifier.
         * @param type Storage type. Enum:
         * btrfs,cephfs,cifs,dir,glusterfs,iscsi,iscsidirect,lvm,lvmthin,nfs,pbs,rbd,zfs,zfspool
         * @return Result
         * @throws JSONException
         */

        public Result create(String storage, String type) throws JSONException {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("storage", storage);
            parameters.put("type", type);
            return _client.create("/storage", parameters);
        }

    }

    public class PVEAccess {

        private final PveClient _client;

        protected PVEAccess(PveClient client) {
            _client = client;

        }

        private PVEUsers _users;

        public PVEUsers getUsers() {
            return _users == null ? (_users = new PVEUsers(_client)) : _users;
        }
        private PVEGroups _groups;

        public PVEGroups getGroups() {
            return _groups == null ? (_groups = new PVEGroups(_client)) : _groups;
        }
        private PVERoles _roles;

        public PVERoles getRoles() {
            return _roles == null ? (_roles = new PVERoles(_client)) : _roles;
        }
        private PVEAcl _acl;

        public PVEAcl getAcl() {
            return _acl == null ? (_acl = new PVEAcl(_client)) : _acl;
        }
        private PVEDomains _domains;

        public PVEDomains getDomains() {
            return _domains == null ? (_domains = new PVEDomains(_client)) : _domains;
        }
        private PVEOpenid _openid;

        public PVEOpenid getOpenid() {
            return _openid == null ? (_openid = new PVEOpenid(_client)) : _openid;
        }
        private PVETfa _tfa;

        public PVETfa getTfa() {
            return _tfa == null ? (_tfa = new PVETfa(_client)) : _tfa;
        }
        private PVETicket _ticket;

        public PVETicket getTicket() {
            return _ticket == null ? (_ticket = new PVETicket(_client)) : _ticket;
        }
        private PVEPassword _password;

        public PVEPassword getPassword() {
            return _password == null ? (_password = new PVEPassword(_client)) : _password;
        }
        private PVEPermissions _permissions;

        public PVEPermissions getPermissions() {
            return _permissions == null ? (_permissions = new PVEPermissions(_client)) : _permissions;
        }

        public class PVEUsers {

            private final PveClient _client;

            protected PVEUsers(PveClient client) {
                _client = client;

            }

            public PVEUseridItem get(Object userid) {
                return new PVEUseridItem(_client, userid);
            }

            public class PVEUseridItem {

                private final PveClient _client;
                private final Object _userid;

                protected PVEUseridItem(PveClient client, Object userid) {
                    _client = client;
                    _userid = userid;
                }

                private PVETfa _tfa;

                public PVETfa getTfa() {
                    return _tfa == null ? (_tfa = new PVETfa(_client, _userid)) : _tfa;
                }
                private PVEToken _token;

                public PVEToken getToken() {
                    return _token == null ? (_token = new PVEToken(_client, _userid)) : _token;
                }

                public class PVETfa {

                    private final PveClient _client;
                    private final Object _userid;

                    protected PVETfa(PveClient client, Object userid) {
                        _client = client;
                        _userid = userid;
                    }

                    /**
                     * Get user TFA types (Personal and Realm).
                     *
                     * @param multiple Request all entries as an array.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result readUserTfaType(Boolean multiple) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("multiple", multiple);
                        return _client.get("/access/users/" + _userid + "/tfa", parameters);
                    }

                    /**
                     * Get user TFA types (Personal and Realm).
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result readUserTfaType() throws JSONException {
                        return _client.get("/access/users/" + _userid + "/tfa", null);
                    }

                }

                public class PVEToken {

                    private final PveClient _client;
                    private final Object _userid;

                    protected PVEToken(PveClient client, Object userid) {
                        _client = client;
                        _userid = userid;
                    }

                    public PVETokenidItem get(Object tokenid) {
                        return new PVETokenidItem(_client, _userid, tokenid);
                    }

                    public class PVETokenidItem {

                        private final PveClient _client;
                        private final Object _userid;
                        private final Object _tokenid;

                        protected PVETokenidItem(PveClient client, Object userid, Object tokenid) {
                            _client = client;
                            _userid = userid;
                            _tokenid = tokenid;
                        }

                        /**
                         * Remove API token for a specific user.
                         *
                         * @return Result
                         * @throws JSONException
                         */
                        public Result removeToken() throws JSONException {
                            return _client.delete("/access/users/" + _userid + "/token/" + _tokenid + "", null);
                        }

                        /**
                         * Get specific API token information.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result readToken() throws JSONException {
                            return _client.get("/access/users/" + _userid + "/token/" + _tokenid + "", null);
                        }

                        /**
                         * Generate a new API token for a specific user. NOTE:
                         * returns API token value, which needs to be stored as
                         * it cannot be retrieved afterwards!
                         *
                         * @param comment
                         * @param expire API token expiration date (seconds
                         * since epoch). '0' means no expiration date.
                         * @param privsep Restrict API token privileges with
                         * separate ACLs (default), or give full privileges of
                         * corresponding user.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result generateToken(String comment, Integer expire, Boolean privsep) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("comment", comment);
                            parameters.put("expire", expire);
                            parameters.put("privsep", privsep);
                            return _client.create("/access/users/" + _userid + "/token/" + _tokenid + "", parameters);
                        }

                        /**
                         * Generate a new API token for a specific user. NOTE:
                         * returns API token value, which needs to be stored as
                         * it cannot be retrieved afterwards!
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result generateToken() throws JSONException {
                            return _client.create("/access/users/" + _userid + "/token/" + _tokenid + "", null);
                        }

                        /**
                         * Update API token for a specific user.
                         *
                         * @param comment
                         * @param expire API token expiration date (seconds
                         * since epoch). '0' means no expiration date.
                         * @param privsep Restrict API token privileges with
                         * separate ACLs (default), or give full privileges of
                         * corresponding user.
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateTokenInfo(String comment, Integer expire, Boolean privsep) throws JSONException {
                            Map<String, Object> parameters = new HashMap<>();
                            parameters.put("comment", comment);
                            parameters.put("expire", expire);
                            parameters.put("privsep", privsep);
                            return _client.set("/access/users/" + _userid + "/token/" + _tokenid + "", parameters);
                        }

                        /**
                         * Update API token for a specific user.
                         *
                         * @return Result
                         * @throws JSONException
                         */

                        public Result updateTokenInfo() throws JSONException {
                            return _client.set("/access/users/" + _userid + "/token/" + _tokenid + "", null);
                        }

                    }

                    /**
                     * Get user API tokens.
                     *
                     * @return Result
                     * @throws JSONException
                     */
                    public Result tokenIndex() throws JSONException {
                        return _client.get("/access/users/" + _userid + "/token", null);
                    }

                }

                /**
                 * Delete user.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result deleteUser() throws JSONException {
                    return _client.delete("/access/users/" + _userid + "", null);
                }

                /**
                 * Get user configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result readUser() throws JSONException {
                    return _client.get("/access/users/" + _userid + "", null);
                }

                /**
                 * Update user configuration.
                 *
                 * @param append
                 * @param comment
                 * @param email
                 * @param enable Enable the account (default). You can set this
                 * to '0' to disable the account
                 * @param expire Account expiration date (seconds since epoch).
                 * '0' means no expiration date.
                 * @param firstname
                 * @param groups
                 * @param keys Keys for two factor auth (yubico).
                 * @param lastname
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateUser(Boolean append, String comment, String email, Boolean enable, Integer expire, String firstname, String groups, String keys, String lastname) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("append", append);
                    parameters.put("comment", comment);
                    parameters.put("email", email);
                    parameters.put("enable", enable);
                    parameters.put("expire", expire);
                    parameters.put("firstname", firstname);
                    parameters.put("groups", groups);
                    parameters.put("keys", keys);
                    parameters.put("lastname", lastname);
                    return _client.set("/access/users/" + _userid + "", parameters);
                }

                /**
                 * Update user configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateUser() throws JSONException {
                    return _client.set("/access/users/" + _userid + "", null);
                }

            }

            /**
             * User index.
             *
             * @param enabled Optional filter for enable property.
             * @param full Include group and token information.
             * @return Result
             * @throws JSONException
             */
            public Result index(Boolean enabled, Boolean full) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("enabled", enabled);
                parameters.put("full", full);
                return _client.get("/access/users", parameters);
            }

            /**
             * User index.
             *
             * @return Result
             * @throws JSONException
             */

            public Result index() throws JSONException {
                return _client.get("/access/users", null);
            }

            /**
             * Create new user.
             *
             * @param userid User ID
             * @param comment
             * @param email
             * @param enable Enable the account (default). You can set this to
             * '0' to disable the account
             * @param expire Account expiration date (seconds since epoch). '0'
             * means no expiration date.
             * @param firstname
             * @param groups
             * @param keys Keys for two factor auth (yubico).
             * @param lastname
             * @param password Initial password.
             * @return Result
             * @throws JSONException
             */

            public Result createUser(String userid, String comment, String email, Boolean enable, Integer expire, String firstname, String groups, String keys, String lastname, String password) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("userid", userid);
                parameters.put("comment", comment);
                parameters.put("email", email);
                parameters.put("enable", enable);
                parameters.put("expire", expire);
                parameters.put("firstname", firstname);
                parameters.put("groups", groups);
                parameters.put("keys", keys);
                parameters.put("lastname", lastname);
                parameters.put("password", password);
                return _client.create("/access/users", parameters);
            }

            /**
             * Create new user.
             *
             * @param userid User ID
             * @return Result
             * @throws JSONException
             */

            public Result createUser(String userid) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("userid", userid);
                return _client.create("/access/users", parameters);
            }

        }

        public class PVEGroups {

            private final PveClient _client;

            protected PVEGroups(PveClient client) {
                _client = client;

            }

            public PVEGroupidItem get(Object groupid) {
                return new PVEGroupidItem(_client, groupid);
            }

            public class PVEGroupidItem {

                private final PveClient _client;
                private final Object _groupid;

                protected PVEGroupidItem(PveClient client, Object groupid) {
                    _client = client;
                    _groupid = groupid;
                }

                /**
                 * Delete group.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result deleteGroup() throws JSONException {
                    return _client.delete("/access/groups/" + _groupid + "", null);
                }

                /**
                 * Get group configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result readGroup() throws JSONException {
                    return _client.get("/access/groups/" + _groupid + "", null);
                }

                /**
                 * Update group data.
                 *
                 * @param comment
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateGroup(String comment) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("comment", comment);
                    return _client.set("/access/groups/" + _groupid + "", parameters);
                }

                /**
                 * Update group data.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateGroup() throws JSONException {
                    return _client.set("/access/groups/" + _groupid + "", null);
                }

            }

            /**
             * Group index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/access/groups", null);
            }

            /**
             * Create new group.
             *
             * @param groupid
             * @param comment
             * @return Result
             * @throws JSONException
             */

            public Result createGroup(String groupid, String comment) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("groupid", groupid);
                parameters.put("comment", comment);
                return _client.create("/access/groups", parameters);
            }

            /**
             * Create new group.
             *
             * @param groupid
             * @return Result
             * @throws JSONException
             */

            public Result createGroup(String groupid) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("groupid", groupid);
                return _client.create("/access/groups", parameters);
            }

        }

        public class PVERoles {

            private final PveClient _client;

            protected PVERoles(PveClient client) {
                _client = client;

            }

            public PVERoleidItem get(Object roleid) {
                return new PVERoleidItem(_client, roleid);
            }

            public class PVERoleidItem {

                private final PveClient _client;
                private final Object _roleid;

                protected PVERoleidItem(PveClient client, Object roleid) {
                    _client = client;
                    _roleid = roleid;
                }

                /**
                 * Delete role.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result deleteRole() throws JSONException {
                    return _client.delete("/access/roles/" + _roleid + "", null);
                }

                /**
                 * Get role configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result readRole() throws JSONException {
                    return _client.get("/access/roles/" + _roleid + "", null);
                }

                /**
                 * Update an existing role.
                 *
                 * @param append
                 * @param privs
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateRole(Boolean append, String privs) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("append", append);
                    parameters.put("privs", privs);
                    return _client.set("/access/roles/" + _roleid + "", parameters);
                }

                /**
                 * Update an existing role.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result updateRole() throws JSONException {
                    return _client.set("/access/roles/" + _roleid + "", null);
                }

            }

            /**
             * Role index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/access/roles", null);
            }

            /**
             * Create new role.
             *
             * @param roleid
             * @param privs
             * @return Result
             * @throws JSONException
             */

            public Result createRole(String roleid, String privs) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("roleid", roleid);
                parameters.put("privs", privs);
                return _client.create("/access/roles", parameters);
            }

            /**
             * Create new role.
             *
             * @param roleid
             * @return Result
             * @throws JSONException
             */

            public Result createRole(String roleid) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("roleid", roleid);
                return _client.create("/access/roles", parameters);
            }

        }

        public class PVEAcl {

            private final PveClient _client;

            protected PVEAcl(PveClient client) {
                _client = client;

            }

            /**
             * Get Access Control List (ACLs).
             *
             * @return Result
             * @throws JSONException
             */
            public Result readAcl() throws JSONException {
                return _client.get("/access/acl", null);
            }

            /**
             * Update Access Control List (add or remove permissions).
             *
             * @param path Access control path
             * @param roles List of roles.
             * @param delete Remove permissions (instead of adding it).
             * @param groups List of groups.
             * @param propagate Allow to propagate (inherit) permissions.
             * @param tokens List of API tokens.
             * @param users List of users.
             * @return Result
             * @throws JSONException
             */

            public Result updateAcl(String path, String roles, Boolean delete, String groups, Boolean propagate, String tokens, String users) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("path", path);
                parameters.put("roles", roles);
                parameters.put("delete", delete);
                parameters.put("groups", groups);
                parameters.put("propagate", propagate);
                parameters.put("tokens", tokens);
                parameters.put("users", users);
                return _client.set("/access/acl", parameters);
            }

            /**
             * Update Access Control List (add or remove permissions).
             *
             * @param path Access control path
             * @param roles List of roles.
             * @return Result
             * @throws JSONException
             */

            public Result updateAcl(String path, String roles) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("path", path);
                parameters.put("roles", roles);
                return _client.set("/access/acl", parameters);
            }

        }

        public class PVEDomains {

            private final PveClient _client;

            protected PVEDomains(PveClient client) {
                _client = client;

            }

            public PVERealmItem get(Object realm) {
                return new PVERealmItem(_client, realm);
            }

            public class PVERealmItem {

                private final PveClient _client;
                private final Object _realm;

                protected PVERealmItem(PveClient client, Object realm) {
                    _client = client;
                    _realm = realm;
                }

                private PVESync _sync;

                public PVESync getSync() {
                    return _sync == null ? (_sync = new PVESync(_client, _realm)) : _sync;
                }

                public class PVESync {

                    private final PveClient _client;
                    private final Object _realm;

                    protected PVESync(PveClient client, Object realm) {
                        _client = client;
                        _realm = realm;
                    }

                    /**
                     * Syncs users and/or groups from the configured LDAP to
                     * user.cfg. NOTE: Synced groups will have the name
                     * 'name-$realm', so make sure those groups do not exist to
                     * prevent overwriting.
                     *
                     * @param dry_run If set, does not write anything.
                     * @param enable_new Enable newly synced users immediately.
                     * @param full If set, uses the LDAP Directory as source of
                     * truth, deleting users or groups not returned from the
                     * sync. Otherwise only syncs information which is not
                     * already present, and does not deletes or modifies
                     * anything else.
                     * @param purge Remove ACLs for users or groups which were
                     * removed from the config during a sync.
                     * @param scope Select what to sync. Enum: users,groups,both
                     * @return Result
                     * @throws JSONException
                     */
                    public Result sync(Boolean dry_run, Boolean enable_new, Boolean full, Boolean purge, String scope) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("dry-run", dry_run);
                        parameters.put("enable-new", enable_new);
                        parameters.put("full", full);
                        parameters.put("purge", purge);
                        parameters.put("scope", scope);
                        return _client.create("/access/domains/" + _realm + "/sync", parameters);
                    }

                    /**
                     * Syncs users and/or groups from the configured LDAP to
                     * user.cfg. NOTE: Synced groups will have the name
                     * 'name-$realm', so make sure those groups do not exist to
                     * prevent overwriting.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result sync() throws JSONException {
                        return _client.create("/access/domains/" + _realm + "/sync", null);
                    }

                }

                /**
                 * Delete an authentication server.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result delete() throws JSONException {
                    return _client.delete("/access/domains/" + _realm + "", null);
                }

                /**
                 * Get auth server configuration.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result read() throws JSONException {
                    return _client.get("/access/domains/" + _realm + "", null);
                }

                /**
                 * Update authentication server settings.
                 *
                 * @param autocreate Automatically create users if they do not
                 * exist.
                 * @param base_dn LDAP base domain name
                 * @param bind_dn LDAP bind domain name
                 * @param capath Path to the CA certificate store
                 * @param case_sensitive username is case-sensitive
                 * @param cert Path to the client certificate
                 * @param certkey Path to the client certificate key
                 * @param client_id OpenID Client ID
                 * @param client_key OpenID Client Key
                 * @param comment Description.
                 * @param default_ Use this as default realm
                 * @param delete A list of settings you want to delete.
                 * @param digest Prevent changes if current configuration file
                 * has different SHA1 digest. This can be used to prevent
                 * concurrent modifications.
                 * @param domain AD domain name
                 * @param filter LDAP filter for user sync.
                 * @param group_classes The objectclasses for groups.
                 * @param group_dn LDAP base domain name for group sync. If not
                 * set, the base_dn will be used.
                 * @param group_filter LDAP filter for group sync.
                 * @param group_name_attr LDAP attribute representing a groups
                 * name. If not set or found, the first value of the DN will be
                 * used as name.
                 * @param issuer_url OpenID Issuer Url
                 * @param mode LDAP protocol mode. Enum:
                 * ldap,ldaps,ldap+starttls
                 * @param password LDAP bind password. Will be stored in
                 * '/etc/pve/priv/realm/&amp;lt;REALM&amp;gt;.pw'.
                 * @param port Server port.
                 * @param secure Use secure LDAPS protocol. DEPRECATED: use
                 * 'mode' instead.
                 * @param server1 Server IP address (or DNS name)
                 * @param server2 Fallback Server IP address (or DNS name)
                 * @param sslversion LDAPS TLS/SSL version. It's not recommended
                 * to use version older than 1.2! Enum:
                 * tlsv1,tlsv1_1,tlsv1_2,tlsv1_3
                 * @param sync_defaults_options The default options for behavior
                 * of synchronizations.
                 * @param sync_attributes Comma separated list of key=value
                 * pairs for specifying which LDAP attributes map to which PVE
                 * user field. For example, to map the LDAP attribute 'mail' to
                 * PVEs 'email', write 'email=mail'. By default, each PVE user
                 * field is represented by an LDAP attribute of the same name.
                 * @param tfa Use Two-factor authentication.
                 * @param user_attr LDAP user attribute name
                 * @param user_classes The objectclasses for users.
                 * @param verify Verify the server's SSL certificate
                 * @return Result
                 * @throws JSONException
                 */

                public Result update(Boolean autocreate, String base_dn, String bind_dn, String capath, Boolean case_sensitive, String cert, String certkey, String client_id, String client_key, String comment, Boolean default_, String delete, String digest, String domain, String filter, String group_classes, String group_dn, String group_filter, String group_name_attr, String issuer_url, String mode, String password, Integer port, Boolean secure, String server1, String server2, String sslversion, String sync_defaults_options, String sync_attributes, String tfa, String user_attr, String user_classes, Boolean verify) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("autocreate", autocreate);
                    parameters.put("base_dn", base_dn);
                    parameters.put("bind_dn", bind_dn);
                    parameters.put("capath", capath);
                    parameters.put("case-sensitive", case_sensitive);
                    parameters.put("cert", cert);
                    parameters.put("certkey", certkey);
                    parameters.put("client-id", client_id);
                    parameters.put("client-key", client_key);
                    parameters.put("comment", comment);
                    parameters.put("default", default_);
                    parameters.put("delete", delete);
                    parameters.put("digest", digest);
                    parameters.put("domain", domain);
                    parameters.put("filter", filter);
                    parameters.put("group_classes", group_classes);
                    parameters.put("group_dn", group_dn);
                    parameters.put("group_filter", group_filter);
                    parameters.put("group_name_attr", group_name_attr);
                    parameters.put("issuer-url", issuer_url);
                    parameters.put("mode", mode);
                    parameters.put("password", password);
                    parameters.put("port", port);
                    parameters.put("secure", secure);
                    parameters.put("server1", server1);
                    parameters.put("server2", server2);
                    parameters.put("sslversion", sslversion);
                    parameters.put("sync-defaults-options", sync_defaults_options);
                    parameters.put("sync_attributes", sync_attributes);
                    parameters.put("tfa", tfa);
                    parameters.put("user_attr", user_attr);
                    parameters.put("user_classes", user_classes);
                    parameters.put("verify", verify);
                    return _client.set("/access/domains/" + _realm + "", parameters);
                }

                /**
                 * Update authentication server settings.
                 *
                 * @return Result
                 * @throws JSONException
                 */

                public Result update() throws JSONException {
                    return _client.set("/access/domains/" + _realm + "", null);
                }

            }

            /**
             * Authentication domain index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/access/domains", null);
            }

            /**
             * Add an authentication server.
             *
             * @param realm Authentication domain ID
             * @param type Realm type. Enum: ad,ldap,openid,pam,pve
             * @param autocreate Automatically create users if they do not
             * exist.
             * @param base_dn LDAP base domain name
             * @param bind_dn LDAP bind domain name
             * @param capath Path to the CA certificate store
             * @param case_sensitive username is case-sensitive
             * @param cert Path to the client certificate
             * @param certkey Path to the client certificate key
             * @param client_id OpenID Client ID
             * @param client_key OpenID Client Key
             * @param comment Description.
             * @param default_ Use this as default realm
             * @param domain AD domain name
             * @param filter LDAP filter for user sync.
             * @param group_classes The objectclasses for groups.
             * @param group_dn LDAP base domain name for group sync. If not set,
             * the base_dn will be used.
             * @param group_filter LDAP filter for group sync.
             * @param group_name_attr LDAP attribute representing a groups name.
             * If not set or found, the first value of the DN will be used as
             * name.
             * @param issuer_url OpenID Issuer Url
             * @param mode LDAP protocol mode. Enum: ldap,ldaps,ldap+starttls
             * @param password LDAP bind password. Will be stored in
             * '/etc/pve/priv/realm/&amp;lt;REALM&amp;gt;.pw'.
             * @param port Server port.
             * @param secure Use secure LDAPS protocol. DEPRECATED: use 'mode'
             * instead.
             * @param server1 Server IP address (or DNS name)
             * @param server2 Fallback Server IP address (or DNS name)
             * @param sslversion LDAPS TLS/SSL version. It's not recommended to
             * use version older than 1.2! Enum: tlsv1,tlsv1_1,tlsv1_2,tlsv1_3
             * @param sync_defaults_options The default options for behavior of
             * synchronizations.
             * @param sync_attributes Comma separated list of key=value pairs
             * for specifying which LDAP attributes map to which PVE user field.
             * For example, to map the LDAP attribute 'mail' to PVEs 'email',
             * write 'email=mail'. By default, each PVE user field is
             * represented by an LDAP attribute of the same name.
             * @param tfa Use Two-factor authentication.
             * @param user_attr LDAP user attribute name
             * @param user_classes The objectclasses for users.
             * @param username_claim OpenID claim used to generate the unique
             * username. Enum: subject,username,email
             * @param verify Verify the server's SSL certificate
             * @return Result
             * @throws JSONException
             */

            public Result create(String realm, String type, Boolean autocreate, String base_dn, String bind_dn, String capath, Boolean case_sensitive, String cert, String certkey, String client_id, String client_key, String comment, Boolean default_, String domain, String filter, String group_classes, String group_dn, String group_filter, String group_name_attr, String issuer_url, String mode, String password, Integer port, Boolean secure, String server1, String server2, String sslversion, String sync_defaults_options, String sync_attributes, String tfa, String user_attr, String user_classes, String username_claim, Boolean verify) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("realm", realm);
                parameters.put("type", type);
                parameters.put("autocreate", autocreate);
                parameters.put("base_dn", base_dn);
                parameters.put("bind_dn", bind_dn);
                parameters.put("capath", capath);
                parameters.put("case-sensitive", case_sensitive);
                parameters.put("cert", cert);
                parameters.put("certkey", certkey);
                parameters.put("client-id", client_id);
                parameters.put("client-key", client_key);
                parameters.put("comment", comment);
                parameters.put("default", default_);
                parameters.put("domain", domain);
                parameters.put("filter", filter);
                parameters.put("group_classes", group_classes);
                parameters.put("group_dn", group_dn);
                parameters.put("group_filter", group_filter);
                parameters.put("group_name_attr", group_name_attr);
                parameters.put("issuer-url", issuer_url);
                parameters.put("mode", mode);
                parameters.put("password", password);
                parameters.put("port", port);
                parameters.put("secure", secure);
                parameters.put("server1", server1);
                parameters.put("server2", server2);
                parameters.put("sslversion", sslversion);
                parameters.put("sync-defaults-options", sync_defaults_options);
                parameters.put("sync_attributes", sync_attributes);
                parameters.put("tfa", tfa);
                parameters.put("user_attr", user_attr);
                parameters.put("user_classes", user_classes);
                parameters.put("username-claim", username_claim);
                parameters.put("verify", verify);
                return _client.create("/access/domains", parameters);
            }

            /**
             * Add an authentication server.
             *
             * @param realm Authentication domain ID
             * @param type Realm type. Enum: ad,ldap,openid,pam,pve
             * @return Result
             * @throws JSONException
             */

            public Result create(String realm, String type) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("realm", realm);
                parameters.put("type", type);
                return _client.create("/access/domains", parameters);
            }

        }

        public class PVEOpenid {

            private final PveClient _client;

            protected PVEOpenid(PveClient client) {
                _client = client;

            }

            private PVEAuthUrl _authUrl;

            public PVEAuthUrl getAuthUrl() {
                return _authUrl == null ? (_authUrl = new PVEAuthUrl(_client)) : _authUrl;
            }
            private PVELogin _login;

            public PVELogin getLogin() {
                return _login == null ? (_login = new PVELogin(_client)) : _login;
            }

            public class PVEAuthUrl {

                private final PveClient _client;

                protected PVEAuthUrl(PveClient client) {
                    _client = client;

                }

                /**
                 * Get the OpenId Authorization Url for the specified realm.
                 *
                 * @param realm Authentication domain ID
                 * @param redirect_url Redirection Url. The client should set
                 * this to the used server url (location.origin).
                 * @return Result
                 * @throws JSONException
                 */
                public Result authUrl(String realm, String redirect_url) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("realm", realm);
                    parameters.put("redirect-url", redirect_url);
                    return _client.create("/access/openid/auth-url", parameters);
                }

            }

            public class PVELogin {

                private final PveClient _client;

                protected PVELogin(PveClient client) {
                    _client = client;

                }

                /**
                 * Verify OpenID authorization code and create a ticket.
                 *
                 * @param code OpenId authorization code.
                 * @param redirect_url Redirection Url. The client should set
                 * this to the used server url (location.origin).
                 * @param state OpenId state.
                 * @return Result
                 * @throws JSONException
                 */
                public Result login(String code, String redirect_url, String state) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("code", code);
                    parameters.put("redirect-url", redirect_url);
                    parameters.put("state", state);
                    return _client.create("/access/openid/login", parameters);
                }

            }

            /**
             * Directory index.
             *
             * @return Result
             * @throws JSONException
             */
            public Result index() throws JSONException {
                return _client.get("/access/openid", null);
            }

        }

        public class PVETfa {

            private final PveClient _client;

            protected PVETfa(PveClient client) {
                _client = client;

            }

            public PVEUseridItem get(Object userid) {
                return new PVEUseridItem(_client, userid);
            }

            public class PVEUseridItem {

                private final PveClient _client;
                private final Object _userid;

                protected PVEUseridItem(PveClient client, Object userid) {
                    _client = client;
                    _userid = userid;
                }

                public PVEIdItem get(Object id) {
                    return new PVEIdItem(_client, _userid, id);
                }

                public class PVEIdItem {

                    private final PveClient _client;
                    private final Object _userid;
                    private final Object _id;

                    protected PVEIdItem(PveClient client, Object userid, Object id) {
                        _client = client;
                        _userid = userid;
                        _id = id;
                    }

                    /**
                     * Delete a TFA entry by ID.
                     *
                     * @param password The current password.
                     * @return Result
                     * @throws JSONException
                     */
                    public Result deleteTfa(String password) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("password", password);
                        return _client.delete("/access/tfa/" + _userid + "/" + _id + "", parameters);
                    }

                    /**
                     * Delete a TFA entry by ID.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result deleteTfa() throws JSONException {
                        return _client.delete("/access/tfa/" + _userid + "/" + _id + "", null);
                    }

                    /**
                     * Fetch a requested TFA entry if present.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result getTfaEntry() throws JSONException {
                        return _client.get("/access/tfa/" + _userid + "/" + _id + "", null);
                    }

                    /**
                     * Add a TFA entry for a user.
                     *
                     * @param description A description to distinguish multiple
                     * entries from one another
                     * @param enable Whether the entry should be enabled for
                     * login.
                     * @param password The current password.
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateTfaEntry(String description, Boolean enable, String password) throws JSONException {
                        Map<String, Object> parameters = new HashMap<>();
                        parameters.put("description", description);
                        parameters.put("enable", enable);
                        parameters.put("password", password);
                        return _client.set("/access/tfa/" + _userid + "/" + _id + "", parameters);
                    }

                    /**
                     * Add a TFA entry for a user.
                     *
                     * @return Result
                     * @throws JSONException
                     */

                    public Result updateTfaEntry() throws JSONException {
                        return _client.set("/access/tfa/" + _userid + "/" + _id + "", null);
                    }

                }

                /**
                 * List TFA configurations of users.
                 *
                 * @return Result
                 * @throws JSONException
                 */
                public Result listUserTfa() throws JSONException {
                    return _client.get("/access/tfa/" + _userid + "", null);
                }

                /**
                 * Add a TFA entry for a user.
                 *
                 * @param type TFA Entry Type. Enum:
                 * totp,u2f,webauthn,recovery,yubico
                 * @param challenge When responding to a u2f challenge: the
                 * original challenge string
                 * @param description A description to distinguish multiple
                 * entries from one another
                 * @param password The current password.
                 * @param totp A totp URI.
                 * @param value The current value for the provided totp URI, or
                 * a Webauthn/U2F challenge response
                 * @return Result
                 * @throws JSONException
                 */

                public Result addTfaEntry(String type, String challenge, String description, String password, String totp, String value) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    parameters.put("challenge", challenge);
                    parameters.put("description", description);
                    parameters.put("password", password);
                    parameters.put("totp", totp);
                    parameters.put("value", value);
                    return _client.create("/access/tfa/" + _userid + "", parameters);
                }

                /**
                 * Add a TFA entry for a user.
                 *
                 * @param type TFA Entry Type. Enum:
                 * totp,u2f,webauthn,recovery,yubico
                 * @return Result
                 * @throws JSONException
                 */

                public Result addTfaEntry(String type) throws JSONException {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("type", type);
                    return _client.create("/access/tfa/" + _userid + "", parameters);
                }

            }

            /**
             * List TFA configurations of users.
             *
             * @return Result
             * @throws JSONException
             */
            public Result listTfa() throws JSONException {
                return _client.get("/access/tfa", null);
            }

            /**
             * Finish a u2f challenge.
             *
             * @param response The response to the current authentication
             * challenge.
             * @return Result
             * @throws JSONException
             */

            public Result verifyTfa(String response) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("response", response);
                return _client.create("/access/tfa", parameters);
            }

        }

        public class PVETicket {

            private final PveClient _client;

            protected PVETicket(PveClient client) {
                _client = client;

            }

            /**
             * Dummy. Useful for formatters which want to provide a login page.
             *
             * @return Result
             * @throws JSONException
             */
            public Result getTicket() throws JSONException {
                return _client.get("/access/ticket", null);
            }

            /**
             * Create or verify authentication ticket.
             *
             * @param password The secret password. This can also be a valid
             * ticket.
             * @param username User name
             * @param new_format With webauthn the format of half-authenticated
             * tickts changed. New clients should pass 1 here and not worry
             * about the old format. The old format is deprecated and will be
             * retired with PVE-8.0
             * @param otp One-time password for Two-factor authentication.
             * @param path Verify ticket, and check if user have access 'privs'
             * on 'path'
             * @param privs Verify ticket, and check if user have access 'privs'
             * on 'path'
             * @param realm You can optionally pass the realm using this
             * parameter. Normally the realm is simply added to the username
             * &amp;lt;username&amp;gt;@&amp;lt;relam&amp;gt;.
             * @param tfa_challenge The signed TFA challenge string the user
             * wants to respond to.
             * @return Result
             * @throws JSONException
             */

            public Result createTicket(String password, String username, Boolean new_format, String otp, String path, String privs, String realm, String tfa_challenge) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("password", password);
                parameters.put("username", username);
                parameters.put("new-format", new_format);
                parameters.put("otp", otp);
                parameters.put("path", path);
                parameters.put("privs", privs);
                parameters.put("realm", realm);
                parameters.put("tfa-challenge", tfa_challenge);
                return _client.create("/access/ticket", parameters);
            }

            /**
             * Create or verify authentication ticket.
             *
             * @param password The secret password. This can also be a valid
             * ticket.
             * @param username User name
             * @return Result
             * @throws JSONException
             */

            public Result createTicket(String password, String username) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("password", password);
                parameters.put("username", username);
                return _client.create("/access/ticket", parameters);
            }

        }

        public class PVEPassword {

            private final PveClient _client;

            protected PVEPassword(PveClient client) {
                _client = client;

            }

            /**
             * Change user password.
             *
             * @param password The new password.
             * @param userid User ID
             * @return Result
             * @throws JSONException
             */
            public Result changePassword(String password, String userid) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("password", password);
                parameters.put("userid", userid);
                return _client.set("/access/password", parameters);
            }

        }

        public class PVEPermissions {

            private final PveClient _client;

            protected PVEPermissions(PveClient client) {
                _client = client;

            }

            /**
             * Retrieve effective permissions of given user/token.
             *
             * @param path Only dump this specific path, not the whole tree.
             * @param userid User ID or full API token ID
             * @return Result
             * @throws JSONException
             */
            public Result permissions(String path, String userid) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("path", path);
                parameters.put("userid", userid);
                return _client.get("/access/permissions", parameters);
            }

            /**
             * Retrieve effective permissions of given user/token.
             *
             * @return Result
             * @throws JSONException
             */

            public Result permissions() throws JSONException {
                return _client.get("/access/permissions", null);
            }

        }

        /**
         * Directory index.
         *
         * @return Result
         * @throws JSONException
         */
        public Result index() throws JSONException {
            return _client.get("/access", null);
        }

    }

    public class PVEPools {

        private final PveClient _client;

        protected PVEPools(PveClient client) {
            _client = client;

        }

        public PVEPoolidItem get(Object poolid) {
            return new PVEPoolidItem(_client, poolid);
        }

        public class PVEPoolidItem {

            private final PveClient _client;
            private final Object _poolid;

            protected PVEPoolidItem(PveClient client, Object poolid) {
                _client = client;
                _poolid = poolid;
            }

            /**
             * Delete pool.
             *
             * @return Result
             * @throws JSONException
             */
            public Result deletePool() throws JSONException {
                return _client.delete("/pools/" + _poolid + "", null);
            }

            /**
             * Get pool configuration.
             *
             * @return Result
             * @throws JSONException
             */

            public Result readPool() throws JSONException {
                return _client.get("/pools/" + _poolid + "", null);
            }

            /**
             * Update pool data.
             *
             * @param comment
             * @param delete Remove vms/storage (instead of adding it).
             * @param storage List of storage IDs.
             * @param vms List of virtual machines.
             * @return Result
             * @throws JSONException
             */

            public Result updatePool(String comment, Boolean delete, String storage, String vms) throws JSONException {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("comment", comment);
                parameters.put("delete", delete);
                parameters.put("storage", storage);
                parameters.put("vms", vms);
                return _client.set("/pools/" + _poolid + "", parameters);
            }

            /**
             * Update pool data.
             *
             * @return Result
             * @throws JSONException
             */

            public Result updatePool() throws JSONException {
                return _client.set("/pools/" + _poolid + "", null);
            }

        }

        /**
         * Pool index.
         *
         * @return Result
         * @throws JSONException
         */
        public Result index() throws JSONException {
            return _client.get("/pools", null);
        }

        /**
         * Create new pool.
         *
         * @param poolid
         * @param comment
         * @return Result
         * @throws JSONException
         */

        public Result createPool(String poolid, String comment) throws JSONException {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("poolid", poolid);
            parameters.put("comment", comment);
            return _client.create("/pools", parameters);
        }

        /**
         * Create new pool.
         *
         * @param poolid
         * @return Result
         * @throws JSONException
         */

        public Result createPool(String poolid) throws JSONException {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("poolid", poolid);
            return _client.create("/pools", parameters);
        }

    }

    public class PVEVersion {

        private final PveClient _client;

        protected PVEVersion(PveClient client) {
            _client = client;

        }

        /**
         * API version details, including some parts of the global datacenter
         * config.
         *
         * @return Result
         * @throws JSONException
         */
        public Result version() throws JSONException {
            return _client.get("/version", null);
        }

    }

}
