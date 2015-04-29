class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(words)
    words = dedupe(words)
    words.select { |word| anagram?(word) }
  end

  private

  # Remove all variations of the starting word from the array
  def dedupe(words)
    words.select { |word| same_word?(word) }
  end

  def same_word?(word)
    word.downcase != @word.downcase
  end

  def anagram?(word)
    sort_string(word) == sort_string(@word)
  end
  
  def sort_string(string)
    string.downcase.chars.sort.join
  end
end
