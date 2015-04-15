class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(anagrams)
    letters = @word.split('').sort

    anagrams.select do |word|
      word = word.downcase
      word != @word && word.split('').sort == letters
    end
  end

end
