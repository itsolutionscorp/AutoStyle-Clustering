class Bob
  def hey(words)
    if said_nothing? words
      'Fine. Be that way!'
    elsif yelling? words
      'Woah, chill out!'
    elsif question? words
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  
  def yelling?(words)
    words.upcase == words
  end

  def said_nothing?(words)
    words.strip.empty?
  end

  def question?(words)
    words.end_with? '?'
  end
end
