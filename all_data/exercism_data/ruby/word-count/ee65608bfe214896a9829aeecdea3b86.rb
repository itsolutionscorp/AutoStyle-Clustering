class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    if @counts.nil?
      count_words
    end
    @counts
  end

  private

  def count_words
    @counts = {}
    words.each do |word|
      increment_count_for word
    end
  end

  def increment_count_for word
    @counts[word] ||= 0
    @counts[word] += 1
  end

  def words
    @_words ||= @phrase.downcase.scan(/\w+/)
  end
end
