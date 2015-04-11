class Anagram

  attr_accessor :word

  def initialize word
    @word = word
  end

  def match(possible_anagrams)
    alphagram = sort(@word.downcase)
    anagrams = possible_anagrams.select{|word| !duplicate?(word) && (sort(word.downcase) == alphagram)}
  end

  def sort(word)
    word.chars.sort.join
  end

  def duplicate?(word)
    word.downcase == @word.downcase
  end

end
