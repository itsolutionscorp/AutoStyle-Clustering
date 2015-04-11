class Bob
  def hey(words)
    if words =~ /\A\s*\Z/
      return fine
    end

    if words.upcase == words
      return chill
    end

    if words[-1] == '?'
      return sure
    end

    whatever
  end

  private
  def sure
    'Sure.'
  end

  def chill
    'Woah, chill out!'
  end

  def fine
    'Fine. Be that way!'
  end

  def whatever
    'Whatever.'
  end
end
