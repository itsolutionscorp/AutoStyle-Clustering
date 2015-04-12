class Phrase

  def initialize(phrase)
      @phrase = phrase
  end

  def word_count
    all_words = @phrase.downcase.scan(/\w+/)
    word_counts = Hash.new(0)
    all_words.each { |word| word_counts[word] += 1 }
    word_counts
  end

end
