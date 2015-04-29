class Anagram
  def initialize word
    @word = word.downcase
  end

  def match list
    list.select { |w| w.downcase != @word && w.downcase.chars.sort == @word.chars.sort }
  end
end
