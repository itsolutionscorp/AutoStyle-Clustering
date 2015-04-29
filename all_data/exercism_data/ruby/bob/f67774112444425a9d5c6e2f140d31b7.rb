require './String_Validations'
require './Message'

class Bob

  def hey(message)
    response = Message.new(message)
    response.validate_to_respond
  end

end
