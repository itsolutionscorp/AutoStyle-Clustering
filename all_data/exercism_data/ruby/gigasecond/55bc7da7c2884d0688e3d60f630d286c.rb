class Gigasecond
  ONE_GIGASECOND = 10**9
  FROM_EPOCH_FORMAT = '%s'

  def self.from(date)
    seconds_from_epoch = date.strftime(FROM_EPOCH_FORMAT).to_i
    anniversary = seconds_from_epoch + (ONE_GIGASECOND)
    Date.strptime(anniversary.to_s, FROM_EPOCH_FORMAT)
  end
end
