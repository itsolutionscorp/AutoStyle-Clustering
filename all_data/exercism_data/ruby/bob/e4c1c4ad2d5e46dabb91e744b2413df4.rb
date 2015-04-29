module Expression
  def yelled?
    @sentence.upcase == @sentence
  end

  def empty?
    @sentence.strip.empty?
  end

  def question?
    @sentence.end_with? "?"
  end
end

class Bob
  include Expression
  def hey(sentence)
    @sentence = sentence
    return "Fine. Be that way!" if empty?
    return "Woah, chill out!" if yelled?
    return "Sure." if question?
    "Whatever."
  end
end
