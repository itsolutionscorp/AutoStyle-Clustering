class Anagram
  attr_reader :characters

  def initialize(word)
    @characters = alphabetize(word)
  end

  def match(words)
    words.select do |word|
      word if anagram?(word)
    end
  end

  def anagram?(word)
    characters == alphabetize(word)
  end

  def alphabetize(word)
    word.chars.sort.join("")
  end
end
