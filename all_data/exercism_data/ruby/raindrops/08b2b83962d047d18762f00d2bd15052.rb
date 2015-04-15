class Raindrops
  attr_reader :integer

  PRIME_WORD_MAP = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(input)
    new(input).to_s
  end

  def initialize(integer)
    @integer = integer
  end

  def to_s
    @to_s ||= "".tap do |output|
      PRIME_WORD_MAP.each do |prime, word|
        output << word if (integer.to_f/prime) % 1 == 0
      end
      output << integer.to_s if output.empty?
    end
  end

end
