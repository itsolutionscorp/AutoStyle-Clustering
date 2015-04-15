class Anagram
  def initialize(word)
    @word = word
    @compare = sort_chars(word)
  end

  def match(words)
    words.find_all { |w| is_anagram?(w) }
  end

  private
  
  def is_anagram?(word)
    sort_chars(word.downcase) == @compare && word.downcase != @word.downcase
  end

  def sort_chars(string)
    string.downcase.chars.sort.join
  end
end
