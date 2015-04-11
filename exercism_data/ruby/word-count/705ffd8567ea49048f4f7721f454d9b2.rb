class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words.count_by(&:self)
  end

  private

  def words
    @phrase.scan(%r{\w+})
  end

end

class Object
  def self
    self
  end
end

module Enumerable
  def count_by &block
    each_with_object(Hash.new(0)) do |value,hash|
      value = block.(value)
      hash[value] += 1
    end
  end
end
