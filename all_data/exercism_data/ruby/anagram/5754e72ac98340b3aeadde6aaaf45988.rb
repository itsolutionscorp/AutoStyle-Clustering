class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(possible_anagrams)
    @possible = remove_duplicates(possible_anagrams)
    sort_and_match
  end

  def remove_duplicates(possible_anagrams)
    possible_anagrams.reject { |dup| dup.downcase == @word.downcase }
  end

  def sort_and_match
    @possible.find_all { |match| match.downcase.split(//).sort == @word.downcase.split(//).sort }
  end

end
