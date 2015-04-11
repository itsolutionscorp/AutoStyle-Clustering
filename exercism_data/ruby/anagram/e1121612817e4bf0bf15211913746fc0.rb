class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    letters = @word.chars.sort

    candidates.select do |word|
      word = word.downcase
      word != @word && word.chars.sort == letters
    end
  end

end
