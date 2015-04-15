class Anagram < Struct.new(:word)

  def find_word(word_array)
    @permutations.delete(word)
    word_array.find_all do |word|
      @permutations.include? word
    end
  end

  def get_permutations
    word.downcase.split(//).permutation.to_a.map(&:join)
  end

  def match(word_array)
    @permutations = get_permutations
    find_word(word_array)
  end

end
