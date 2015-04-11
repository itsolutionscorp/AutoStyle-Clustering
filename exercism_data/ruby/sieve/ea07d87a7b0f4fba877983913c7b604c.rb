class Sieve
  def initialize(upto)
    @upto = upto
  end

  def primes
    range = (2..@upto).to_a
    (2..Math.sqrt(@upto)).each do |n|
      range.delete_if { |i| i != n && i % n == 0 }
    end
    range
  end

end
