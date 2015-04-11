class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)

    if sentence.saying_nothing?
      "Fine. Be that way."
    elsif sentence.asking?
      "Sure."
    elsif sentence.yelling?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def asking?
    @sentence.end_with?("?")
  end

  def yelling?
    @sentence.upcase == @sentence
  end

  def saying_nothing?
    @sentence.empty?
  end
end
