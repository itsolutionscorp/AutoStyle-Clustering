class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)
    if sentence.being_silent?
      "Fine. Be that way!"
    elsif sentence.shouting?
      "Woah, chill out!"
    elsif sentence.asking?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence
  end
  def being_silent?
    !@sentence || @sentence.empty?
  end

  def shouting?
    @sentence == @sentence.upcase
  end

  def asking?
    @sentence.end_with?("?")
  end
end
