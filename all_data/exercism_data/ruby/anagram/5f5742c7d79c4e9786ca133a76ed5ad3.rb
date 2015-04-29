class Anagram
  def initialize(word)
    @word = word
    @sorted_word = sort_string(@word)
  end
  
  def match(words)
    words = dedupe(words)
    words.select do |word|
      anagram? word
    end
  end

  private

  def sort_string(string)
    string.downcase.chars.sort.join
  end
  
  # Remove all variations of the starting word from the array
  def dedupe(words)
    words.select do |word|
      word.downcase != @word.downcase
    end
  end

  def anagram?(word)
    @sorted_word == sort_string(word)
  end
end
