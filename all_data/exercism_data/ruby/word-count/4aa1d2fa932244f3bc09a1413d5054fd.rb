class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @count ||= words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private
  attr_reader :phrase

  def words
    phrase.downcase.scan(%r{\w+})
  end
end
