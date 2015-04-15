require 'pry-byebug'
 
 class Squares
   def initialize(i)
     @inclusive_range = (1..i)
   end

   def square_of_sums
     sum = @inclusive_range.inject(:+) 
     sum * sum
   end

   def sum_of_squares
     @inclusive_range.inject(0) { |mem, y| mem + y**2 }
   end

   def difference
     square_of_sums - sum_of_squares
   end
 end
