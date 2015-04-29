class String
  def sort
    self.split(//).sort.join ''
  end
end

class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    words.group_by do |word| 
      unless same_word?(word)
        @word.downcase.sort == word.downcase.sort
      end
    end[true] || []
  end

  def same_word?(word)
    @word == word || 
      @word.downcase == word || 
      @word.upcase == word ||
      @word.capitalize == word
  end

end
