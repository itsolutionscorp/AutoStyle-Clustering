Anagram = Struct.new(:word) do

  def match(word_list)
    word_list.select do|w| 
     anagram?(w)
    end
  end
  
  private

  def alphabetize(word)
    word.downcase.chars.sort
  end

  def anagram?(w)
    unless  word.downcase  ==  w.downcase
      alphabetize(word) == alphabetize(w)
    end
  end
end
