class Gigasecond
  def self.from(date_of_birth)
    Date.parse((date_of_birth.to_time + 10**9).to_s)
  end
end
