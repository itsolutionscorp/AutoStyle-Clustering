class Phrase < Struct.new(:text)
  def word_count
    counts = Hash.new(0)
    words.each { |word| counts[word] += 1 }
    counts
  end

  private

  def words
    text.downcase.scan(/['\w]+/)
  end
end
