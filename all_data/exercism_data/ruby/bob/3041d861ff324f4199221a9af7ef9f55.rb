class Bob
  def hey(words)
    if words.strip.empty?
      'Fine. Be that way!'
    elsif yelling?(words)
      'Woah, chill out!'
    elsif words.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  
  def yelling?(words)
    words.upcase == words
  end
end
