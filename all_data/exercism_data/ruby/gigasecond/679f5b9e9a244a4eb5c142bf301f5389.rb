class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    time = @date.to_time
    time = time.to_i
    g_annivesary_time = time + 1000000000
    g_annivesary_time = Time.at(g_annivesary_time)

    g_annivesary_date = (g_annivesary_time.strftime("%Y-%-m-%-d"))
    g_annivesary_date = Date.new(g_annivesary_date.to_i)
    g_annivesary_date
  end

end
