class Sieve
  def initialize(list)
    @list = (2..list).to_a
  end
  
  def primes
    primes_list = []
    until primes_list.include?(nil) do
    primes_list << @list.first
    front = @list.first
    @list.delete_if { |num| num % front == 0 }
    end
    primes_list.pop
    primes_list
  end
end
