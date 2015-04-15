class Phrase
  def initialize(string)
    @histogram = Histogram.new(sanitize(string).split)
  end

  def word_count
    @histogram.to_hash
  end

  private

  def sanitize(string)
    string.gsub(/\W/, ' ').downcase
  end
end

class Histogram
  def initialize(items=[])
    @counts = Hash.new(0)
    count_items(items)
  end

  def count_items(items)
    items.each &method(:count_item)
  end

  def count_item(item)
    @counts[item] += 1
  end

  def to_hash
    @counts
  end
end
