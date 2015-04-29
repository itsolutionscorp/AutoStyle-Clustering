class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence || "")
    if sentence.mute?
      "Fine. Be that way!"
    elsif sentence.offensive?
      "Woah, chill out!"
    elsif sentence.curious?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Sentence < String
  alias :mute? :empty?

  def offensive?
    self == upcase
  end

  def curious?
    end_with? "?"
  end
end
