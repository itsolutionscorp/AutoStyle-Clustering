class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    sorted = @word.downcase.chars.sort
    words.select do |anagram|
      sorted == anagram.downcase.chars.sort && anagram.downcase != @word.downcase
    end
  end
end
