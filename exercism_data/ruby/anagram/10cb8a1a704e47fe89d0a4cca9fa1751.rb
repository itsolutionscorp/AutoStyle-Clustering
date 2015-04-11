class Anagram < String
  def match(words)
    words.select do |word|
      anagram?(word)
    end
  end

  private

  def anagram?(word)
    chars.sort == word.chars.sort
  end
end
