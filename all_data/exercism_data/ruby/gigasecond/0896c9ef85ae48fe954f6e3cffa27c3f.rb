class Gigasecond
  def self.from(date)
    today_sec     = Date.today.to_time.to_i
    elapsed_sec   = today_sec - date.to_time.to_i
    remaining_sec = 1e9.to_i - elapsed_sec

    gigasecond    = today_sec + remaining_sec
    DateTime.strptime(gigasecond.to_s, '%s').to_date
  end
end
