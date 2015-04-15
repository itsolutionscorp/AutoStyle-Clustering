class Anagram
  def initialize(base_word)
    @base_word = base_word.downcase
    @base_letter_sort = @base_word.chars.sort
  end

  def match(words_array)  
    words_array.select { |word| anagram?(word.downcase) } 
  end

  private

  def anagram?(word)
     identical_words?(word) ? false : same_letters?(word)  
  end

  def identical_words?(word)
    @base_word == word
  end

  def same_letters?(word)
    @base_letter_sort == word.chars.sort 
  end
end
