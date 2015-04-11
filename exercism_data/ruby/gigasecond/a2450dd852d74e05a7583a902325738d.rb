class Gigasecond

  def self.from(date)
    Time.at(convert_date_to_time(date).to_i + 10**9).to_date
  end

  def self.convert_date_to_time(date)
    Time.local(date.year, date.month, date.day)
  end

end
