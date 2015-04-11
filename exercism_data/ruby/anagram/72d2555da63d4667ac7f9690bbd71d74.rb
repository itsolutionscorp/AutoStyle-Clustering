#new iteration
class Anagram
  def initialize(word)
    @word = word.downcase
    @prime = @word.split("").sort.join
  end

  def match(ray)
    ans = ray.select do |word|
      word.downcase.split("").sort.join
      word.downcase.split("").sort.join == @prime.downcase
    end
    ans.select! {|word| word.downcase != @word}
    ans
  end
end
