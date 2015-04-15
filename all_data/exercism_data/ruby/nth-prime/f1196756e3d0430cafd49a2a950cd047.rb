require 'prime'

class Primes
  def self.nth(n)
    results_array = []
    i = 0

    if n == 0 then raise ArgumentError end
    while results_array.size < n
      i += 1
      if i.prime? then results_array << i end
    end

    results_array[n-1]
  end
end
