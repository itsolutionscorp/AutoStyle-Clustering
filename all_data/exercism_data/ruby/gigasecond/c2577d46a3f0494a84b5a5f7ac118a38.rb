class Gigasecond
    attr_accessor :gigadate
    def initialize( birthday )
        gigaseconds = birthday.strftime("%s").to_i + 10**9
        @gigadate = DateTime.strptime(gigaseconds.to_s,"%s").to_date
    end

    def date
        @gigadate
    end
end
