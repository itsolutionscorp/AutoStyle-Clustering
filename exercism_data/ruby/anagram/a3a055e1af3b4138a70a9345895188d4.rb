class Anagram

  def initialize(word)
    @word = word.downcase
    @permutations = generate_permutations(@word)
  end

  def match(candidates)
    candidates.select do |candidate|
      @permutations.include?(candidate.downcase)
    end
  end

  private

  def generate_permutations(word)
    all = word.split('').permutation.map(&:join)
    all.delete(word)
    all
  end
end
