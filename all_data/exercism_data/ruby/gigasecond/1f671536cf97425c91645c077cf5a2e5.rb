class Gigasecond
    def initialize(date)
        @date = date
        @time = Time.new(date.year,date.month,date.day)
    end

    def date
        new_time = @time + 10**9
        new_date = Date.new(new_time.year,new_time.month,new_time.day)
    end
end
