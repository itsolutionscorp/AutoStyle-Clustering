class Bob
  def hey(message)
    Conversation.new(message).start.to_s
  end
end

class Conversation
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def start
    possible_responses.detect { |response| response.will_respond_to?(message) }
  end

  def possible_responses
    [
      Response::Blank.new,
      Response::Shouting.new,
      Response::Question.new,
      Response::Default.new
    ]
  end
end

module Response
  class Blank
    def will_respond_to?(message)
      message.nil? || message.empty?
    end

    def to_s
      "Fine. Be that way!"
    end
  end

  class Shouting
    def will_respond_to?(message)
      message.upcase == message
    end

    def to_s
      "Woah, chill out!"
    end
  end

  class Question
    def will_respond_to?(message)
      message[-1, 1] == '?'
    end

    def to_s
      "Sure."
    end
  end

  class Default
    def will_respond_to?(message)
      true
    end

    def to_s
      "Whatever."
    end
  end
end
