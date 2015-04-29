module Gigasecond
  def self.from(birthday)
    epoch = birthday.to_time.to_i
    Time.at(epoch + 10**9).to_date
  end
end
