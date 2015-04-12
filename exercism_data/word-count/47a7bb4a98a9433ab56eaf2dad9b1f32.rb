class Phrase
  attr_accessor :word_count
  
  def initialize(phrase)
    @phrase = phrase
    clean_phrase
    count_words
  end

  private
  def clean_phrase
    @phrase.downcase!
    @phrase.gsub!(/[^\w\s]/, ' ')
  end

  def count_words
    @word_count = {}
    @phrase.split.each do |word|
      @word_count[word] = 0 unless @word_count[word]
      @word_count[word] += 1
    end    
  end

end
