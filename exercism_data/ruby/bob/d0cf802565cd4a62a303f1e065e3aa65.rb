class Bob
  def hey utterance
    sentence = Sentence.new utterance
    if sentence.shouting? then
      "Woah, chill out!"
    elsif sentence.question? then
      "Sure."
    elsif sentence.empty? then
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Sentence
  attr_accessor :raw_sentence

  def initialize( utterance )
    @raw_sentence = utterance
  end

  def shouting?
    if self.numeric_only?
      false
    else
      @raw_sentence == @raw_sentence.upcase
    end
  end

  def question?
    @raw_sentence[-1] == '?'
  end

  def numeric_only?
    !@raw_sentence.match /[a-zA-Z]/
  end

  def empty?
    spaceless_sentence = @raw_sentence.gsub /\s*/, ''
    spaceless_sentence.length == 0
  end
end
