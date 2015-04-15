class Grains
  def square(sq)
    sq==1 ? 1 : (self.square(sq-1))*2
  end
  
  def total
    total_num = 0
    (1..64).each do |x| 
      total_num += self.square(x)
    end 
    total_num
  end
end
