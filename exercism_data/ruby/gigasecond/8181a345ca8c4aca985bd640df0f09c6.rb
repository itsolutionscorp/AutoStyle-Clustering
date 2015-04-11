class Gigasecond
  def self.from(date)
    timestamp = date.to_time.to_i + 10 ** 9
    Date.strptime(timestamp.to_s,'%s')
  end
end
