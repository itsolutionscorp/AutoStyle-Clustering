class Bob
  def initialize
  end

  def hey(message)
    case classify(message)
    when :silence
      "Fine. Be that way!"
    when :question
      "Sure."
    when :shouting
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def classify(message)
    if message.strip == ""
      :silence
    elsif message.upcase == message
      :shouting
    elsif message.end_with?("?")
      :question
    end
  end
end
