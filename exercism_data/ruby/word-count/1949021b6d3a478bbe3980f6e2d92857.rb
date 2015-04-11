class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    if @counts.nil?
      count!
    end
    @counts
  end

  private

  def count!
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
    @_words ||= @phrase.downcase.split(/[ ,:!\$@&%\^]+/)
  end
end
