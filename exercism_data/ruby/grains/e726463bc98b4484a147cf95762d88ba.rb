class Grains
  def square(sq)
    sq==1 ? 1 : (self.square(sq-1))*2
  end
  
  def total
   # (1...64) { 
   # total += |sq_num| self.square(sq_num)}
  end
end
