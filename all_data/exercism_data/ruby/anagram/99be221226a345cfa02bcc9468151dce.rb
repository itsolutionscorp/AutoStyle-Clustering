class Anagram
  def initialize(word)
    @word = word
  end

  def match(arr)
    anagrams = []
    sorted_word = @word.downcase.split("").sort
    arr.each do |el|
      if el.downcase != @word.downcase
        sorted_el = el.downcase.split("").sort
        anagrams << el if sorted_el == sorted_word
      end
    end

    anagrams
  end
end
