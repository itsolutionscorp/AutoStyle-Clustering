class Anagram
  def initialize(word)
    @word = word
  end

  def match(potential_anagrams)
    letters = @word.downcase.split('')
    potential_anagrams.each.with_object([]) do |potential_anagram, matches|
      potential_anagram_letters = potential_anagram.downcase.split('')
      if contain_same_letters?(potential_anagram_letters, letters) && not_same_word?(potential_anagram_letters, letters)
        matches << potential_anagram
      end
    end
  end

  def not_same_word?(potential_anagram_letters, letters)
    potential_anagram_letters != letters
  end

  def contain_same_letters?(potential_anagram_letters, letters)
    potential_anagram_letters.sort == letters.sort
  end
end
