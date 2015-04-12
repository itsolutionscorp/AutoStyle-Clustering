class Phrase
  
  attr_reader :words
  
  def initialize(phrase)
    @words = find_words_in(phrase)
  end
  
  def word_count
    @words = downcase_words_in(@words)    
    count_words_in(@words)
  end

  private
  
  def find_words_in(phrase)
    phrase.split(/\W+/)
  end
  
  def downcase_words_in(array)
    array.collect { |word| word.downcase}
  end
  
  def count_words_in(array)
    count = Hash.new(0)
    array.each { |word| count[word] += 1 }
    count
  end
  
end
