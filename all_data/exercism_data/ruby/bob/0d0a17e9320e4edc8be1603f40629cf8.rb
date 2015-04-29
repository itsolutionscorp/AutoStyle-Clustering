class Bob
  def hey sentence
    case sentence.strip[-1]
    when '?'
      'Sure.'
    when '!'
      'Woah, chill out!'
    when nil
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
