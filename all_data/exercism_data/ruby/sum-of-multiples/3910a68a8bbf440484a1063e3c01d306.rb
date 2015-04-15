class SumOfMultiples
  
  attr_reader :multiples

  def initialize *multiples
    @multiples = multiples
  end

  def self.to number
    new( 3, 5 ).to number
  end

  def to number
    ( 0..number.pred ).reduce(0) do |result, element|
      result + value_of( element )
    end
  end

private
  
  def value_of element
     is_multiple?( element ) ? element : 0
  end

  def is_multiple? element
    multiples.find { |number| (element % number).zero? }
  end

end
