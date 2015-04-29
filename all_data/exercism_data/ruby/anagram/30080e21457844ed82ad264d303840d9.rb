class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    candidate_words(words).select { |w| anagram?(w) }
  end

  private

  def candidate_words(words)
    words.reject { |w| w.length != @word.length }
         .select { |w| w.downcase != @word }
  end

  def anagram?(word)
    permutations(word).include?(@word)
  end

  def permutations(word)
    word.downcase.split(//).permutation.map(&:join)
  end
end
