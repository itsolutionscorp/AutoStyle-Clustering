class Grains

  def initialize()
    
  end

  def square( number )
    if (1..64) === number
      2 ** (number - 1)
    else
      raise ArgumentError.new("Argument is not in range of 1..64")
    end 
  end

  def total
    # 2**64 - 1
    18446744073709551615
  end


end
