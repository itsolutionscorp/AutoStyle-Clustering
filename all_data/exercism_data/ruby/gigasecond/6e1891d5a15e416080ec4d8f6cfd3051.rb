class Gigasecond
  def self.from(day)
    new = day.strftime('%s').to_i + 10**9
    Date.strptime(new.to_s, '%s')
  end
end
