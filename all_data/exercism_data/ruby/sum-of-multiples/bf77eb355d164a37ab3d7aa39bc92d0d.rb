class SumOfMultiples
    def self.to(n)
        new(3,5).to(n)
    end
    def initialize(*a)
        @a = a
    end
    def to(n)
        (2..n-1).to_a.reduce(0) { |s, j| s + (@a.reduce(1) { |y, i|  y * (j%i) }==0 ? j : 0) }
    end
end
