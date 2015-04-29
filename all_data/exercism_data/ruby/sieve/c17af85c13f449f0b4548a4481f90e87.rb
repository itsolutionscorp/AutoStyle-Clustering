class Sieve

  def initialize(num)
    @max = num
  end
 
  def primes
    ary = (2..@max).to_a
    ary.each_with_index do |element, index|
      ary.reject! { |v| v % ary[index] == 0 && v != ary[index] }
    end
    ary
  end    
 
end
