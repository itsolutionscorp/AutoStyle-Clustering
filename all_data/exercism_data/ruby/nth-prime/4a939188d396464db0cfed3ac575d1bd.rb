require 'prime'
class Prime
  class << self
    def nth n
      raise ArgumentError unless n > 0
      sieve = Prime::EratosthenesGenerator.new
      n.times.inject(0) { |memo| memo = sieve.next }
    end
  end
end
