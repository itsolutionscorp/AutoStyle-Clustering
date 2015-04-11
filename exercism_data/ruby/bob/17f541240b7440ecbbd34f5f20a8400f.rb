class Bob
  def hey(mouthnoises)
    utterance = Utterance.new(mouthnoises.to_s)
    if utterance.silent?
      "Fine. Be that way!"
    elsif utterance.loud?
      "Woah, chill out!"
    elsif utterance.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Utterance < String
  def silent?
    empty?
  end

  def loud?
    upcase == self
  end

  def question?
    chars.last == "?"
  end
end
