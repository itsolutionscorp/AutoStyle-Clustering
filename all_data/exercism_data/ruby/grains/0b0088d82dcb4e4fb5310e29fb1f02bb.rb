class Grains
    attr_accessor :total
    
    def initialize
        @squares = Array.new
        @total = 0;
        grains = 1
        (1..64).each {|n|
            if n > 1
                grains = grains * 2
            end
            @squares[n] = grains
            @total += grains
        }
    end
    
    def square(sq)
        @squares[sq]
    end
end
