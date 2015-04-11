require 'set'

module Prime

  def self.nth(n)
    PrimeSieve.new(upper_bound(n)).drop(n-1).first
  end

  def self.upper_bound(n)
    return 20 if n <= 6
    n * (Math.log(n) + Math.log(Math.log(n)))
  end

  class PrimeSieve
    include Enumerable

    def initialize(max)
      @max = max
    end

    def each
      yield 2

      odd_composites = Set.new
      (3..@max).step(2) do |n|
        if !odd_composites.include?(n)
          yield n
          (n**2..@max).step(n) do |i|
            odd_composites << i
          end
        end
      end
    end
  end
end
