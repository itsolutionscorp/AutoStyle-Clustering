require 'delegate'

# Decorates String with conversation-relevant query methods
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

# Your basic semicommunicative teenager
class Bob
  def hey(message)
    respond_to ConversationalString.new(message)
  end

  def respond_to(message)
    case
    when message.shouting?
      "Woah, chill out!"
    when message.questioning?
      "Sure."
    when message.silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
