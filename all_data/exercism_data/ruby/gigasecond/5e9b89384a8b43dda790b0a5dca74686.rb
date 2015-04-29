class Gigasecond
  @giga = 10**9

  def self.from(date)
    time = date.to_time
    new_date = time + @giga
    new_date.to_date
  end
end
