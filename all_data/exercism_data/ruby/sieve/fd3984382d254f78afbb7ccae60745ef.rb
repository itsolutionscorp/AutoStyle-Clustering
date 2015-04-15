class Sieve
  attr_accessor :num_list

  def initialize(max)
    @num_list = (2..max)
  end

  def primes
    prime_list = num_list.to_a
    num_list.each do |num|
      num_list.each_with_index do |poss, index|
        next if poss <= num
        prime_list[index] = nil if poss % num == 0
      end
    end
    prime_list.compact
  end
end
