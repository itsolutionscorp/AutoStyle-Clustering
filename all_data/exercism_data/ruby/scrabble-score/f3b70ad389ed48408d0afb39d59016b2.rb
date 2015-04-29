class Scrabble
  def initialize(word)
    @word = word || ''
  end

  def score
    @word.chars.inject(0) { |score, letter| score + VALUES[letter.downcase] }
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  private

  def self.create_values
    values = Hash.new(0)

    [
      [%w{A E I O U L N R S T}, 1],
      [%w{D G}, 2],
      [%w{B C M P}, 3],
      [%w{F H V W Y}, 4],
      [%w{K}, 5],
      [%w{J X}, 8],
      [%w{Q Z}, 10]
    ].map do |letters, score|
      letters.map(&:downcase).each { |c| values[c] = score }
    end

    values
  end

  VALUES = create_values
end
