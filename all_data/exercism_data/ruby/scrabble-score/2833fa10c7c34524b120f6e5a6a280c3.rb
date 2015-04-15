class Scrabble
  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    word = '' if word.nil?
    @word = word.strip.downcase
  end

  def score
    return 0 if @word.empty?
    values = @word.chars.map { |char| tile_value_of char }
    values.reduce &:+
  end

  def tile_value_of(letter)
    case letter
    when /[qz]/
      10
    when /[jx]/
      8
    when /[k]/
      5
    when /[fhvwy]/
      4
    when /[bcmp]/
      3
    else
      1
    end
  end
end
