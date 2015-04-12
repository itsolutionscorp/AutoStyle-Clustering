class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    count_words
  end

  private

  def words
    phrase.scan(%r{\w+})
  end

  def count_words
    words.each_with_object(Counter.new) do |word, counter|
      counter.count word
    end.data
  end

end

class Counter
  attr_reader :data

  def initialize
    @data = Hash.new(0)
  end

  def count(item)
    @data[item] += 1
  end
end
