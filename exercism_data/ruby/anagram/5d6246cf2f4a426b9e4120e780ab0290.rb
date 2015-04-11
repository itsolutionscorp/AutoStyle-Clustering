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
      anagram?(item) 
    end
  end

  def anagram?(candidate)
    unless @word == candidate.downcase
      sort_chars(@word) == sort_chars(candidate)
      # I know it could be a one-liner,
      # but I have a strict '80-chars per line' rule ;)
    end
  end

  def sort_chars(word)
    word.downcase.chars.sort.join 
  end
end
