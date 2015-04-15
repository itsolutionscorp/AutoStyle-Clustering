class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    @phrase = phrase    
    count_words
  end

  private
  def count_words
    @word_count = {}
    @phrase.downcase.scan(/\w+/).each do |word|
      @word_count[word] = 0 unless @word_count[word]
      @word_count[word] += 1
    end    
  end
  
end
