class Gigasecond
  GIGASECOND = 1000000000

  def self.from(date_of_birth)
    (date_of_birth.to_time + GIGASECOND).to_date # need a time object to add seconds
  end
end
