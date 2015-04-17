class Phrase
  def initialize expression
    @expression = expression
  end
  
  def word_count
    Histogram.new(parse_for_words).count
  end
  
  private
  def parse_for_words
    @expression.downcase.scan(/\w+/)
  end
end

class Histogram
  def initialize items
    @items = items
  end
  
  def count
    @items.each_with_object(Hash.new(0)) { |item, item_counts| item_counts[item] += 1}
  end
end