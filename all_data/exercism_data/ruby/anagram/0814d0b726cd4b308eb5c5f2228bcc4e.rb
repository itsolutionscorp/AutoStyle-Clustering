class Anagram

  attr_reader :anagram, :result

  def initialize anagram
    @anagram  = anagram
    @result = []
  end

  def match list
    list .each { |word| check word unless identical? word }
    result
  end

  private

    def check word
       result << word if detect_anagram? word 
    end

    def detect_anagram? word
      word.downcase.chars.sort.join == anagram.downcase.chars.sort.join
    end
    
    def identical? word
      word.downcase == anagram.downcase
    end
end
