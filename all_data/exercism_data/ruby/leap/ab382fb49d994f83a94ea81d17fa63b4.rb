class Year
    def initialize(year)
        @year = year
    end

    def leap?
        divide_four? and (fourth_century? or not century?)
    end

    private

    def divide?(n)
        @year % n == 0
    end

    def divide_four?
        divide? 4
    end

    def century?
        divide? 100
    end

    def fourth_century?
        divide? 400
    end
end
