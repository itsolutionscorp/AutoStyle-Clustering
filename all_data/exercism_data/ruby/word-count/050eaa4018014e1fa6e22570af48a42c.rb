class Phrase < String
  def word_count
    counts = Hash.new(0)

    words.each { |word| counts[word] += 1 }

    counts
  end

  def words
    downcase.split(/\W+/)
  end
end
