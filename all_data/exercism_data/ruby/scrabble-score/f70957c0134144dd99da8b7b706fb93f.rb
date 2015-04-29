class Scrabble  

  def initialize(word)
    @word = word.to_s.upcase
  end
  
  def score
    scores = Hash.new(0)
    scores["A"] = 1
    scores["E"] = 1
    scores["I"] = 1
    scores["O"] = 1
    scores["U"] = 1
    scores["L"] = 1
    scores["N"] = 1
    scores["R"] = 1
    scores["S"] = 1
    scores["T"] = 1
    scores["D"] = 2
    scores["G"] = 2
    scores["B"] = 3
    scores["C"] = 3
    scores["M"] = 3
    scores["P"] = 3
    scores["F"] = 4
    scores["H"] = 4
    scores["V"] = 4
    scores["W"] = 4
    scores["Y"] = 4
    scores["K"] = 5
    scores["J"] = 8
    scores["X"] = 8
    scores["Q"] = 10
    scores["Z"] = 10
    @word.chars.inject(0) { |sum, element| sum + scores[element] }
  end

  def self.score(word)
    Scrabble.new(word).score
  end  

end
