class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    anagrams = []
    words.each do |word|
      if word.downcase != @word.downcase
        if word.length == @word.length
          if word.downcase.split('').sort == @word.downcase.split('').sort
            anagrams << word 
          end
        end
      end
    end
    anagrams
  end
end
