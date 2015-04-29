class Bob

  def hey(input)
    case
    when say_nothing?(input)
      'Fine. Be that way!'
    when yelling?(input)
      'Woah, chill out!'
    when question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def say_nothing?(message)
    message.strip.empty?
  end

  def yelling?(message)
    message.eql?(message.upcase)
  end

  def question?(message)
    message.end_with?("?")
  end
end
