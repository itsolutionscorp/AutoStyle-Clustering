class Gigasecond

  GIGASECOND = 10 ** 9

  def self.from(from_date)
    seconds_to_date(date_to_seconds(from_date) + GIGASECOND)
  end

  private

  def self.date_to_seconds(date)
    date.strftime('%s').to_i
  end

  def self.seconds_to_date(seconds)
    Date.strptime(seconds.to_s, '%s')
  end
end
