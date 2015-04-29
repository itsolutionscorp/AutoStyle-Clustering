class Gigasecond
  def self.from(date)
    return Date.strptime((date.strftime('%s').to_i + 1000000000).to_s, '%s')
  end
end
