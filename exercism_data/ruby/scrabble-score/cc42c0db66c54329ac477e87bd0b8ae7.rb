class Scrabble
  def initialize(word)
    @word = word || ""
    @values = { 1 =>  ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
                2 =>  ['D', 'G'],
                3 =>  ['B', 'C', 'M', 'P'],
                4 =>  ['F', 'H', 'V', 'W', 'Y'],
                5 =>  ['K'],
                8 =>  ['J', 'X'],
                10 => ['Q', 'Z']
              }
  end

  def score
    result = 0
    @word.split('').each do |letter|
      @values.each do |k, v|
        if v.include?(letter.upcase)
          result += k
        end
      end
    end
    result
  end

  def self.score(word)
    Scrabble.new(word).score
  end
end
