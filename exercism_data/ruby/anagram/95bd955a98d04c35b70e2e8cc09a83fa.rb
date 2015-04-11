class Anagram
  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    possible_anagrams.select { |possible_word| matching?(possible_word) }
  end

  def matching?(possible_match)
    arrays_match?(possible_match) && words_not_the_same?(possible_match)
  end

  def arrays_match?(specific_word)
    prettify(specific_word) == prettify(@word)
  end

  def words_not_the_same?(possible_same)
    possible_same.downcase != @word.downcase
  end

  def prettify(ugly)
    ugly.downcase.chars.sort
  end
end
