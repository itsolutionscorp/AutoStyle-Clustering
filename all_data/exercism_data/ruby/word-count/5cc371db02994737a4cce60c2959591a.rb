class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(initial_words_hash).each { |word, hash| hash[word] += 1 }
  end

  private
  def words
    @phrase.downcase.scan(/\w+/)
  end

  def initial_words_hash
    Hash.new(0)
  end
end
