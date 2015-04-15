class Squares
    def initialize(n)
        @n = n
        def self.square_of_sums
            i = 0
            output = 0
            while (i<=@n)
                output+=i
                i+=1
            end
            return (output**2)
        end
        def self.sum_of_squares
            i = 0
            output = 0
            while i<=@n
                output+=(i**2)
                i+=1
            end
            return output
        end
        def self.difference
            return self.square_of_sums-self.sum_of_squares
        end
    end
end
