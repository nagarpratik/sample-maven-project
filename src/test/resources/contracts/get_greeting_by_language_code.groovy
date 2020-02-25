import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "should return a Hello World greeting in English"

  request {
    url "/greetings/locales/en_US"
    method GET()
  }

  response {
    status 200
    headers {
      contentType applicationJson()
    }
    body (
         status : 'success',
         data: [
                 greeting : 'Hello World!'
               ] 
    )
  }
}