class Gigasecond

  def self.from(date)
    date_in_seconds = date.strftime('%s').to_i
    one_gigasecond = 1000000000
    future_date = (date_in_seconds + one_gigasecond).to_s

    Date.strptime(future_date, '%s')
  end
end
