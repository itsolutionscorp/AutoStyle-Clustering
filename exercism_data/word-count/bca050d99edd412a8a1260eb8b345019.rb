class Phrase

  def initialize(words)
    @phrase = words.scan(/\w+/)
  end

  def normalize_words
    @phrase.each { |word| word.downcase! }
  end

  def word_count
    word_count = Hash.new(0)
    normalize_words

    @phrase.each { |word| word_count[word] += 1 }
    word_count
  end
end
