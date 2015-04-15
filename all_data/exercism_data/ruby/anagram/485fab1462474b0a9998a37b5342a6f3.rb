class Anagram
  def initialize(word)
    @word = word
  end

  def word_hash
    @word.split(//).inject(Hash.new(0)) do |accumulator, letter|
      letter.downcase
      accumulator[letter.downcase] += 1
      accumulator
    end
  end

  def match(input)
    input.map! do |string|
      anagram_hash = string.split(//).inject(Hash.new(0)) do |acc, letter|
        letter.downcase
        acc[letter.downcase] += 1
        acc
      end

      if anagram_hash.to_a.sort == word_hash.to_a.sort && string.downcase != @word
        string
      end
      
    end
    input.compact
  end
end
