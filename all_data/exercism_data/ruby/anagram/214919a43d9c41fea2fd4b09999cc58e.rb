class Anagram
  def initialize(word)
    @word = word.split('').map(&:downcase)
  end

  def match(list)
    result = []
    list.each do |word|
      check = word.split('').map(&:downcase)
      next if check == @word
      result << word if check.sort == @word.sort
    end
    result
  end
end
