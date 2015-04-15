class Sieve
  def initialize(n)
    @initial = (2..n).to_a
  end

  def primes
    @primes ||= [].tap do |pr|
      until initial.empty?
        pr << (n = initial.shift)
        initial.reject! {|x| x % n == 0 }
      end
    end
  end

  private
  attr_reader :initial
end
