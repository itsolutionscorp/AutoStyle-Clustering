class Raindrops
  def self.convert(number)
    new(number).to_s
  end

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def to_s
    if matches.any?
      matches.join
    else
      number.to_s
    end
  end

  private

  def matches
    conversions.select do |prime, conversion|
      number % prime == 0
    end.values
  end

  def conversions
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end
end
