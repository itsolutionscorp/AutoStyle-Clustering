class Phrase
  def initialize(text)
    @text = text.to_s
  end

  def word_count
    Histogram.new(words).to_hash
  end

  private

  def words
    @text.downcase.scan(/\w+/)
  end
end

class Histogram
  def initialize(items=[])
    @counts = items.each_with_object(Hash.new(0)) {|item, counts| counts[item] += 1}
  end

  def to_hash
    @counts.dup
  end
end
