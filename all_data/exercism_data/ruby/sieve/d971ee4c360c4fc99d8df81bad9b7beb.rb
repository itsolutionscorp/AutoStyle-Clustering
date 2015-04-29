class Sieve

  def initialize max
    @max = max
  end


  def primes
    list = (2..@max).to_a
    i = 0
    while i < list.length
      # list.delete_if... iterates through entire list,
      # not just numbers greater than list[i]
      list[i+1..list.length].each do |n|
        list.delete(n) if n % list[i] == 0
      end
      i += 1
    end
    list
  end

end
