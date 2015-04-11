class Anagram
  def initialize(word)
    @word = word
  end

  def match(dictionary)
    matches = Array.new
    dictionary.each do |word|
      next if @word.downcase == word.downcase
      matches.push(word) if canon_format(@word) == canon_format(word)
    end
    matches
  end

  private 

  def canon_format(word)
    word.downcase.chars.sort
  end
end
