class Sieve
  def initialize(upto)
    @upto = upto
  end

  def primes
    numbers = (2..@upto).to_a
    numbers.each do |number|
      numbers.reject! {|i| i > number && i % number == 0}
    end
  end

end
