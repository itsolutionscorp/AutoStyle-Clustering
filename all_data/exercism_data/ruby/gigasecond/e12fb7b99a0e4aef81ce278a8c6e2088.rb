class Gigasecond
  def self.from(input_date)
    Date.parse((input_date.to_time + 10**9).to_s)
  end
end
