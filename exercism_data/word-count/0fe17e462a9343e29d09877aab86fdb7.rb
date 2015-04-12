class Phrase

  def initialize(phrase)
    @words_hash = Hash.new(0)
    count_words_in(phrase)
  end

  def word_count
    @words_hash
  end

  private

  def count_words_in(phrase, words = %r{[a-zA-Z0-9]+})
    phrase.scan(words).each do |word|
      @words_hash[word.downcase] += 1
    end
  end

end
