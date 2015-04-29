require 'active_support'
require 'active_support/core_ext'

class Sieve
  attr_reader :primes

  def initialize(up_to)
    fail ArgumentError, 'Argument given not an integer', caller unless up_to.is_a? Integer
    @primes = generate_primes(up_to)
  end

  private

  def reject_nums_divisible_by(integer, arr)
    arr.reject do |num|
      num.modulo(integer) == 0 unless num == integer
    end
  end

  def generate_primes(upper_range)
    @number_list = (2..upper_range).to_a
    @only_primes = @number_list.deep_dup

    @number_list.each do |number|
      @only_primes = reject_nums_divisible_by(number, @only_primes)
    end

    @only_primes
  end
end
