class Anagram
attr_reader :word

  def initialize(word)
    @word = word
  end


  def match(set_of_anagrams)
    set_of_anagrams.select do |anagram|
      word.downcase != anagram.downcase && 
        normalize_letters(anagram) == 
        normalize_letters(word)
    end
  end

  def normalize_letters(string)
    string.downcase.split("").sort
  end
end
