class Bob
  # Primary method for interacting with outside world
  # People say `hey` to Bob and he replies in various ways
  def hey(speech)

    if says_nothing?(speech)
      "Fine. Be that way!"

    elsif yelling?(speech)
      "Woah, chill out!"

    elsif question?(speech)
      "Sure."

    else
      "Whatever."
    end

  end

  private
  # Methods to process type of speech
  def says_nothing?(speech)
    speech.strip.empty?
  end
  
  def yelling?(speech)
    speech == speech.upcase
  end
  
  def question?(speech)
    speech.strip[-1] == "?"
  end

end
