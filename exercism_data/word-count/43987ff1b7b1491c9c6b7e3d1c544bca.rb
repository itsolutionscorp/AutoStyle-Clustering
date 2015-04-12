class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  def words
  	@phrase.downcase.scan(/\w+/)
  end
  
end
