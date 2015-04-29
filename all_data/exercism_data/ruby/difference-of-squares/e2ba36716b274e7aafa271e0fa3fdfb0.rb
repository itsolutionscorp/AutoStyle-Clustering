class Squares
    def initialize(aNumber)
        @enumeratorToCalc = 0.upto(aNumber)
    end

    def square_of_sums
        #Let's get rid of creating a temporary variable.
        # reduce allows me to do that, and in later cases, any accumulator
        # variables can be contained to the block.
        # I also noticed since this is an object with an instance,
        # I can set an instance variable to save things already calculated to
        # be called later again. Then the ||= sign allows me to either
        # lazily initate the instance variable, or call it if it's already
        # defined.
        @square_of_sums ||= @enumeratorToCalc.reduce(:+) ** 2
    end

    def sum_of_squares
        @sum_of_squares ||= @enumeratorToCalc.reduce {|accum, curNum| accum += curNum ** 2}
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
