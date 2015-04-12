# encoding: utf-8

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @count ||= begin
      words = @phrase.scan(/\w+/)
      ItemCounter.new(words).items_with_count
    end
  end
end

class ItemCounter
  def initialize(items)
    @items_with_count = Hash.new(0)
    @items = items
  end

  def items_with_count
    @items.each { |item| @items_with_count[item.downcase] += 1 }
    @items_with_count
  end
end
