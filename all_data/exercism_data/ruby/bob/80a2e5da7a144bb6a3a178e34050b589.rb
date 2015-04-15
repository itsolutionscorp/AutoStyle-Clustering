require_relative 'message_responder'

class Bob
  def initialize(options = {})
    @responder = options.fetch(:responder) { MessageResponder.new }
  end

  def hey(text)
    response = @responder.respond(text)
    response.output
  end
end
