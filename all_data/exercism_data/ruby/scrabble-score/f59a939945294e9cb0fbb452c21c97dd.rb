class Scrabble  

  def initialize(s)
    @s = s
    if(@s == nil) 
      @s = ''
    end
    @s = @s.upcase
  end
  
  def score
    scores = {
      "AEIOULNRST" => 1,
      "DG" => 2,
      "BCMP" => 3,
      "FHVWY" => 4,
      "K" => 5,
      "JX" => 8,
      "QZ" => 10,
    }  
    count = 0
    scores.each { |l, n| count += @s.count(l) * n }
    count
  end

  def self.score(s)
    Scrabble.new(s).score
  end  

end
