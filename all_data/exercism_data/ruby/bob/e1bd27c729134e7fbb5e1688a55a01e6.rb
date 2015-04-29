class Bob
  def hey(words)
    if silent?(words)
      'Fine. Be that way!'
    elsif yelling?(words)
      'Woah, chill out!'
    elsif asking?(words)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent?(words)
    words.strip.empty?
  end

  def asking?(words)
    words.end_with?('?')
  end

  def yelling?(words)
    words.match(/[a-zA-Z]/) && words == words.upcase
  end
end
