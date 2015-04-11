class Gigasecond
    ONE_GIGASECOND=10**9

    def self.from(date)
        (date.to_time + ONE_GIGASECOND).to_date
    end

end
