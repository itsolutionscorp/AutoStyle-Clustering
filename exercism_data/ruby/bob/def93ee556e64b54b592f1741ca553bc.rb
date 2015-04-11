class Bob
  CONDITION_RESPONSE_MAP = {
    empty: 'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    question: 'Sure.',
    exists: 'Whatever.'
  }.freeze

  def hey(input)
    respond_to MessageResponder.new(input, response_conditions).condition
  end

private

  def respond_to(condition)
    responses.fetch condition
  end

  def responses
    CONDITION_RESPONSE_MAP
  end

  def response_conditions
    responses.keys
  end

  class MessageResponder
    attr_accessor :conditions
    attr_reader   :message

    def initialize(message, conditions)
      self.message = message
      self.conditions = conditions
    end

    def message=(value)
      @message = Message.new(value)
    end

    def condition
      conditions.detect do |condition|
        message.send "#{condition}?"
      end
    end
  end

  class Message
    attr_accessor :message

    def initialize(message)
      self.message = message
    end

    def empty?
      ! message[/\w/]
    end

    def question?
      message.end_with? '?'
    end

    def shouting?
      message.upcase == message
    end

    def exists?
      true
    end
  end
end
