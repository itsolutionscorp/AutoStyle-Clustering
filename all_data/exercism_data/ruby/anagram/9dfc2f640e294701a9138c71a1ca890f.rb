class AnagramTest < MiniTest::Unit::TestCase
  class Anagram
    def initialize(word)
      @word = word
      @anagram = Array.new
    end

    

    def match(candidates)
      candidates.each do |candidate|
      if candidate.to_s.downcase.chars.sort.join == @word.to_s.downcase.chars.sort.join
          @anagram << candidate unless candidate.downcase == @word
        end
      end
      @anagram
    end
  end
end
