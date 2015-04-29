class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(anagrams)
    sorted_word = sort(word)

    anagrams
      .reject{ |a| a.downcase == word.downcase }
      .select{ |a| sort(a) == sorted_word }
  end

  private
  
  def sort(word_to_sort)
    word_to_sort.downcase.chars.sort.join
  end
end
