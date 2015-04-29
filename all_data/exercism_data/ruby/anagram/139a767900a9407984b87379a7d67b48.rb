class Anagram
  attr_accessor :word, :anagrams

  def initialize(word)
    @word = word
  end

  def match(array)
    @anagrams = []
    array.each do |case_word|
      if case_word.downcase.split('').sort.eql? @word.downcase.split('').sort
        if case_word == @word
          break
        end
        @anagrams << case_word
      end
    end
    return @anagrams
  end

end
