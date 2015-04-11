class Anagram
  def initialize(word)
    @word = word.downcase
    @normalized = normalize(@word)
  end

  def match(possible_anagrams)
    possible_anagrams.select { |w|
      w = w.downcase
      same_letters?(w) && ! same_word?(w)
    }
  end

  private

  def same_word?(w)
    w == @word
  end

  def same_letters?(w)
    normalize(w) == @normalized
  end

  def normalize(word)
    word.chars.sort.join
  end
end
