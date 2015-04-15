class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokenized_words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word] += 1
    end
  end

  private

  def tokenized_words
    phrase.downcase.scan(/\w+/)
  end
end
