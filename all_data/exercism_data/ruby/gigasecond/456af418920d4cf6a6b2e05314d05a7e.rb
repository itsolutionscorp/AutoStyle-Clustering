class Gigasecond

  def self.from(date)
    date + number_of_days
  end

  def self.number_of_days
    minutes = (10**9)/60
    hours   = minutes/60
    days    = hours/24
  end

end
