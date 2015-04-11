class Bob
  class Message
    def initialize(original_message)
      @message = original_message.to_s
    end

    def exclamation?
      @message.end_with?("!")
    end

    def forceful_talking?
      @message =~ /\Alet(?:'s)?/i
    end

    def silence?
      @message.strip.empty?
    end

    def question?
      @message.end_with?("?")
    end

    def shout?
      real_speech? and upcase?
    end

    def real_speech?
      @message =~ /[A-Z]/
    end

    def upcase?
      @message == @message.upcase
    end

    def to_s
      @message
    end
  end

  class MessageMatcher
    def initialize(&block)
      @matchers = []
    end

    def match(&matcher)
      @matchers << matcher
      self
    end

    def match?(message)
      message = Message.new(message)
      @matchers.any? { |matcher| matcher.call(message) }
    end
    alias_method :===, :match?
  end

  # Responds to a text message.
  # The actual response depends on the processing order due to the fatal design flaw.
  #
  def hey(message)
    case message
    when shout then "Woah, chill out!"
    when question then "Sure."
    when silence then "Fine. Be that way!"
    else "Whatever."
    end
  end

  def shout
    MessageMatcher.new.
      match { |msg| msg.exclamation? && !msg.forceful_talking? }.
      match { |msg| msg.shout? }
  end

  def question
    MessageMatcher.new.match { |msg| msg.question? }
  end

  def silence
    MessageMatcher.new.match { |msg| msg.silence? }
  end
end
