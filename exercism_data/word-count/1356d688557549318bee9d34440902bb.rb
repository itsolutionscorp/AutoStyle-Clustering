class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    frequencies(to_words)
  end

  private

  def frequencies(words)
    words.each_with_object(Hash.new(0)) do |word, frequency|
      frequency[word] += 1
    end
  end

  def to_words
    @sentence.downcase().scan(%r/\w+/)
  end
end
