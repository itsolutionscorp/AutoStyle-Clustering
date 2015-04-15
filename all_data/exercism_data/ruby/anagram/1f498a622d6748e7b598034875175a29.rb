class Anagram
  def initialize(word)
    @word = word
  end

  def match(potential_anagrams)
    potential_anagrams.select { |potential_anagram| anagram?(potential_anagram) }
  end

  def anagram?(item)
    item_letters = item.downcase.split('')
    contain_same_letters?(item_letters) && not_same_word?(item_letters)
  end

  def letters
    @word.downcase.split('')
  end

  def not_same_word?(potential_anagram_letters)
    potential_anagram_letters != letters
  end

  def contain_same_letters?(potential_anagram_letters)
    potential_anagram_letters.sort == letters.sort
  end
end
