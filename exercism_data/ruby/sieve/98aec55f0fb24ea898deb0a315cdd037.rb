require 'prime'

 class Sieve
   def initialize(max)
     @range = (2..max).map { |n| n }
   end

   def primes(list=@range, index=0)
    list -= list[(index + 1)..-1].select do |n|
      n % list[index] == 0 
    end
    list[index + 2] ? primes(list, index + 1) : list
   end
 end
