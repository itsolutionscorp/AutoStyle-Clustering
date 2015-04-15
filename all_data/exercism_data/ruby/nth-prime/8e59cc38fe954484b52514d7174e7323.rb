class Prime
  def self.nth(num)
    @nth_prime = num
    if num == 0
      raise ArgumentError
    else
      sieve
    end
  end

private

  def self.sieve
    @range ||= 1000
    @list = (2..@range).to_a
    primes_list = []
    while @list.length > 0 do
      if primes_list.length == @nth_prime
        break
      end
      primes_list << @list.first
      front = @list.first
      @list.delete_if { |num| num % front == 0 }
    end

    if primes_list.length == @nth_prime
      primes_list.pop
    else
      @range += 10000
      self.sieve
    end
  end
end
