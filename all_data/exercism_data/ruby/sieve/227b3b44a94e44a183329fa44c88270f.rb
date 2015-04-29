class Sieve
  def initialize(num)
    @primes = Array(1..num)
  end

  def primes
    result = @primes - [1]
    2.upto(@primes.length).inject(result) { |result, num| result - divide_by(num, @primes.length) }
  end

  private
  def divide_by(a, to_num)
    (a..(to_num/a).to_i).map { |num| num * a }
  end
end
