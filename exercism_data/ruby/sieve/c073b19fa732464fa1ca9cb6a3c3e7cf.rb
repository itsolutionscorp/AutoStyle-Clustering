class Sieve
    attr_accessor:primes
    def initialize(n)
        @primes=(2..n).to_a
        (2..n).to_a.each { |x| @primes.each {|y| primes.delete(x*y)}}
    end
end
