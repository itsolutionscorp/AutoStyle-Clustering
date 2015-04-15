class Scrabble
  def initialize(word)
    @word = word
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  def score
    return 0 if @word.nil?
    result = 0
    @word.each_char do |l|
      dictionary.each_pair do |k,v|
        if k.include?(l.upcase)
          result += v
          break
        end  
      end
    end
    result
  end

  def dictionary
    {
      ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"] => 1,
                                              ["D", "G"] => 2,
                                    ["B", "C", "M", "P"] => 3,
                               ["F", "H", "V", "W", "Y"] => 4,
                                                   ["K"] => 5,
                                              ["J", "X"] => 8,
                                              ["Q", "Z"] => 10
    }
  end
end
