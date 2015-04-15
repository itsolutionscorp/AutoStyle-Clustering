class Anagram
  def initialize(word)
    self.word = word
    self.letters = word.downcase.split('')
  end

  def match(potential_anagrams)
    potential_anagrams.select { |potential_anagram| anagram?(potential_anagram) }
  end

  private

  def anagram?(item)
    item_letters = item.downcase.split('')
    contain_same_letters?(item_letters) && not_same_word?(item_letters)
  end

  def not_same_word?(potential_anagram_letters)
    potential_anagram_letters != letters
  end

  def contain_same_letters?(potential_anagram_letters)
    potential_anagram_letters.sort == letters.sort
  end

  attr_accessor :word
  attr_accessor :letters
end
