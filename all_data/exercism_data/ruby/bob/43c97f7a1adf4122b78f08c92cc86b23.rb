class Speech < String
  def yelling?
    self == upcase
  end

  def question?
    end_with? "?"
  end

  def silent?
    strip.empty?
  end
end

class Bob
  def hey input
    speech = Speech.new input
    return "Fine. Be that way!" if speech.silent?
    return "Woah, chill out!" if speech.yelling?
    return "Sure." if speech.question?
    "Whatever."
  end
end
