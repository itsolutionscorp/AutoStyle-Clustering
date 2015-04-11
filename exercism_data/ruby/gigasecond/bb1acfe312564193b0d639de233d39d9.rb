class Gigasecond
  def self.from( starting_date )
    starting_date + one_gigasecond_in_days
  end

  def self.one_gigasecond_in_days
    seconds = 1000000000.0
    minutes = seconds / 60.0
    hours   = minutes / 60.0
    days    = hours / 24.0
    days.to_i
  end
end
