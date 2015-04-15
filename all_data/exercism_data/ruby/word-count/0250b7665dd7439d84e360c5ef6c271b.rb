class Phrase
  def initialize(word)
    @word = word.downcase
  end

  def word_count
    normalized_words.
      each.with_object(Hash.new(0)) do |word, count_hash|
      count_hash[word] += 1
    end
  end

  private

  def normalized_words
    @word.scan(/\w+/)
  end
end
