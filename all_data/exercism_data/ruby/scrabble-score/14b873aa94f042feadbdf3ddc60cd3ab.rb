class Scrabble
  KEY = {
     1 => ['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'],
     2 => ['d', 'g'],
     3 => ['b', 'c', 'm', 'p'],
     4 => ['f', 'h', 'v', 'w', 'y'],
     5 => ['k'],
     8 => ['j', 'x'],
    10 => ['q', 'z']
  }

  def initialize(word)
    @word = word
  end

  attr_reader :word

  def score
    total = 0
    return total if (word =~ /^(?=.*\D)[\w-]*$/).nil?

    @word.downcase.split('').each do |char|
      KEY.each do |score, letter|
        if letter.include?(char)
          total += score
        end
      end
    end
    return total
  end

  def self.score(word)
    self.new(word).score
  end
end
