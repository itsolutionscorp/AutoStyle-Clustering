class Gigasecond
  def self.from (date)
    return (date.to_time + 10**9).to_date
  end
end
