class Gigasecond
  def self.from(date)
    date_from_epoch = date.strftime('%s').to_i
    anniversary = date_from_epoch + (10**9)
    Date.strptime(anniversary.to_s, '%s')
  end
end
