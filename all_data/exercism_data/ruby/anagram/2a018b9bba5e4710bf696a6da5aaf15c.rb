class Anagram
  def initialize(str)
    str = str.downcase
    @str = str
    @sorted_str = str.chars.sort.join
  end

  def match(words)
    words.select do |word|
      word = word.downcase
      @str != word && @sorted_str == word.chars.sort.join
    end
  end
end
