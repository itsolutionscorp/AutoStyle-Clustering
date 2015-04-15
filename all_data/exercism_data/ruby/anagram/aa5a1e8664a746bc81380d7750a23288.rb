class Anagram
  def initialize(word)
    word.downcase!
    @anagram_array = word.split('')
                         .permutation
                         .map { |item| item.join }
    @anagram_array.delete(word)
  end

  def match(word_array)
    match_array = []
    word_array.each do |word|
      match_array.push word if @anagram_array.include? word.downcase
    end

    match_array
  end
end
