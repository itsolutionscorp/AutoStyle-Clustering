class Gigasecond
  ONE_GIGASECOND = (10**9)

  def self.from(date_of_birth)
    (date_of_birth.to_time + ONE_GIGASECOND).to_date
  end
end
