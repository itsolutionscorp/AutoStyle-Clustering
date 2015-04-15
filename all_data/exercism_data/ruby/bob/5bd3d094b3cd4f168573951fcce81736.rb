class Bob

  def hey(words)
    if silence?(words)
      "Fine, be that way."
    elsif shout?(words)
      "Woah, chill out!"
    elsif question?(words)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(words)
    words.empty?
  end

  def shout?(words)
    words == words.upcase
  end

  def question?(words)
    words.end_with?('?')
  end

end
