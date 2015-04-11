class Phrase

  def initialize(phrase)
      @phrase = phrase
  end

  def word_count
    word_counts = Hash.new(0)
    all_words.each { |word| word_counts[word] += 1 }
    word_counts
  end

  private
    def all_words
      @phrase.downcase.scan(/\w+/)
    end

end
