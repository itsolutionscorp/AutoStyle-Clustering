class Grains 

  def square(square_number)
    squares[square_number]
  end

  def total
    total = 0
    squares.values.each do |value|
      total = total + value
    end
    total
  end

  private

  def squares
    squares = Hash.new
    value = 1
    1.upto(64) do |i| 
      if i == 1 
        squares[i] = 1  
      else  
        value = value * 2
        squares[i] = value   
      end
    end
    squares
  end

end
