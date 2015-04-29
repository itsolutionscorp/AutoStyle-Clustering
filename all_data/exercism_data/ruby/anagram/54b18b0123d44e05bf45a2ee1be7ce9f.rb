class Anagram
    def initialize word
      @word = word.downcase
      @letters = @word.chars.sort.join
    end

    def match words
      words.find_all { |w| w.downcase.chars.sort.join == @letters }.reject { |i| i.downcase == @word }
    end
end
