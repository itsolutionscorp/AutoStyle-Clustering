class Sieve

  attr_reader :numbers

  def initialize n
    @numbers = (2..n).to_a.map { |val| Prime.new(val, true) }
  end

  def primes
    numbers.each_with_object([]) do |num, prime_array|
      numbers.each do |nu|
        nu.prime = false if nu.value % num.value == 0 && !(nu.value.eql? num.value)
      end if num.prime == true

      prime_array << num.value if num.prime == true
    end
  end
  
  private

  Prime = Struct.new(:value, :prime)

end
