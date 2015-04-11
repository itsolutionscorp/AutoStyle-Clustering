Gigasecond = Struct.new(:birthdate) do

  def date
    birthdate + gigaseconds_in_days
  end

  def gigaseconds_in_days
    10**9 / (seconds_in_a_day)
  end

  def seconds_in_a_day
    60 * 60 * 24
  end

end
