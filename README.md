# OAuth

client:クライアント  
oauth:認可サーバ+リソースサーバ  

seleniumはビルド対象からはずしている。  

Spel調査結果  
認可サーバ、リソースサーバが同一warでデプロイされている場合はRoleとScopeをそのままspelで使用できる。  
異なるwarでデプロイされている場合は、アクセストークンにある`Authentication`を取得してspelで使用できるようにする必要があるので、
以下のクラスのいずれかを拡張、変更する必要がありそう
* タグ`oauth2:web-expression-handler `
* `OAuth2MethodSecurityExpressionHandler`
* `ExpressionHandlerBeanDefinitionParser`

