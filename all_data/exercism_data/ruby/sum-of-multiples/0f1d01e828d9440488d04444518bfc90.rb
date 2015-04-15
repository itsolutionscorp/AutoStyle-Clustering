class SumOfMultiples
  
  DEFAULT_BASES = 3, 5

  attr_reader :bases

  def initialize *bases
    @bases = bases
  end

  def self.to number
    new( *DEFAULT_BASES ).to number
  end

  def to number
    ( 0...number ).reduce(0) do |result, element|
      result + value_of( element )
    end
  end

private
  
  def value_of element
    is_multiple?( element ) ? element : 0
  end

  def is_multiple? element
    bases.find { |number| (element % number).zero? }
  end

end
