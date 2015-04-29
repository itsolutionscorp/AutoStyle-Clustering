class Raindrops

  def self.convert value
    new( value ).convert
  end

  FACTOR_CORRESPONDENCE = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  attr_reader :value

  def initialize value
    @value = value
  end

  def convert
    output = factor_output

    output.empty? ? value.to_s : output
  end

private

  def factor_output
    matching_factors.map( &:last ).join
  end

  def matching_factors
    FACTOR_CORRESPONDENCE.select do |factor, output|
      ( value % factor ).zero?
    end
  end

end
