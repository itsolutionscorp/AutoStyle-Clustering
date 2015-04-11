class Anagram
  def initialize(word)
    @anagrams = word.to_s.downcase.split('').permutation.map &:join
  end

  def match(list)
    list.select { |word| @anagrams.include? word.downcase }
  end
end
