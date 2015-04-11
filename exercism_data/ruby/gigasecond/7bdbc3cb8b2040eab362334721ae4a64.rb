class Gigasecond

    def self.from(dFrom)
        Date.strptime((dFrom.strftime("%s").to_i + 10**9).to_s, "%s")
    end
end
