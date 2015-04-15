class Gigasecond
  def self.from(birthdate)
    birthdate + gigasecond_in_days
  end

  private

  def self.gigasecond_in_days
    10**9 / 60 / 60 / 24
  end
end
