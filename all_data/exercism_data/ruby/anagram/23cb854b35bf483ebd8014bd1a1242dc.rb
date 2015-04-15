class Anagram
  def initialize word
    @word = word
  end

  def match potential_anagrams
    potential_anagrams.select { |word| anagram?(word) }
  end

  private
  attr_reader :word

  def anagram?(potential_anagram)
    comparable_word.downcase == comparable(potential_anagram).downcase
  end

  def comparable_word
    @comparable_word ||= comparable(word)
  end

  def comparable potential_anagram
    potential_anagram.chars.sort { |a, b| a.downcase <=> b.downcase }.join
  end
end
