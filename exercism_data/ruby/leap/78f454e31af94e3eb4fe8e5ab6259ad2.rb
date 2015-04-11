class Year

    attr_reader :year

    def initialize y
        @year = y
    end

    def leap?
        return false if year % 4 != 0
        return false if year % 100 == 0 and year % 400 != 0
        return true
    end

end
