class Anagram
  attr_reader :word
  def initialize word
    @word = word
    @parsed = word.downcase.split('').sort.join
  end

  def match words
    words.select { |word| anagram? word }
  end
  
  private
  def anagram? word
    @parsed == word.downcase.split('').sort.join && @word.downcase != word.downcase
  end
end
