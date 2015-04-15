class Grains
  
  def initialize 
    @squares = {}
  end

  def square no
    2 ** (no - 1)
  end
  
  def total
    @total ||= (1..64).inject(0) do |sum, i| 
      sum += square(i) 
    end
  end
  
end
