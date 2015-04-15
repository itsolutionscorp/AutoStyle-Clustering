class Bob
  def hey(phrase)
    if isYelling(phrase)
      "Woah, chill out!"
    elsif isQuestion(phrase)
      "Sure."
    elsif isNotSayingAnything(phrase)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  # Yelling is all in upper case
  # Note: all numbers does not constitute yelling - changing numbers to lowercase has no effect 
  def isYelling(phrase)
    phrase.upcase.eql? phrase and phrase.upcase != phrase.downcase
  end

  # A question ends with ?
  def isQuestion(phrase)
    phrase.last.eql?("?")
  end

  # Not saying anything
  def isNotSayingAnything(phrase)
    phrase.eql? "" or phrase.match("^(\s)+$")
  end
end

class String
  def last
    self[-1,1]
  end
end
