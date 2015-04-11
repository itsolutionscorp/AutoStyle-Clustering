module StringUtils
  refine String do
    def to_a
      split('')
    end

    def permutation
      to_a.permutation.map(&:join)
    end
  end
end

class Anagram
  using StringUtils

  def initialize(word)
    @permutations = word.downcase.permutation.reject do |perm|
      perm == word.downcase
    end
  end

  def match(list)
    list.select do |word|
      @permutations.include? word.downcase
    end
  end
end
