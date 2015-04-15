class Gigasecond
    attr_reader :time
    def initialize(date)
        @time = date.to_time
    end
    def date
        (@time + 1_000_000_000).to_date
    end
end
