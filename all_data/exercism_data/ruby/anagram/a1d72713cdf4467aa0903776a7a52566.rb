class Anagram

  def initialize(dictionary)
    @dictionary = dictionary.downcase
  end	

  def match(words) 
		words.each_with_object(Array.new(0)) do |word , matches|         
      if matches_dictionary?(word) 
        matches << word
      end  
    end  
  end

  private
  
  def sort_word(word)
    word.chars.sort.join
  end 

  def matches_dictionary?(word)
    sort_word(word) == sort_word(@dictionary) && word != @dictionary
  end 
  
end	
