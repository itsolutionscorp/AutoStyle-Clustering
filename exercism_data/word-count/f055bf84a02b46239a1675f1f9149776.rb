class Phrase

  def initialize(phrase)
    @phrase = phrase
    @counts = {}
    @counted = false
  end

  def word_count
    unless @counted
      @words ||= get_words
      count_words
      @counted = true
    end
    @counts
  end

  private
  def get_words
    @phrase.downcase.scan /[[:word:]]+/
  end

  def count_words
    @words.each do |word|
      @counts.has_key?(word) ? @counts[word] += 1 : @counts[word] = 1
    end
  end

end
