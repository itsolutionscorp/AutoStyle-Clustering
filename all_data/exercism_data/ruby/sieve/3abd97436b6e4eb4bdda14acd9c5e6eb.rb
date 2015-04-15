class Sieve

  def initialize(num)
    @sieve = Array.new(num)
    fill_sieve_upto(num)
  end

  def fill_sieve_upto(num)
    i = 2
    while i < num
      fill_multiples_of(i) 
      i = get_next(i)
    end
  end

  def get_next(i)
    i += 1 while @sieve[i]
    i
  end

  def fill_multiples_of(i) 
    x = 1
    len = @sieve.length
    while x*i < len && i > 1
      @sieve[x*i] = i 
      x += 1
    end
  end

  def primes
    primes = []
    @sieve.each_with_index do |val,index|
      primes << index if val == index
    end
    primes
  end

end
