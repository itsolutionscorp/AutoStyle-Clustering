class Sieve
  def initialize(n)
    @n = n
  end

  def primes
    numbers = []
    (2..@n).each{|x|
      numbers << x
    }
    counter = 0
    while counter < @n

      numbers.each_with_index{|x,i|
        if numbers[counter] != nil
          if x % numbers[counter] == 0 && i != counter
            numbers.delete_at(i)
          end
        end
      }
    counter += 1
    end
    numbers
  end
end

p Sieve.new(10).primes
