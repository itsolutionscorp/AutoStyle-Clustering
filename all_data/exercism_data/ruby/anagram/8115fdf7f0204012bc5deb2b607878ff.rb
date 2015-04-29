require 'pry'

class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(candidates)
    anagram = @anagram.split('').map{|letter| letter.downcase}
    anagrams = []
    nested_array = candidates.each do |word|
      letters = word.downcase.split('')
      if anagram != letters && letters.count == anagram.count && (letters-anagram).count == 0 && (anagram-letters).count == 0
          anagrams << word
      end
    end
    anagrams
  end
end
