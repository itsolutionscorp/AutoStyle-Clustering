class SumOfMultiples 
  def initialize(*args)
    @multipliers = *args
  end


  def to(upto)
    (0..upto-1).select{|num| multiple? num }.inject(:+)
  end

  def self.to(upto)
    new(3, 5).to(upto)
  end
  
  def multiple?(number)
    @multipliers.any?{|multi| number % multi == 0}
  end
end
