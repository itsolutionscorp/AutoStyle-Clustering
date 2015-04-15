class Gigasecond
  def self.from(date)
    date + one_gigasecond_in_days
  end

  def self.one_gigasecond_in_days
    10**9 / 60 / 60 / 24
  end
end
