class Gigasecond
  def self.from(date)
    # 1 gigasecond = 1,000,000,000 seconds
    # 1,000,000,000 seconds / 60 seconds = 16666666.66 minutes
    # 16666666.66 minutes / 60 minutes = 277777.77 hours
    # 277777.77 hours / 24 hours = 11574.1 days OR 11574 days.
    gigasecond_in_days = 11574
    date + gigasecond_in_days
  end
end
