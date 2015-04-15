class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    Counter.occurence_of words
  end

  private

  def words
    @phrase.scan(%r{\w+})
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

  def self.occurence_of(countable_data)
    countable_data.each_with_object(new) do |occurence, counter|
      counter.count occurence
    end.data
  end
end
