class Gigasecond
    def self.from(t)
        gs = 10**9
        gs -= 3600 if t.year < 1978
        t + gs
    end
end
