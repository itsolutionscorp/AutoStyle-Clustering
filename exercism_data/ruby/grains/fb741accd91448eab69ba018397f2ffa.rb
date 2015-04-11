class Grains

  def square no
    (1..no).inject(0) do |sum, i|
      sum = (i == 1) ? 1 : sum * 2
    end
  end
  
  def total
    (1..64).inject(0) do |sum, i| 
      sum += square(i) 
      sum
    end
  end
  
end
