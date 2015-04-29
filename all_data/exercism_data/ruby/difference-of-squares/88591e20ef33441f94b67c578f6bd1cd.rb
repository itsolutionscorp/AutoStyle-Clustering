class Squares
    def initialize(n)
        @limit = n
    end
    
    def square_of_sums
        sums ** 2
    end
    
    def sum_of_squares
        squares.reduce(:+)
    end
    
    def difference
        square_of_sums - sum_of_squares
    end
    
    private
    
    #Add range together
    def sums
        range.reduce(:+)
    end
    
    #Loop over range (because it's an Array) and return an array of their squares
    def squares
        range.map{|n| n**2 }
    end
    
    #Get range from 0 'upto' number being passed i.e. 0,1,2,3,4,5
    def range
        @range ||= 0.upto(@limit)
    end
end
