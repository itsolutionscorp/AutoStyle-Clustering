class Gigasecond
    def self.from(date)
        u = date.strftime "%-s"
        DateTime.strptime((u.to_i + 10**9).to_s, "%s").to_date
    end
end
