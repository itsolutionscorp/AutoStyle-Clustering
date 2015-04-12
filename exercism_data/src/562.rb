class Phrase
  
  
  def initialize word
    @word = word
  end

  def word_count
    words = Hash.new(0)

    @word.gsub! /[^a-zA-Z0-9_']/, " "
    @word.downcase.split.each{|w| words[w] += 1}
    
    return words

  end
end
