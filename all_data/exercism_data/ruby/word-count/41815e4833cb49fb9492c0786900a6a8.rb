class Phrase < String

  def word_count
    words.each_with_object(Hash.new(0)) { |word, word_occurrences| word_occurrences[word] += 1 }
  end
  
  private
    def words
      # "Dinosaur" is the same word as "dinoSAUR", so downcase the whole phrase.
      self.downcase.scan(/\w+/)
    end
end
