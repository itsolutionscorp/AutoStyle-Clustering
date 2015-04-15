class Grains
 
  def square number
    (1..number).inject(1) do |r, e| 
      if e == 1
        r
      else
        r*2
      end
    end 
  end

  def total
    (1..64).inject { |r, e| r + square(e) }
  end
end
