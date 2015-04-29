class SumOfMultiples
    
    def initialize( *multiples )
        @multiples = multiples
    end
    
    def to( limit )
        ( 1...limit ).to_a.reject do |x|
            !@multiples.any? do |m|
                x % m == 0
            end
        end.inject( 0, :+ )
    end
    
    def self.to( limit )
        SumOfMultiples.new( 3, 5 ).to limit
    end
end
