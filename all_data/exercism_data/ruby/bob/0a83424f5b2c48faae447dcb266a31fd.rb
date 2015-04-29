class Bob
  def hey(msg)
    case
    when empty?(msg)
      'Fine. Be that way!'
    when shouting?(msg)
      'Woah, chill out!'
    when question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty?(str)
    str.strip.empty?
  end

  def question?(str)
    str.end_with?('?')
  end

  def shouting?(str)
    str.upcase == str
  end
end
