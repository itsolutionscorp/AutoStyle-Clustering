class Gigasecond
    def self.from birthdate
        # Time.at(Time.new - birthdate).utc
        Time.at(birthdate + 10**9).utc
    end
end
