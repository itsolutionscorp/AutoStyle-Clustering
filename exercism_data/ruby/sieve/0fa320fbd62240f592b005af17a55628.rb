class Sieve

  def initialize(number)
    @number = number
  end

  def primes
    numbers = (2..@number).to_a
    numbers.each do |number|
      numbers.delete_if { |number_greater| number_greater % number == 0 unless number_greater == number}
    end
  end

end
