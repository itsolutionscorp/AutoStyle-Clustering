class Squares
    def initialize(aNumber)
        @enumeratorToCalc = 0.upto(aNumber)
    end

    def square_of_sums
        returnNumber = 0
        @enumeratorToCalc.each {|i| returnNumber += i}
        returnNumber ** 2
    end

    def sum_of_squares
        returnNumber = 0
        @enumeratorToCalc.each {|i| returnNumber += i ** 2}
        returnNumber
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
