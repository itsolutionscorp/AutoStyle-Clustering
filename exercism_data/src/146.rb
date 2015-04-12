class Phrase
  
  
  def initialize word
    @word = word
  end

  def word_count

    @word.gsub! /[^a-zA-Z0-9_']/, " "
    @word.downcase.split.each_with_object(Hash.new(0)){|w, words| words[w] += 1}
    

  end
end
