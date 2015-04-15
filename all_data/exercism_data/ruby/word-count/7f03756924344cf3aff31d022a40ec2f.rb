class Phrase < String
  def word_count
    words  = self.split(/\W+/).map(&:downcase)
    counts = Hash.new(0)

    words.each { |word| counts[word] += 1 }

    counts
  end
end
