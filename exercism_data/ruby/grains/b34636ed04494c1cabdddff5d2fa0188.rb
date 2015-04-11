class Grains
  def square(sq)
    sq == 1 ? 1 : (self.square(sq-1))*2
  end

  def total
    total_var = (1..64).each do |num|
  		self.square(num)
    end
    puts total_var
    #total_var += total_var
  end

end
