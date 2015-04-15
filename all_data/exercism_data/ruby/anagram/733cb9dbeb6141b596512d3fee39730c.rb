class Anagram
  def initialize word
    @word = word
  end

  def match potential_anagrams
    potential_anagrams.select { |potential_anagram| anagram?(potential_anagram) }
  end

  private
  attr_reader :word

  def anagram?(potential_anagram)
    comparable_word[/\A#{comparable(potential_anagram)}\z/i]
  end

  def comparable_word
    @comparable_word ||= comparable(word)
  end

  def comparable potential_anagram
    potential_anagram.downcase.chars.sort.join
  end
end
