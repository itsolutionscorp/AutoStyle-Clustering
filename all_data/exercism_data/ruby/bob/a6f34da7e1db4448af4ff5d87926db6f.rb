class Utterance < String
  def initialize(string)
    super(string.to_s)
  end

  def shout?
    upcase == self
  end

  def question?
    end_with?("?")
  end

  def blank?
    empty?
  end
end

class Bob
  def hey(phrase)
    utterance = Utterance.new(phrase)
    if utterance.blank?
      "Fine. Be that way!"
    elsif utterance.shout?
      "Woah, chill out!"
    elsif utterance.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
