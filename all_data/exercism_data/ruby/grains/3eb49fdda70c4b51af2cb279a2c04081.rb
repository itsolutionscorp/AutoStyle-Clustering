class Grains
    def initialize
        
    end
    def square(grain)
        2**(grain-1)
    end
    def total
        @total ||= (1..64).inject(0) do |sum, number|
          sum + square(number)
        end
    end
end
