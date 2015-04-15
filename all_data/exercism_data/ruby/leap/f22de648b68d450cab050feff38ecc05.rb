class Year

    def initialize(value)
        @year = value
    end

    def leap?
        leap = false
        if @year%4 == 0
            if @year%100 == 0
                if @year%400 == 0
                    leap = true
                end
            else
                leap = true
            end
        end
        leap
    end

end
