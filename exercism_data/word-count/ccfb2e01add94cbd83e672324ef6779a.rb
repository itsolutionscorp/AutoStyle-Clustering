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
    counts = words.group_by {|word| word}
    counts.each {|word, occurrences| counts[word] = occurrences.length}
    counts
  end

end
