#   ```plain
# Letter                           Value
# A, E, I, O, U, L, N, R, S, T       1
# D, G                               2
# B, C, M, P                         3
# F, H, V, W, Y                      4
# K                                  5
# J, X                               8
# Q, Z                               10
# ```

# - You can play a `:double` or a `:triple` letter.
# - You can play a `:double` or a `:triple` word.

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
    total = 0
    return total if (word =~ /^(?=.*\D)[\w-]*$/).nil?

    word.downcase.split('').each do |char|
      KEY.each do |score, letter|
        if letter.include?(char)
          total += score
        end
      end
    end
    return total
  end
end
