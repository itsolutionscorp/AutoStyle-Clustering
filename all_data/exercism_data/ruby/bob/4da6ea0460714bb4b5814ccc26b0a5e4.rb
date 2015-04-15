require 'delegate'

class ConversationalString < SimpleDelegator
  def shouting?
    has_letters? && self == upcase
  end

  def questioning?
    end_with?("?")
  end

  def silent?
    strip.length.zero?
  end

  private

  def has_letters?
    self =~ /[[:alpha:]]/
  end
end

class ShoutResponse
  def matches?(message)
    message.shouting?
  end

  def response
    "Woah, chill out!"
  end
end

class QuestionResponse
  def matches?(message)
    message.questioning?
  end

  def response
    "Sure."
  end
end

class SilentResponse
  def matches?(message)
    message.silent?
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
  def responses
    @responses ||= [ShoutResponse, QuestionResponse, SilentResponse, DefaultResponse].map(&:new)
  end

  def hey(message)
    message = ConversationalString.new(message)
    responses.detect {|response| response.matches? message }.response
  end
end
