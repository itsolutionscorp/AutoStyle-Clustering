class SumOfMultiples
    def initialize(*a)
        @a = a
    end
    def self.tots(n, a)
        (2..n-1).to_a.reduce(0) { |s, j| s + (a.reduce(1) { |y, i|  y * (j%i) }==0 ? j : 0)}
    end
    def self.to(n)
    	self.tots(n, [3,5])
    end
    def to(n)
    	self.class.tots(n, @a)
    end
end
