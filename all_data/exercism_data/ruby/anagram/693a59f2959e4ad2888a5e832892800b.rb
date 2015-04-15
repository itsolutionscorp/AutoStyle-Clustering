class Anagram
  def initialize(str)
    @str = str.downcase
  end

  def match(words)
    words.select do |word|
      next unless @str.length == word.length
      word = word.downcase
      word != @str && sort_chars(word) == sort_chars(@str)
    end
  end

  private

  def sort_chars(str)
    str.chars.sort.join
  end
end
