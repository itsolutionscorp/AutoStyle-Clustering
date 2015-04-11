class ShoutResponse
  def matches?(message)
    has_letters?(message) && message == message.upcase
  end

  def response
    "Woah, chill out!"
  end

  private

  def has_letters?(message)
    message =~ /[[:alpha:]]/
  end
end

class QuestionResponse
  def matches?(message)
    message.end_with?("?")
  end

  def response
    "Sure."
  end
end

class SilentResponse
  def matches?(message)
    message.strip.length.zero?
  end

  def response
    "Fine. Be that way!"
  end
end

class DefaultResponse
  def matches?(message)
    true
  end

  def response
    "Whatever."
  end
end

# Your basic semicommunicative teenager
class Bob
  @@responses = [ShoutResponse, QuestionResponse, SilentResponse, DefaultResponse].map(&:new)

  def hey(message)
    @@responses.detect {|response| response.matches? message }.response
  end
end
