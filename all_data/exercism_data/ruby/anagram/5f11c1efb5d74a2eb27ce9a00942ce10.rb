class Anagram

  def initialize(anagram)
    @anagram = anagram
  end

  def match(words)
    matching_words = []

    words.each do |word|
      matching_words << word if anagram?(word) && not_identical(word) 
    end
    
    matching_words
  end

  def anagram?(word)
    word.downcase.chars.sort.join == @anagram.downcase.chars.sort.join
  end

  def not_identical(word)
    word.downcase != @anagram.downcase
  end
end
