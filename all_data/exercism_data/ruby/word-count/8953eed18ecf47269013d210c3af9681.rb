class Phrase
  def initialize phrase
    @phrase = phrase.downcase
    @counts = {}
  end

  def word_count
    reset_counts
    @phrase.scan(word_pattern).each do |word|
      @counts[word] += 1
    end
    @counts
  end

  private

  def reset_counts
    @phrase.scan(word_pattern).each do |word|
      @counts[word] = 0
    end
  end

  def word_pattern
    /\b\w+\b/
  end
end
