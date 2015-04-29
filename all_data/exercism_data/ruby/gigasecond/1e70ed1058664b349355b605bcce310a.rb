class Gigasecond
    def self.from(date)
        return Time.at(date.to_time.to_i + 1e9).to_date
    end
end
