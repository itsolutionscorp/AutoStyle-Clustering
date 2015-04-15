class SumOfMultiples
    def initialize(*a)
        @a = a
    end
    def self.tots(n, a)
        s = 0
        (2..n-1).to_a.each do |j|
        	f = false
            a.each do |i| 
            	f = f || (j%i==0)
            end
            s += j if f
        end
        s
    end
    def self.to(n)
    	self.tots(n, [3,5])
    end
    def to(n)
    	self.class.tots(n, @a)
    end
end
