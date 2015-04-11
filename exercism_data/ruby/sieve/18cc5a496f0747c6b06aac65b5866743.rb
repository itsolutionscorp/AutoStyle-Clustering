class Sieve
  def initialize(limit)
    @list = (2..limit).to_a
  end
  
  def primes
    primes_list = []
    while @list.length > 0 do
    primes_list << @list.first
    front = @list.first
    @list.delete_if { |num| num % front == 0 }
    end
    primes_list
  end
end
