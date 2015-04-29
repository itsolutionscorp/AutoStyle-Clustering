class Bob
  def hey utterance
    return "Fine. Be that way!" if silence? utterance
    return "Woah, chill out!"   if shout? utterance
    return "Sure."              if question? utterance
    "Whatever."
  end

  private

  def shout? utterance
    utterance == utterance.upcase
  end

  def silence? utterance
    utterance.strip.empty?
  end

  def question? utterance
    utterance.end_with? "?"
  end
end
  
