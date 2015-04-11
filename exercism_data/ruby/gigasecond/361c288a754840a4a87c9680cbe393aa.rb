class Gigasecond
    def self.from(date)
        @unixTime = date.strftime("%s")
        puts "/n UNIX TIME:" + @unixTime
        @addGig = @unixTime.to_i+1000000000
        @newDate = Date.strptime(@addGig.to_s, "%s")
        return @newDate

    end

end
