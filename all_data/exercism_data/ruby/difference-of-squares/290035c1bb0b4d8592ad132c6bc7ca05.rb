class Squares
  attr_reader :n
  def initialize(n)
     @n = n
   end
 
   def sum_of_squares
     f = lambda { |n| n < 2 ? n : f[n-1] + n**2 }
     f.call(n)
   end
 
   def square_of_sums
     f = lambda { |n| n < 2 ? n : f[n-1] + n }
     f.call(n)**2
   end
 
   def difference
     square_of_sums - sum_of_squares
   end
end
