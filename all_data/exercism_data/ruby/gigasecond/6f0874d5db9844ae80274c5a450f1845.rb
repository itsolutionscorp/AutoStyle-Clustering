class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    @date +  gigasecond_in_days
  end

  def gigasecond_in_days
    # 1000000000 A Gigasecond
    # 86400 Gigasecond in a day (60seconds * 60minutes * 24hours)
    1000000000 / 86400 
  end

end
