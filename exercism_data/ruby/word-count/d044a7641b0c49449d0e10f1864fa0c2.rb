class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(words)
  end

  private

  def words
    @phrase.scan(/[\w']+/)
  end

 
  def count(words)
    word_counts = Hash.new(0)
    words.each { |word| word_counts[word.downcase] += 1 }
    word_counts
  end

end
