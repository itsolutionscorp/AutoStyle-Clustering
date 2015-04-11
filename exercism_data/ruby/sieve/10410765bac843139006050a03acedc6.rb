class Sieve
  def initialize(limit)
    fail ArgumentError, 'The smallest prime is 2' if limit < 2
    @limit = limit.to_i
  end

  def primes
    list = candidate_list
    res = []
    loop do
      prime = list.shift
      res << prime
      return res if list.empty?
      list_without_multiples_of list, prime
    end
  end

  private

  def divisible_by?(dividend, divisor)
    dividend % divisor == 0
  end

  def list_without_multiples_of(list, number)
    list.keep_if { |e| !divisible_by? e, number }
  end

  def candidate_list
    [2] + oneven_numbers_upto(@limit)
  end

  def oneven_numbers_upto(limit)
    (1..((limit - 1) / 2)).map { |e| 2 * e + 1 }.to_a
  end
end
