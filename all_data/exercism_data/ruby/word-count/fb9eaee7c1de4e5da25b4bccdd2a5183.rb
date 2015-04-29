class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @phrase = phrase
    @word_count = {}
    count_occurrences
  end

  private

  def count_occurrences    
    words.uniq.each do |unique_word|
      occurrences = words.count { |word| unique_word == word }
      @word_count[unique_word] = occurrences
    end
  end

  def words
    @phrase.split(/[^\w']+/).map { |word| word.downcase }
  end

end
