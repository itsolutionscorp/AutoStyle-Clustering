class Anagram
  def initialize(word)
    @reference = sort_letters(word)
  end

  def match(candidates)
    candidates.select { |word| sort_letters(word) == @reference }
  end

  private
  def sort_letters(str)
    str.to_s.strip.downcase.chars.sort.join
  end
end
