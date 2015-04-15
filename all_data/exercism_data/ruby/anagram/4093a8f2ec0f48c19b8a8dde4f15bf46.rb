class String
  def sort_chars
    self.chars.sort
  end
end

class Anagram

  def initialize(pattern)
    @pattern = pattern.downcase
  end

  def match(words)
    words.select { |word| anagram?(word.downcase) }
  end
  
  private

    def anagram?(word)
      return false unless word.length == @pattern.length
      return false if word == @pattern
      word.sort_chars == sorted_pattern
    end

    def sorted_pattern
      @sorted_pattern ||= @pattern.sort_chars
    end
    
end
