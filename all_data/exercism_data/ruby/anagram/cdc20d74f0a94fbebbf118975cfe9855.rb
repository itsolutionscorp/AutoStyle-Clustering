class Anagram

  def initialize(seed)
    @seed = seed
  end

  def match(words)
    matches = Array.new
    words.each do | word |
      matches << word if anagram?(word)
    end
    return matches
  end

  private
    def anagram?(word)
      word.downcase.split('').sort == @seed.downcase.split('').sort && word.downcase != @seed.downcase
    end
end
