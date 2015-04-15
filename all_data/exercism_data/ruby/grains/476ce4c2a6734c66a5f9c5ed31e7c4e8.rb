class Grains

  def square(square_number)
    2 ** (square_number - 1)
  end

  def total
    (1..64).inject(0) do |total,square_number| 
      total += square(square_number) 
    end     
  end

end
