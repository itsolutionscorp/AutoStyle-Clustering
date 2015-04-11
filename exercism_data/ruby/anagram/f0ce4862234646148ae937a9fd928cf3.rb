class Anagram

  def initialize string
    @string = string
  end

  def match list
    list.select do |word|
      word = word.downcase
      string = @string.downcase
      word != string && word.chars.sort == string.chars.sort
    end
  end

end
