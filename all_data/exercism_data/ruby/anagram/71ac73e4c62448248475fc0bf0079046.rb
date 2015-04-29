class Anagram
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def match(potential_matches)
    filter(potential_matches).find_all do |potential_match|
      clean(potential_match) == clean(input)
    end
  end

private

  def clean(input)
    input.downcase.split("").sort.join
  end

  def filter(list_of_words)
    list_of_words.reject do |word|
      word.downcase == input.downcase
    end
  end
end
