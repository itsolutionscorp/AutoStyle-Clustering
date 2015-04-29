class Anagram
  attr_accessor :word

  def initialize(word)

    @word = word

  end

  def match(word_list)
    word_list.select do |word|
      
      alphabetize(word) == alphabetize(@word) ? word : nil
    end
    wordlist.delete(@word)
  end

  private

  def alphabetize(word)
    word.downcase.split(//).sort.join
  end

end
