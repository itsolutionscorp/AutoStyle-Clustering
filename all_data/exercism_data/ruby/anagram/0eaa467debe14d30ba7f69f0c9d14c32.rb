class Anagram
  attr_accessor :word, :anagrams

  def initialize(word)
    @word = word
  end

  def match(array)
    @anagrams = []
    array.each do |case_word|
      if case_statement(case_word)
        if case_word == word
          break
        else
          @anagrams << case_word
        end
      end
    end
    return @anagrams
  end

  private

  def case_statement(case_word)
    case_word.downcase.split('').sort.eql? @word.downcase.split('').sort
  end

end
