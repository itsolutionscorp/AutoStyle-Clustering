class Bob
  def hey(_utterance)
    utterance = Utterance.new(_utterance)

    if utterance.shouting?
      "Woah, chill out!"
    elsif utterance.silent?
      "Fine. Be that way!"
    elsif utterance.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Utterance
  def initialize(utterance)
    @utterance = utterance
  end

  def shouting?
    @utterance.upcase == @utterance &&
      @utterance.downcase != @utterance
  end

  def silent?
    @utterance.strip == ""
  end

  def question?
    @utterance.end_with? "?"
  end
end
