class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def words
    @phrase.scan(/\w+/).map(&:downcase)
  end
  
  def word_count
    words.each_with_object(Hash.new(0)) do |word, frequencies|
      frequencies[word] += 1
    end
  end
end
