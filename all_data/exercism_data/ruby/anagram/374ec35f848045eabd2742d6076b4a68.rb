class Anagram

  def initialize(word)
    @word = word
  end

  def match(dif_words) dif_words.select{|w| is_anagram?(w)} end


  def is_anagram?(dif_word) 
     (sorted == Anagram.new(dif_word).sorted) and !same?(dif_word)
  end
 
  def sorted
    @word.downcase.chars.sort
  end
 
  private
  def same?(dif_word) @word.downcase == dif_word.downcase end
end
