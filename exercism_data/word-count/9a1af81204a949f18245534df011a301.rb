class Phrase

  attr_reader :words
  
  def initialize(words)
    @words = words
  end

  def word_count
    table = Hash.new(0) 
    split_words.each {|w| table[w]+=1}
    table
  end

  private

  def split_words
    words.downcase.scan(/[\w']+/)
  end

end
