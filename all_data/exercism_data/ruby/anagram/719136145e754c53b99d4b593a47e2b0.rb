class Anagram
  def initialize string
    @string = string
  end

  def match words
    words.select { |word| anagram? word }
  end

  private

  attr_reader :string

  def anagram? word
    not same? word and
      permutations.include? word.downcase.chars
  end

  def same? word
    word.casecmp(string).zero?
  end

  def permutations
    string.downcase.chars.permutation
  end
end
