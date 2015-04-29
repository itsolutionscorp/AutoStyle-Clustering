class Anagram
  
  def initialize word
    @word = word
  end

  def match choices
    permutations = @word.downcase.chars.permutation.to_a.map{ |item| item.join }
    permutations.shift   
    choices.select { |word| permutations.include? word.downcase }
  end
end
