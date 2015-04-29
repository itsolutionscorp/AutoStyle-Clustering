class Scrabble

  @@score = {}

  [["AEIOULNRST", 1],
   ["DG", 2],
   ["BCMP", 3],
   ["FHVWY", 4],
   ["K", 5],
   ["JX", 8],
   ["QZ", 10]].each { |sc| sc[0].each_char { |l| @@score[l] = sc[1] } }

  def initialize(word)
    if word == nil then
      @word = ""
    else
      @word = word.gsub(/[^a-zA-Z]/, "").upcase
    end
  end

  def score
    @word.each_char.reduce(0) { |acc, c| acc += @@score[c] }
  end

  def self.score(word)
    return Scrabble.new(word).score
  end

  def self.score_board
    @@score
  end
end
