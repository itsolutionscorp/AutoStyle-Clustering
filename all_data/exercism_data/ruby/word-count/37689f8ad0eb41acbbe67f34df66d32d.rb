class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= counts_for words_in cleaned_up @phrase
  end

  private

  WORD_SEPARATORS = ' ,'

  def cleaned_up phrase
    remove_punctuation_from normalized phrase
  end

  def normalized phrase
    phrase.downcase
  end

  def remove_punctuation_from phrase
    phrase.gsub(/[^a-z0-9#{WORD_SEPARATORS}]/, '')
  end

  def words_in phrase
    phrase.split(/[#{WORD_SEPARATORS}]+/)
  end

  def counts_for words
    counts = words.group_by {|word| word}
    counts.each {|word, occurrences| counts[word] = occurrences.length}
    counts
  end

end
