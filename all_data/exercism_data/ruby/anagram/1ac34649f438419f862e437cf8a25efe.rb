class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(words)
    dedupe(words).select { |word| anagram?(word) }
  end

  private

  # Remove all variations of the starting word from the array
  def dedupe(words)
    words.select do |word|
      word.downcase != @word.downcase      
    end
  end

  def anagram?(word)
    sort_string(word) == sort_string(@word)
  end
  
  def sort_string(string)
    string.downcase.chars.sort.join
  end
end
