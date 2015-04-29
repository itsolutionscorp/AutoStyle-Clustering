class Bob
  def hey message
    case message
    when SilentMessageChecker
      'Fine. Be that way!'
    when YellingMessageChecker
      'Woah, chill out!'
    when QuestionMessageChecker
      'Sure.'
    else
      'Whatever.'
    end
  end

  class SilentMessageChecker
    def self.=== message
      message.to_s.empty?
    end
  end

  class YellingMessageChecker
    def self.=== message
      message == message.upcase
    end
  end

  class QuestionMessageChecker
    def self.=== message
      message.end_with?(??)
    end
  end
end
