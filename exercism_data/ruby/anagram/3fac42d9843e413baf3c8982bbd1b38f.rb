class Anagram

  def initialize(word)
    @given_word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible|
      is_duplicate?(possible_anagram, @given_word) && is_anagram?(possible_anagram, @given_word)
    end
  end

  private

  def is_duplicate?(possible_anagram, given_word)
    possible_anagram.downcase != given_word
  end

  def is_anagram?(possible_anagram, given_word)
    sort(possible_anagram) == sort(given_word)
  end

  def sort(word)
    word.downcase.split("").sort
  end

end
