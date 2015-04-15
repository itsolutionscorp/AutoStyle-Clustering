class Gigasecond
  GIGASECOND = 10**9

    def self.from(arg)
      Time.at(arg.to_time.to_i + GIGASECOND).to_date
    end
end
