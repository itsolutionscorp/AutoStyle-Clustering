class Gigasecond

  def self.from(date)
    gigasecond_in_days = 1_000_000_000/60/60/24

    new_date = date + gigasecond_in_days
  end
end
