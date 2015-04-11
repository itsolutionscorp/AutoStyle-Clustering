class Bob
  # Methods to process type of speech
  def saysNothing(speech)
    speech.strip == ""
  end
  
  def isYelling(speech)
    speech == speech.upcase
  end
  
  def isQuestion(speech)
    speech.strip[-1] == "?"
  end

  # Primary method for interacting with outside world
  # People say `hey` to Bob and he replies in various ways
  def hey(speech)

    if saysNothing(speech) then
      "Fine. Be that way!"

    elsif isYelling(speech) then
      "Woah, chill out!"

    elsif isQuestion(speech) then
      "Sure."

    else
      "Whatever."
    end

  end
end
