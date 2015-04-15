class Grains

  def initialize
    @squares = populate_squares
  end
  
  def square number
    @squares[number]
  end
  
  def total
    @squares.values.inject(:+)
  end
  
  private 
  
  def populate_squares
    
    (1..64).inject({}) do |hash, square|
      hash[square] = ( square > 1 ) ? hash[ square - 1 ] * 2 : square
      hash
    end

  end

end
