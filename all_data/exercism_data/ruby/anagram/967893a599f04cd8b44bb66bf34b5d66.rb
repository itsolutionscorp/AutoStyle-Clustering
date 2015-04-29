class Anagram
  attr_accessor :word
  def initialize(word)
    @word = word
  end

  def match(array)
    permutations = word.downcase.chars.to_a.permutation.map(&:join).uniq
    results = array.select {|arr| permutations.include?(arr.downcase) && arr.downcase != word.downcase}
  end
end
