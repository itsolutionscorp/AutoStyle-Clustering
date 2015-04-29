class Phrase < Struct.new(:source)
  def word_count
    words.reduce(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  private

  def words
    normalized_source.scan(/\w+/)
  end

  def normalized_source
    source.downcase
  end
end
