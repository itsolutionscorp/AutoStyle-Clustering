class Gigasecond
  def self.from(dob)
    (dob.to_time + 10**9).to_date
  end
end
