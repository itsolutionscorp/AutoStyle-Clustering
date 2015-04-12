class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    count(split(sentence))
  end

  private

  def split(sentence)
    sentence.split(/[^[:alpha:]\d']+/)
  end

  def count(words)
    words.each_with_object(Hash.new(0)) do
      |word, counts| counts[word.downcase] += 1
    end
  end

  attr_reader :sentence
end
