class Scrabble

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = (word || "" ).gsub(/\W/, '').downcase
  end

  def score
    @word.each_char.map { |c| VALUES[c] }.reduce(0, :+)
  end

private 

  def self.create_letter_values(letter_values)
    letter_values.each_with_object(Hash.new(0)) do |(letters, value), hash|
      letters.each { |l| hash[l] = value }
    end
  end

  VALUES = create_letter_values({ %w{ a e i o u l n r s t } =>  1,
                                  %w{ d g }                 =>  2,
                                  %w{ b c m p }             =>  3,
                                  %w{ f h v w y }           =>  4,
                                  %w{ k }                   =>  5,
                                  %w{ j x }                 =>  8,
                                  %w{ q z }                 => 10 })

end
