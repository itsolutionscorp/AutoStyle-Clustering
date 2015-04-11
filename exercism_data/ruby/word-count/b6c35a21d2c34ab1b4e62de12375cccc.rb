class Phrase
  attr_reader :word_count
  def initialize(words)
    @word_count = count words
  end

  def count words
    words = words.downcase.gsub(/[^a-z0-9'\s]/i,' ').split
    words.uniq.each_with_object(tally = {}) do |word, words_hash|
      words_hash[word] = words.count(word)
    end
  end
end
