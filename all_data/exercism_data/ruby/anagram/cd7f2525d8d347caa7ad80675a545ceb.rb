class Anagram
  def initialize(word)
    @word    = word.downcase
    @letters = @word.split("").sort
  end

  def match(choices)
    choices.select do |choice|
      next if choice.downcase == @word
      choice if choice.downcase.split("").sort == @letters
    end
  end
end
