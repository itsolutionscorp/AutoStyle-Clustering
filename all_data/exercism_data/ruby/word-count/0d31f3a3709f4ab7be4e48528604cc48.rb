class Phrase < Struct.new(:text)
  def word_count
    words.inject(empty_counts) do |counts, word|
      counts[word] += 1
      counts
    end
  end

  private

  def words
    text.split(/\W+/).map(&:downcase)
  end

  def empty_counts
    Hash.new(0)
  end
end
