class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    g_time = @date.to_time + 1000000000
    puts g_time
  end

end


# converted gigasecond into day
