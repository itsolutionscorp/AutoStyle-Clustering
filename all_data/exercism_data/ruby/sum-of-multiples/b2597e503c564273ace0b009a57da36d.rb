class SumOfMultiples
    def initialize(*a)
        @a = a
    end
    def self.tots(n, a)
        s = 0
        (2..n-1).to_a.each { |j| s += j if 0==a.reduce(1) { |y, i|  y * (j%i) }}
        s
    end
    def self.to(n)
    	self.tots(n, [3,5])
    end
    def to(n)
    	self.class.tots(n, @a)
    end
end
