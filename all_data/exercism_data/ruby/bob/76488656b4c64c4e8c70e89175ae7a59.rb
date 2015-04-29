class Bob
  def hey(raw_message)
    message = Classifier.new(raw_message)
    if message.is_shouting?
      'Woah, chill out!'
    elsif message.is_question?
      'Sure.'
    elsif message.is_silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  class Classifier
    attr_accessor :message
    def initialize(message)
      @message = message.gsub(/\n/,'')
    end

    def is_shouting?
      message !~ /[a-z]/ && message =~ /[A-Z]/
    end

    def is_question?
      message =~ /\?$/
    end

    def is_silence?
      message =~ /^\s*$/
    end
  end
end
