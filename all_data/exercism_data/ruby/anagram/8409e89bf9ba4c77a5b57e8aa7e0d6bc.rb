class Anagram
  def initialize (word)
    @word = word
  end
  def match(candidates)
    anagrams = []
    candidates.each do |word|
      if word.downcase != @word
        if word.downcase.split('').sort == @word.downcase.split('').sort
          anagrams << word
        end
      end
    end
    anagrams
  end

end
