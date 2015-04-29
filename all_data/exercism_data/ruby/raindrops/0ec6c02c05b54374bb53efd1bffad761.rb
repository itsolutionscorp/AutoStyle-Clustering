class Raindrops

  def self.convert(prime_numb)                  # input - putative prime number
    convert_output = ''                         # initialize output variable

    if prime_numb%3 == 0                        # number evenly divisable by 3
      convert_output = convert_output + "Pling"
    end

    if prime_numb%5 == 0                        # number evenly divisable by 5
      convert_output = convert_output + "Plang"
    end

    if prime_numb%7 == 0                        # number evenly divisable by 7
      convert_output = convert_output + "Plong"
    end

    if prime_numb%3 != 0 && prime_numb%5 != 0 && prime_numb%7 != 0  # no primes
      convert_output = prime_numb.to_s
    end
    convert_output                              # prime number result output
  end
end
