/* load the records. */
/* Resource Owner Records. password=demo */
INSERT INTO users(username, password, enabled) VALUES('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', true);
INSERT INTO authorities(username, authority) VALUES('demo', 'READ');

/* Client Records. */
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES('testClient', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'hogehoge', 10, 20);

INSERT INTO scopes(client_id, scope) VALUES('testClient', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('testClient', 'WRITE');
INSERT INTO scopes(client_id, scope) VALUES('testClient', 'UPDATE');
INSERT INTO scopes(client_id, scope) VALUES('testClient', 'DELETE');

INSERT INTO resource_ids(client_id, resource_id) VALUES('testClient', 'todoResource');

INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient', 'authorization_code');

INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'client-web/');

COMMIT;