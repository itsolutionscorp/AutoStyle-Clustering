class Phrase
  def initialize(phrase)
  	@phrase = phrase
  end

  def word_count
  	words_count = Hash.new(0) 
    words.map { |word| words_count[word] += 1 }
  	words_count
  end

  private

  def words
    @phrase.downcase.gsub(/[^a-z0-9'\s]/, ' ').split(' ')
  end
end
