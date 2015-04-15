class Anagram
  attr_accessor :word, :anagrams

  def initialize(word)
    @word = word
  end

  def match(array)
    @anagrams = []
    array.each { |case_word| case_statement(case_word) ? (case_word == word ? break : @anagrams << case_word) : false }
    return @anagrams
  end

  private

  def case_statement(case_word)
    case_word.downcase.split('').sort.eql? @word.downcase.split('').sort
  end

end
