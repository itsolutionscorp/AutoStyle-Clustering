class Anagram
  def initialize(word)
    @word = sanitize_word(word)
    @permutations = calculate_permutations
  end

  def match(words)
    candidates = sanitize_candidates(words)

    candidates.map.with_index do |candidate, index| 
      words[index] if anagram_test(candidate)
    end.compact!
  end
  
  private

  def anagram_test(candidate)
    new_perms = @permutations - [candidate]
    if @permutations.count > new_perms.count
      true
    else
      false
    end
  end

  def calculate_permutations
    @word.split('').permutation.to_a.map{ |perm| perm.join('') } - [@word]
  end

  def sanitize_word(word)
    word.downcase.strip
  end

  def sanitize_candidates(words)
    words.map { |word| word.downcase.strip }
  end
end
