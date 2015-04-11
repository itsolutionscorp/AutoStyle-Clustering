class Gigasecond
  def self.from(birthdate)
    gigas = self.expo_seconds(9)
    (birthdate.to_time + gigas).to_date
  end

  private

  def self.expo_seconds(n)
    10**n
  end
end
