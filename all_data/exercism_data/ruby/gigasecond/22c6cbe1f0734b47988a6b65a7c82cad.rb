class Gigasecond
  def self.from my_date
    (my_date.to_time + 10**9).to_date
  end
end
