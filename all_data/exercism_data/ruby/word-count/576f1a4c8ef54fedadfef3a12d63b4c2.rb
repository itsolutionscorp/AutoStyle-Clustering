class Phrase

  @phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    results = Hash.new
    words.each { |word| count_word(word, results) }

    results
  end

  private

  def words
    @phrase.downcase.gsub(/[^\w\s]/, ' ').squeeze(' ').split
  end

  def count_word(word, hash)
    hash[word] = 0 unless hash[word]
    hash[word] += 1
  end

end
