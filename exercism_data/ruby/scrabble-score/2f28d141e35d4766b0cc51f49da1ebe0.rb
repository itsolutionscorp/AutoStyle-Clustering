class Scrabble
  VALUES = {
    '1' => ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
    '2' => ['D', 'G'],
    '3' => ['B', 'C', 'M', 'P'],
    '4' => ['F', 'H', 'V', 'W', 'Y'],
    '5' => ['K'],
    '8' => ['J', 'X'],
    '10' => ['Q', 'Z']
  }
  def initialize(word = '')
    @anagram = word
  end

  def score(word = @anagram)
    score = 0
    VALUES.each do |key, value|
      word.to_s.upcase.split('').each do |letter|
        if value.include?(letter)
            if letter && letter != '' && letter != '\t' && letter != '\n'
              score += key.to_i
            end
        end
      end
    end
    score
  end

  def self.score(word)
    new(word).score
  end
end
