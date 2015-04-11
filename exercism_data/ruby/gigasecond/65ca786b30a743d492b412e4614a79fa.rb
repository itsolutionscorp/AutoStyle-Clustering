# Calculate one's Gigasecond b'day

class Gigasecond

  SECONDS_PER_DAY = 86400

  def initialize(birth_date)
    @birth_date = birth_date
    @giga_second_in_days = (10 ** 9) / SECONDS_PER_DAY
  end

  # Return Gigasecond date from b'day
  def date
    @birth_date + @giga_second_in_days
  end

end
