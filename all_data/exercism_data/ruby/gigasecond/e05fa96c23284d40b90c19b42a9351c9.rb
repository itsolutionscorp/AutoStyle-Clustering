# class to compute gigaseconds from a date
class Gigasecond
  def self.from(date)
    date + 10**9
  end
end
