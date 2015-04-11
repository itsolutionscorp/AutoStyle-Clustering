class Anagram
  attr_reader :matcher

  def initialize(matcher)
    @matcher = matcher
  end

  def match(list)
    list.select do |word|
      sort_match?(word) && no_exact_match(word)
    end
  end

  def split_sort(word)
    word.upcase.chars.sort
  end

  def sort_match?(word)
    split_sort(word) == split_sort(matcher)
  end

  def no_exact_match(word)
    word.upcase != matcher.upcase
  end
end
