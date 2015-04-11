require 'prime'

class PrimeFactors

  def self.for number
    Enumerator.new do |yielder|
      product = number
      loop do
        break if product == 1
        factor, product = get_first_prime_factor product
        yielder.yield factor
      end
    end.to_a
  end

  private

  def self.get_first_prime_factor number
    factor = Prime.each do |prime|
      break prime if number.modulo(prime).zero?
    end
    return factor, (number / factor)
  end

end
