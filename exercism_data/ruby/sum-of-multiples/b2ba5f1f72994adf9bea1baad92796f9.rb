class SumOfMultiples
    def self.to(n)
        new(3,5).to(n)
    end
    def initialize(*a)
        @a = a
    end
    def to(n)
        (1...n).to_a.reduce(0) { |s, j| s + (@a.any? { |i| j%i==0 } ? j : 0) }
    end
end
