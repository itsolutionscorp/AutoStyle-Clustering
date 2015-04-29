class Bob
  def hey(words)
    case
    when silent?(words)
      'Fine. Be that way!'
    when shouting?(words)
      'Woah, chill out!'
    when question?(words)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(words)
    words.to_s.empty?
  end

  def question?(words)
    words.end_with? '?'
  end

  def shouting?(words)
    words == words.upcase
  end
end
