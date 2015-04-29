class Scrabble

    @@scores = {}
    ["a", "e", "i", "o", "u", "l", "n", "r", "s", "t"].each { |letter| @@scores[letter] = 1 }
    ["d", "g"].each                                         { |letter| @@scores[letter] = 2 }
    ["b", "c", "m", "p"].each                               { |letter| @@scores[letter] = 3 }
    ["f", "h", "v", "w", "y"].each                          { |letter| @@scores[letter] = 4 }
    ["k"].each                                              { |letter| @@scores[letter] = 5 }
    ["j", "x"].each                                         { |letter| @@scores[letter] = 8 }
    ["q", "z"].each                                         { |letter| @@scores[letter] = 10 }

  def initialize(word)
    @@word = word 
    Scrabble.score_word(word)
  end

  def Scrabble.score(word = @@word)
    #works for the "convenient_scoring" test
    score_word(word) 
  end

  def score(word = @@word)
    #works for all of the Scrabble.new("test").score calls
    Scrabble.score_word(word)
  end
  
  def Scrabble.score_word(word)
    #does all of the work for the class
    total = 0

    word.to_s.downcase.each_char do |i| 
      total += @@scores[i] if @@scores[i]
    end
 
    return total 
  end
end
