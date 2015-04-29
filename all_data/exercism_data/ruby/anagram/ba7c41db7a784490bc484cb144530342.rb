class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    possible_anagrams(word_list)
  end


  private 

  def possible_anagrams(list)
    list.select do |item|
      anagram?(item.downcase) 
    end
  end

  def anagram?(candidate)
    sort_chars(@word) == sort_chars(candidate) unless @word == candidate
  end

  def sort_chars(word)
    word.chars.sort.join 
  end
end
