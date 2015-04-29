class SumOfMultiples

  attr_reader :multiple_of

  def initialize  *multiple_of
    @multiple_of =  multiple_of
  end


  def to limit
    multiples = multiple_of.flat_map { |divident| get_multiples divident, limit }
    sum_of_multiples_unique multiples 
  end

  def self.to limit
    SumOfMultiples.new( 3, 5 ).to limit 
  end

private

  def sum_of_multiples_unique multiples
    multiples.uniq.reduce( :+ )
  end


  def get_multiples divident, limit
    ( 0...limit ).select do |number|
        number % divident == 0
    end
  end

end
