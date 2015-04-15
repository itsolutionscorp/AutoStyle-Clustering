class Squares

    def initialize(int_in)
        @int_in = int_in  
    end

    def square_of_sums
        calc_values(false)**2
    end

    def sum_of_squares
        calc_values(true)
    end

    def calc_values(squares_flag)
        exponent = 1 
        if squares_flag == true
           exponent = 2
        end
        sum = 0
        1.upto(@int_in) do |x|
          sum += x**exponent
        end 
        sum
    end

    def difference
        square_of_sums - sum_of_squares 
    end

end

=begin
    def square_of_sums
        sum = 0     
        1.upto(@int_in) do |x|
           sum += x
        end 
        sum**2 
    end

    def sum_of_squares
        sum = 0
        1.upto(@int_in) do |x|
          sum += x**2
        end 
        sum
    end
=end
