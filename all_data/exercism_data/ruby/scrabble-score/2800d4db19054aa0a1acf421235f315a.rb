class Scrabble

  LETTER_VALUES = {
    %w( A E I O U L N R S T ) => 1,
    %w( D G )                 => 2,
    %w( B C M P )             => 3,
    %w( F H V W Y )           => 4,
    %w( K )                   => 5,
    %w( J X )                 => 8,
    %w( Q Z )                 => 10
  }

  attr_reader :word

  def initialize word
    @word = word
  end

  def self.score word
    Scrabble.new( word ).score
  end

  def score
    if is_a_word?
      sum 
    else
      0
    end
  end

private

  def is_a_word?
    word =~ /[a-zA-Z]+/
  end

  def array_of_words
    word.chars
  end

  def get_points
    array_of_words.map do |letter|
      get_keys letter.upcase
    end
  end

  def get_keys letter
    LETTER_VALUES.flat_map do |keys, value|
      keys.flat_map do |key| 
        value if key.include? letter
      end
    end.compact
  end

  def sum
    get_points.flatten.reduce:+
  end

end
