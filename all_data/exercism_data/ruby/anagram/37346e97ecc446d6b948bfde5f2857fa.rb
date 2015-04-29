class Anagram

 def initialize(word)
  @word = word
 end

 def match(word_array)
    @permutations = get_permutations
    @permutations.delete(@word)
    find_word(word_array)
 end

 private

 def find_word(word_array)
    word_array.find_all do |word|
      @permutations.include? word.downcase
    end
  end

  def get_permutations
    @word.downcase.split(//).permutation.to_a.map(&:join)
  end

end
