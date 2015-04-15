class Sieve
  def initialize (to)
    @to = to
  end

  def primes
    numbers = (2..@to).to_a
    primes = []

    while numbers.length > 0 do
      current_number = numbers.first()
      primes << current_number

      # only keep numbers that aren't multiples of current number
      # this also removes current_number itself
      numbers.reject!() do |number|
        number % current_number === 0
      end
    end

    primes
  end
end
