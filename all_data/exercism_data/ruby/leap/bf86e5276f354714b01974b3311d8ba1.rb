class Year
    def self.leap?(year)
        Year.new(year).leap?
    end
    def initialize(year)
        @year = year
    end
    def leap?
        divisible_by_4? and not 
            (divisible_by_100? and not divisible_by_400?)
    end
    private
    def divisible_by_100?
        @year % 100 == 0
    end
    def divisible_by_4?
        @year % 4 == 0
    end
    def divisible_by_400?
        @year % 400 == 0
    end
end
