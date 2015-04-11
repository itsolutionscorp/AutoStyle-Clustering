class Gigasecond
  def self.from(d)
    return Date.parse(Time.at(d.strftime('%s').to_i + 10**9).strftime('%Y/%m/%d'))
  end
end
