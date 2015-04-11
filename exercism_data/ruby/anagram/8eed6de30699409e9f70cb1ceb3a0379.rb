class Anagram
  def initialize word
    @word = word
  end

  def match list
    list.select {|word| @word.downcase.chars.sort == word.downcase.chars.sort and @word.downcase != word.downcase}
  end
end
