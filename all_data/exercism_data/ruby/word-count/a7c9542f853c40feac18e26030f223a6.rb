class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_words(split_phrase_into_words)
  end

  private

  def split_phrase_into_words
    @phrase.scan(/\w+/)
  end

  def count_words(words)
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word.downcase] += 1 }
  end
end
