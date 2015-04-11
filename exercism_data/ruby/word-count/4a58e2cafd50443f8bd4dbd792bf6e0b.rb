class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @_word_count ||= words.each_with_object({}) do |word, count|
      count[word] ||= 0
      count[word] += 1
    end
  end

  private

  def words
    @_words ||= @phrase.downcase.scan(/\w+/)
  end
end
