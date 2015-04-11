class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(list)
    permutations = @word.downcase.scan(/\w/).permutation.map(&:join)
    intersection = Array.new
    list.each do |word|
      intersection.push(word) if permutations.include?(word.downcase) and word.downcase != @word.downcase
    end
    intersection
  end
end
