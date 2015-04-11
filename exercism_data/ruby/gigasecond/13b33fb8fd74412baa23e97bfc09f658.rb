class Gigasecond

  def self.from(date)
    (date.to_time + 10e8).to_date
  end

end
