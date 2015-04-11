class Anagram

  def initialize(word)
    @word = word.downcase
    @letters = @word.chars.sort
  end
  
  def match(anagrams)
    match = anagrams.each_with_object([]) do |anagram, m|
      a = anagram.downcase
      m.push anagram if a != @word && a.chars.sort == @letters
    end
    return match
  end  
  
end
