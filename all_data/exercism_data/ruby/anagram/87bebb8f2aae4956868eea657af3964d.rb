class Anagram
  attr_accessor :word

  def initialize(word)

    @word = word

  end

  def match(word_list)
    word_list.select do |word|
      
      alphabetize(word) == alphabetize(@word) && word.downcase != @word.downcase ? word : nil
    end
  end

  private

  def alphabetize(word)
    word.downcase.split(//).sort.join
  end

end
