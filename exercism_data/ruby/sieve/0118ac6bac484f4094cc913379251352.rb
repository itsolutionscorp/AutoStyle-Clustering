class Sieve

  def initialize(num)
    @num = num
  end

  def primes
    list = (2..@num).to_a
    2.upto(@num) do |n|
      list.delete_if { |i| i % n == 0 && i != n }
    end
    list
  end
end
