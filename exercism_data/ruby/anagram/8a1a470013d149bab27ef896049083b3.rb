class Anagram
  
  def initialize(word)
    @original_word = word.downcase
    @sorted_word = word.downcase.chars.sort
  end

  def match(words)
    words.select do |word|
      dword = word.downcase
      @original_word != dword && @sorted_word == dword.chars.sort
    end
  end

end
