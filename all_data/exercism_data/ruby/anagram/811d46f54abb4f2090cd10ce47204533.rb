class Anagram
  def initialize(word)
    @word = word
    @words = get_permutations
  end

  def match(possabilities)
    possabilities.each_with_object([]) do |word, array|
      array << word if @words.include?(word.downcase)
    end
  end

  def get_permutations
    @word.downcase.split(//).permutation(@word.length).map(&:join).uniq
  end

end
