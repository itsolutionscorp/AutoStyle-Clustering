class Bob
  def hey(message)
    case
    when empty?(message)
      'Fine. Be that way!'
    when all_caps?(message)
      'Woah, chill out!'
    when question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty?(message)
    message.strip.empty?
  end

  def all_caps?(message)
    message.upcase.eql?(message)
  end

  def question?(message)
    message.end_with?('?')
  end
end
