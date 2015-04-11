class Scrabble

  def initialize(word)
    @word = word

    @score_table = Hash.new(0)
    table = {%w{A E I O U L N R S T} => 1,
    %w{D G} => 2,
    %w{B C M P} => 3,
    %w{F H V W Y} => 4,
    %w{K} => 5,
    %w{J X} => 8,
    %w{Q Z} => 10}
    setup(table)
  end

  def setup(table)
    table.each_pair do |letters, num|
      letters.each { |letter| @score_table[letter] = num }
    end
  end

  def score
    return 0 if @word == nil
    @word.upcase.each_char.reduce(0) { |acc, letter| acc += @score_table[letter] }
  end

  def self.score(word)
    self.new(word).score
  end

end
