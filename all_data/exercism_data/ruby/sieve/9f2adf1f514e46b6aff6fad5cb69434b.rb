class Sieve
	def initialize(number)
		@n = number
	end

	def primes
    @array_numb = (2..@n).reduce([]) do |acm, val|
      acm << val
    end
    @array_numb.map do |v|
      if (v != 0)
        (v..@array_numb.length-1).map do |k|
          if (@array_numb[k] % v == 0)
            @array_numb[k] = 0
          end
        end
      end
    end
    @array_numb.select do |val|
      val != 0
    end
	end
end
