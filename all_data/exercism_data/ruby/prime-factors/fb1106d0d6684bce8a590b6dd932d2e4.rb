require "prime"

class PrimeFactors

  def self.for(number)
    prime = number
    solution = []
    check_true = 1

    until check_true == number
      if number % prime == 0 && Prime.prime?(prime)
        solution << prime
      end
      prime -= 1
      if prime == 0
        prime = number
      end

      check_true = 1

      solution.each do |solutions|
        check_true = solutions * check_true
      end
    end

    solution.sort
  end

end
