class Anagram
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def match(potential_matches)
    filter_duplicates(potential_matches).find_all do |potential_match|
      sort_letters(potential_match) == sort_letters(input)
    end
  end

  private

  def sort_letters(input)
    input.downcase.split("").sort.join
  end

  def filter_duplicates(list_of_words)
    list_of_words.reject do |word|
      word.downcase == input.downcase
    end
  end
end
