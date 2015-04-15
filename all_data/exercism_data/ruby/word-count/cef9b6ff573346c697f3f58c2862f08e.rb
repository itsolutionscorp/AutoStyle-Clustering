class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words.counted
  end

  private

  def words
    @phrase.scan(%r{\w+})
  end
end

module Enumerable
  def counted
    each_with_object(Hash.new(0)) do |value,hash|
      hash[value] += 1
    end
  end
end
