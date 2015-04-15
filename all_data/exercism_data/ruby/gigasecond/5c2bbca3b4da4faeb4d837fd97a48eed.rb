class Gigasecond
  class << self
   def from(birthday)
    Time.at(birthday.to_time.to_i + 10**9).to_date
   end
  end
end
