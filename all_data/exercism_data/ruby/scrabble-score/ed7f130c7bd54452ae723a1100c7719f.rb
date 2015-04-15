class Scrabble
  attr_reader :letters
  def initialize(letters)
    @letters = letters.to_s.strip.downcase.split("")
  end

  def self.score(word)

    scrabble = new(word)
  end

  def score
    array = letters.map do |l|
      alphabet[l]
    end
    if array.empty?
  def alphabet
    {'a' => 1, 'A' => 1, 'M' => 3, 'U' => 1, 'L' => 1, 'T' => 1,
      'I' => 1, 'B' => 3, 'O' => 1, 'N' => 1, 'R' => 1, 'E' => 1,
      's'=> 1, 't' => 1, 'r' => 1, 'q' => 10, 'k' => 5, 'u' => 1,
      'e' => 1, 't' =>1, 'f' => 4, '' => 0, 'i' => 1, 'y' => 4, }
      # for nil values ---#"" => 0 }
  end
# A, E, I, O, U, L, N, R, S, T       1
# D, G                               2
# B, C, M, P                         3
# F, H, V, W, Y                      4
# K                                  5
# J, X                               8
# Q, Z                               10
# ```
  end
end
end
