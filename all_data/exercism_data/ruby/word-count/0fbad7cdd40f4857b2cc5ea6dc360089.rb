class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new { |h,k| h[k] = 0 }) do |word, counter|
      counter[word] += 1
    end
  end

  private

  attr_reader :phrase

  def words
    normalized_phrase.scan(/\w+/)
  end

  def normalized_phrase
    phrase.downcase
  end

end
