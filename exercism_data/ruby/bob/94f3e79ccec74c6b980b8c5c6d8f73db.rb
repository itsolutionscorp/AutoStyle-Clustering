class Bob
  def hey message
    case Listen.to message
    when :silence
      "Fine. Be that way."
    when :shouting
      "Woah, chill out!"
    when :asking
      "Sure."
    else
      "Whatever."
    end
  end
end

class Listen < Struct.new :message
  def self.to message
    new(message).interpret
  end

  def interpret
    [:silence, :shouting, :asking].each do |inflection|
      return inflection if send "#{inflection}?"
    end
    return :telling
  end

  def shouting?
    message == message.upcase
  end

  def asking?
    message.end_with? "?"
  end

  def silence?
    message.nil? || message.empty?
  end
end
