class Anagram
  def initialize(word)
    @word = word.downcase
    @anagram = word.downcase.chars.sort.join
  end

  def match(words)
    words.select do |word| 
      @anagram == word.downcase.chars.sort.join &&
       @word != word.downcase
    end
  end
end
