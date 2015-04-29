class Bob

  def angry?(input)
    input.match(/\p{L}/) && input.upcase == input
  end

  def empty?(input)
    input.strip.empty? || input.strip.nil?
  end

  def question?(input)
    input.end_with? ('?')
  end

  def hey(words)
    if empty?(words)
      'Fine. Be that way!'
    elsif angry?(words)
      'Whoa, chill out!'
    elsif question?(words)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
