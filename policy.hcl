path "secret/data/devwebapp/config" {
  capabilities = ["read"]
}
path "mongodb/creds/my-role" {
  capabilities = ["read","list","update"]
}

path "kv/event" {
  capabilities = ["read","list"]
}
path "kv/event/*" {
  capabilities = ["read","list"]
}