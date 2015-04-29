class Gigasecond
  def self.from(date)
    date_in_seconds = date.strftime("%s").to_i + 10**9
    Date.strptime(date_in_seconds.to_s,"%s")
  end
end
