class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @counts ||= count_words
  end

  private

  def count_words
    words.each_with_object({}) do |word, count|
      count[word] ||= 0
      count[word] += 1
    end
  end

  def words
    @_words ||= @phrase.downcase.scan(/\w+/)
  end
end
