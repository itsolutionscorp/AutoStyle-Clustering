class Sieve
  def initialize (to)
    @to = to
  end

  def primes
    numbers = (2..@to).to_a
    to = @to
    current_number = numbers.first()

    loop do
      # only keep numbers that aren't multiples of current number
      # shrinking the array with every iteration and speeding up for large ones
      numbers.select!() do |number|
        number == current_number || number % current_number != 0
      end

      # find next number, that is greater than current
      # if there is none left, break
      current_number = numbers.find() do |number|
        number > current_number
      end

      break if current_number.nil?
    end

    numbers
  end
end
