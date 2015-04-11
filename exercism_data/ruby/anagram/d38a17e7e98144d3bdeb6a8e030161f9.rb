class Anagram < String
  def match words
    words.select do |word| 
      word.downcase != self.downcase && word.downcase.chars.sort == self.downcase.chars.sort
    end
  end
end
