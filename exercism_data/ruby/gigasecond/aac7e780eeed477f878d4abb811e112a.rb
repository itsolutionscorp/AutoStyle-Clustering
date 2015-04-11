class Gigasecond
  def initialize initial_date
    @_initial_date= initial_date
  end

  def date
    @_initial_date + gigasecond_in_days
  end

  private

  def gigasecond_in_days
    (10**9) / seconds_per_day
  end

  def seconds_per_day
    60 * 60 * 24
  end

end
