class Sieve
  def initialize(limit)
    @n = limit
  end

  def primes
    sieve @n
  end

  private
  def sieve(n)
    multiple = Hash.new{|multiple,integer| multiple[integer] = []}
    primes = []
    (2..n).each do |integer|
        if not multiple.include? integer
            primes << integer
            multiple[integer * integer] = [integer]
        else
	    multiple[integer].each do |prime|
		    multiple[prime + integer] << prime
            end
        end
        multiple.delete integer
        integer += 1
    end
    primes
  end
end
