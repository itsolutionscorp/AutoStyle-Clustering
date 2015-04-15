GIGASECONDS=10**9

class Gigasecond
  def self.from(date)
    (date.to_time + GIGASECONDS).to_date
  end
end
