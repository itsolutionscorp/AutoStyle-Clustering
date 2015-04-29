class Anagram

  def initialize input
    @input = input
  end

  def match matches
    matches.each_with_object([]) do |current, result|
      result << current if is_anagram_of? current
    end
  end

  private

  attr_reader :input

  def is_anagram_of? value
    v = value.downcase
    i = input.downcase

    v != i && v.chars.sort == i.chars.sort
  end

end
