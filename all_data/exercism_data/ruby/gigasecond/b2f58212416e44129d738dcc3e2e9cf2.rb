class Gigasecond
  def self.from(date)
    @date = date
    gs = add_gigasecond
    convert_to_date(gs)
  end

  private
  def self.convert_to_date(time)
    time.to_date
  end

  def self.add_gigasecond
    @date.to_time + 10**9
  end
end
