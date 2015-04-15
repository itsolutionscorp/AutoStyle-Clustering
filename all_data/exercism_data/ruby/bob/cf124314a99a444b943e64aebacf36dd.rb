class Bob

  def hey(words)
    @words = words
    if silence?
      "Fine, be that way."
    elsif shout?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?
    @words.empty?
  end

  def shout?
    @words == @words.upcase
  end

  def question?
    @words.end_with?('?')
  end

end
