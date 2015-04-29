class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  private

  attr_reader :phrase

  def words
    phrase.downcase.scan(/\w+/)
  end

end
