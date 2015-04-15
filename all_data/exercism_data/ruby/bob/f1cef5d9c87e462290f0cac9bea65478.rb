module ResponseSystem

  def self.included(target)
    target.extend DSL
  end

  module DSL

    def respond_to(*message_types, with: :response)
      message_types.each do |message_type|
        responses_to[message_type] = with
      end
    end

    def identify_message_as(message_type, &identifier_block)
      identifiers[message_type] = identifier_block
    end

    def default_response(response)
      responses_to.default = response
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

  respond_to :silence, :nothing,
             with: 'Fine. Be that way.'

  respond_to :shouting,
             with: 'Woah, chill out!'

  respond_to :question,
             with: 'Sure.'

  default_response 'Whatever.'

  identify_message_as :silence do |message|
    message.respond_to?(:empty?) && message.empty?
  end

  identify_message_as :nothing do |message|
    message.nil?
  end

  identify_message_as :shouting do |message|
    message == message.upcase
  end

  identify_message_as :question do |message|
    message[-1] == '?'
  end

  def hey(message)
    message_type = identify_message(message)
    respond_to(message_type)
  end

end
