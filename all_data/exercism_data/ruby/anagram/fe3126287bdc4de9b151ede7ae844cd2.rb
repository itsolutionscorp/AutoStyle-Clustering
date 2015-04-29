class WordComparison

  def initialize(word1, word2)
    @word1 = word1.downcase
    @word2 = word2.downcase

    @letters1 = @word1.split('')
    @letters2 = @word2.split('')
  end

  def is_anagram?
    same_length? and different? and has_same_letters?
  end

  def has_same_letters?
    same_letters = true
    @letters1.each do |w|
      if @letters2.count(w) > 0
        _remove_letter @letters2, w
      else
        same_letters = false
      end
    end
    same_letters
  end

  def different?
    @word1 != @word2
  end

  def same_length?
    @word1.size == @word2.size
  end

  private

  def _remove_letter(letters, letter)
    letters.slice! letters.index(letter), 1
  end

end

class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    matches = []
    words.each do |w|
      matches.push w if WordComparison.new(@word, w).is_anagram?
    end
    matches
  end
end
