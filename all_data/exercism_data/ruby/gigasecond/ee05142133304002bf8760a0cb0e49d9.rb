class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    g_time = @date.to_time + 1000000000
    g_date = Time.at(g_time).to_date

    g_date
  end

end
