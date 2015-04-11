class Gigasecond
  def Gigasecond.from(date)
    Time.at(date.to_time.to_i + 10**9).to_date
  end
end
