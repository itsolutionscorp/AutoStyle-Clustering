class Gigasecond
  BILLION = 1000000000
  def self.from(birth_date)
    self.calculate_gig_anniversary birth_date
  end

  private

  def self.calculate_gig_anniversary(birth_date)
    (birth_date.to_time + BILLION).to_date
  end
end
