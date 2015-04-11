require 'prime'

class PrimeFactors

  def self.for product
    Enumerator.new do |yielder|
      loop do
        break if product == 1
        factor = Prime.find {|prime| product.modulo(prime).zero?}
        product /= factor
        yielder.yield factor
      end
    end.to_a
  end

end
