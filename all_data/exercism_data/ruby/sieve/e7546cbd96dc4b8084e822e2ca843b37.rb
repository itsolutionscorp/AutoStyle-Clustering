class Sieve

  attr_accessor :primes

  def initialize(limit)
    list = (2..limit).to_a

    list.each do |num1|
      list.select! { |num2| num2 % num1 != 0 || num2 <= num1 }
    end

    @primes = list
  end
end
