class Gigasecond

  def self.from(start_date)
    (start_date.to_time + 1000000000).to_date
  end

end


# One gigasecond = 1,000,000,000 seconds
# One day = 24 hrs * 60 min * 60 sec = 86400
# One gigasecond / one day = 11574
# .to_time, to_date, Date.new
