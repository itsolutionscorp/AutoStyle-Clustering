class Sieve
    def initialize( n )
        @n = n
    end
    
    def primes
    
        list = ( 2..@n ).to_a
        res = []
        
        # optimize by: actually having tuples and using i to calculate table
        # index. in the end, collect all the true's.

        while !list.empty?
            res << list.first
            list.reject! do |p| ( p % res.last ) == 0 end
        end
        
        res
    end
end
