class Scrabble

    Table = { %w{A E I O U L N R S T} => 1,
              %w{D G} => 2,
              %w{B C M P} => 3,
              %w{F H V W Y} => 4,
              %w{K} => 5,
              %w{J X} => 8,
              %w{Q Z} => 10 }

    ScoreTable = Table.each_pair.with_object(Hash.new(0)) do |(letters, num), score_table |
      letters.each { |letter| score_table[letter] = num }
    end

  def initialize(word)
    @word = word
  end

  def score
    return 0 if @word == nil
    @word.upcase.each_char.reduce(0) { |acc, letter| acc += ScoreTable[letter] }
  end

  def self.score(word)
    self.new(word).score
  end

end