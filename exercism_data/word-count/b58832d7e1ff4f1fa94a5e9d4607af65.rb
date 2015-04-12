class Phrase
  DEFAULT_WORD_COUNT = 0

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(initial_words_hash).each { |word, hash| hash[word] += 1 }
  end

  private
  def words
    @phrase.downcase.gsub(/[^a-z0-9]/, ' ').split
  end

  def initial_words_hash
    Hash.new(DEFAULT_WORD_COUNT)
  end
end
