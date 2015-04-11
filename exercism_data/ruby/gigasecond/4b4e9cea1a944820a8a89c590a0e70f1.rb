class Gigasecond

    def initialize(day_born)
      @starting_time = day_born.to_time
    end

    def date
      giga_date = @starting_time + (10**9)
      return giga_date.to_date
    end

end
