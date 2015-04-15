class Anagram
  def initialize word
    @word = word
  end
  
  def chars word
    word.downcase.chars.sort
  end

  def match words
    words.select do |item|
      chars(@word) == chars(item) && item.downcase != @word
    end
  end
end
