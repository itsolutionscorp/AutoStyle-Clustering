class Anagram

  def initialize(word)
    @word = word
  end

  def match(word_array)
    letter_array = prep(@word)
    anagrams = Array.new
    word_array.each do |word|
      comp_array = prep(word)
      break if comp_array.eql?(letter_array)
      anagrams << word if comp_array.sort.eql?(letter_array.sort)
    end
    anagrams
  end

  def prep(word)
    word.downcase.split(//)
  end

end
