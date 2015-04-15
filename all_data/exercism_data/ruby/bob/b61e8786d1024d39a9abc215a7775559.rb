module ResponseSystem

  def self.included(target)
    target.extend DSL
  end

  module DSL

    def respond_to(message_type, with: :response, when_message: :identifier)
      responses_to[message_type] = with
      identifiers[message_type]  = when_message
    end

    def default_response(response)
      @responses_to.default = response
    end

    def responses_to
      @responses_to ||= {}
    end

    def identifiers
      @identifiers ||= {}
    end

  end

private

  def identify_message(message)
    self.class.identifiers.each do |message_type, identifier|
      break message_type if identifier.call(message)
    end
  end

  def respond_to(message_type)
    self.class.responses_to[message_type]
  end

end

class Bob

  include ResponseSystem

  respond_to :silence,
             with: 'Fine. Be that way.',
             when_message: -> (message) {
               message.nil? || message.empty?
             }

  respond_to :shouting,
             with: 'Woah, chill out!',
             when_message: -> (message) {
               message == message.upcase
             }

  respond_to :question,
             with: 'Sure.',
             when_message: -> (message) {
               message[-1] == '?'
             }

  default_response 'Whatever.'

  def hey(message)
    message_type = identify_message(message)
    respond_to(message_type)
  end

end
