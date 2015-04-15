class Bob
  def hey(message)
    case
    when silence(message)
      'Fine. Be that way!'
    when shouting(message)
      'Woah, chill out!'
    when question(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence(message)
    /\A\s*\Z/.match(message)
  end

  def shouting(message)
    /\A[^a-z]*\Z/.match(message) and /[A-Z]/.match(message)
  end

  def question(message)
    /\A.*\?\Z/.match(message)
  end
end
