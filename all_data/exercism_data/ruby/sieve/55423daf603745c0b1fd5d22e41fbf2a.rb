require 'prime'

class Sieve
  def initialize(up_to)
    @up_to = up_to
  end

  def primes
    Prime::EratosthenesGenerator.new.take_while {|i| i <= @up_to}
  end
end
