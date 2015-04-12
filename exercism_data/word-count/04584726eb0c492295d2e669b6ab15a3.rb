class Phrase

  def initialize(phrase)
      @phrase = phrase
  end

  def without_punctation
      @phrase.gsub /[^A-Za-z0-9\s]/, ' '
  end

  def word_count
    all_words = self.without_punctation.downcase.split 
    word_counts = Hash.new { |h,k| h[k] = 0 }
    all_words.each { |word| word_counts[word] += 1 }
    word_counts
  end

end
