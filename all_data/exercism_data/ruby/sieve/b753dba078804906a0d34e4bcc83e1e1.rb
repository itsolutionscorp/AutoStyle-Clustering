class Sieve
  attr_accessor :limit
  def initialize(limit)
    @limit = limit
  end

  def primes
    list = (2..limit).to_a
    list.each do |p|
      list.reject! {|x| x % p == 0 && x / p > 1}
    end
    list
  end
end
