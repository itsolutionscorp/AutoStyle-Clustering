class Grains
  
  def square(n)

    if n < 1
      return 0
    elsif n < 65
      return (2**n)/2 if n.between?(1,64)
    else
      return 0
    end

  end


  def total
    @total = (1..64).inject { |sum, n| sum + square(n) }
    @total
  end

end
