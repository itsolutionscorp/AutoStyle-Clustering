class Anagram

  attr_reader :anagram

  def initialize anagram
    @anagram  = anagram
  end

  def match list
    list.select { |word| check word unless identical? word }
  end

  private

    def check word
        word if detect_anagram? word 
    end

    def detect_anagram? word
      word.downcase.chars.sort.join == anagram.downcase.chars.sort.join
    end
    
    def identical? word
      word.downcase == anagram.downcase
    end
end
