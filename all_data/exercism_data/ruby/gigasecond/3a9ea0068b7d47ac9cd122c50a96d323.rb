# A gigasecond is one billion seconds.
class Gigasecond
  def self.from(date)
    (date.to_time + 10**9).to_date
  end
end
