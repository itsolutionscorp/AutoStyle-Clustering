class Anagram

  def initialize(word)
    @sorted_matcher = sort_chars(word)
  end

  def match(word_list)
    word_list.select do |entry|
      sort_chars(entry) == sorted_matcher
    end
  end

  private 

  attr_reader :sorted_matcher

  def sort_chars(word)
    word.downcase.chars.sort
  end

end
