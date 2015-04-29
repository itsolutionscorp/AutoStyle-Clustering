class Anagram

  def initialize(pattern)
    @pattern = pattern.downcase
  end

  def match(words)
    words.each_with_object([]) { |word, matches| matches << word if anagram?(word.downcase) }
  end
  
  private

    def anagram?(word)
      return false unless word.length == @pattern.length
      return false if word == @pattern
      word.chars.sort == sorted_pattern
    end

    def sorted_pattern
      @sorted_pattern ||= @pattern.chars.sort
    end
    
end
