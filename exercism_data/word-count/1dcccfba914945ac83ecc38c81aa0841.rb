class Phrase
  attr_reader :word_count

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count()
    frequencies to_words
  end

  private

  def frequencies(list)
    list.each_with_object(Hash.new(0)) { |i, freq| freq[i] += 1 }
  end

  def to_words()
    @sentence.downcase().scan(%r/\w+/)
  end
end
