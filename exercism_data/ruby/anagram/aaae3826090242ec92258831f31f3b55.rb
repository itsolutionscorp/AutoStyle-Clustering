class Anagram

  def initialize(word)
    @word = word.downcase
    @letters = @word.chars.sort
  end
  
  def match(anagrams)
    match = Array.new
    anagrams.each do |anagram|
      a = anagram.downcase
      if a != @word && a.chars.sort == @letters
        match.push anagram
      end
    end
    return match
  end  
  
end
