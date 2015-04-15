class Anagram
  def initialize(word)
    @word    = word.downcase
    @letters = @word.split("").sort
  end

  def match(choices)
    choices.each_with_object(Array.new) do |choice, anagrams|
      next if choice.downcase == @word
      anagrams << choice if choice.downcase.split("").sort == @letters
    end
  end
end
