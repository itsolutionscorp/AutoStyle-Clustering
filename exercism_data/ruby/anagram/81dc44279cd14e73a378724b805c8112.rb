class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    letters = @word.downcase.chars.sort

    candidates.select do |word|
      word = word.downcase
      word != @word && word.chars.sort == letters
    end
  end

end
