class Squares
  def initialize(num)
     @num = num
   end
 
   def sum_of_squares
     x=@num
     f = lambda { |x| x < 2 ? x : f[x-1] + x**2 }
     f.call(x)
   end
 
   def square_of_sums
     x=@num
     f = lambda { |x| x < 2 ? x : f[x-1] + x }
     f.call(x)**2
   end
 
   def difference
     square_of_sums - sum_of_squares
   end
end
