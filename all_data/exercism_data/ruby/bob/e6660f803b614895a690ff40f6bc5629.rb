class Bob

  def hey(given_sentence)
    sentence = Sentence.new(given_sentence)

    return "Fine. Be that way!" if sentence.silent?
    return "Whatever." if sentence.statement?
    return "Woah, chill out!" if sentence.shout?
    return "Sure." if sentence.question?

  end

end

class Sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def statement?
    upper_lower_ending_with_dot_or_exclamation = /\A[A-Z][a-z].*[\.!]\z/
    @sentence.scan(upper_lower_ending_with_dot_or_exclamation).any?
  end

  def shout?
    @sentence == @sentence.upcase
  end

  def question?
    @sentence.include? "?"
  end

  def silent?
    @sentence.to_s.strip.empty?
  end

end
