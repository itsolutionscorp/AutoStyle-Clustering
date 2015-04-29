class Phrase < Struct.new(:text)

  def word_count
    words.each_with_object(CountingSet.new) do |word, set|
      set << word
    end.to_hash
  end

private
  def words
    text.downcase.scan(/\w+/)
  end

end

class CountingSet

  def initialize
    @item_count = Hash.new(0)
  end
  def <<(item)
    @item_count[item] += 1
  end
  def to_hash
    @item_count.dup
  end

end
