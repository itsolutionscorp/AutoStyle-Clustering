class Anagram

  def initialize(word)
    @word = word
  end

  def match(anagrams)
    matches = []
    anagrams.each do |word|
      if word.downcase.split("").sort == @word.downcase.split("").sort && word.downcase != @word.downcase
        matches << word
      end
    end
    matches
  end
end
