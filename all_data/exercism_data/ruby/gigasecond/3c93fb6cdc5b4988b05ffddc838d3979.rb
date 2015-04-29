Gigasecond = Struct.new(:birth) do
  def date
    birth + (10**9 / seconds_per_day)
  end

  private
  def seconds_per_day
    24 * 3600
  end
end
