class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= counts_of words_in normalized @phrase
  end

  private

  def normalized phrase
    phrase.downcase
  end

  def words_in phrase
    phrase.scan(/[a-z0-9]+/)
  end

  def counts_of words
    counts = Hash.new(0)
    words.each {|word| counts[word] += 1 }
    counts
  end

end
