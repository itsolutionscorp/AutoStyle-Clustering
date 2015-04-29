require 'date'
class Gigasecond
  def self.from(date)
    DateTime.strptime((date.to_time.to_i + 10**9).to_s, '%s').to_date + 1
  end
end
