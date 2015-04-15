class Phrase
  def initialize(text)
    @text = text.to_s
  end

  def word_count
    Histogram::create_histogram(words)
  end

  private

  def words
    @text.downcase.scan(/\w+/)
  end
end

module Histogram
  def self.create_histogram(items)
    items.each_with_object(Hash.new(0)) do |item, counts|
      counts[item] += 1
    end
  end
end
