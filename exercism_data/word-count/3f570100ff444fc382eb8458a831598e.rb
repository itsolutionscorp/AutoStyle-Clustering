class Phrase < Struct.new(:text)

  def word_count
    counts = {}
    counts.default = 0
    words.each do |word|
      counts[word] += 1
    end
    counts
  end

  private

  def words
    text.downcase.scan /\w+/
  end
end
