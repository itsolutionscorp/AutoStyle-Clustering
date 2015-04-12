class Phrase < Struct.new(:text)
  def word_count
    counts = Hash.new(0)
    each_word { |w| counts[w] += 1 }
    counts
  end

  private
  def each_word(&block)
    text.downcase.scan(/\w+/, &block)
  end
end
