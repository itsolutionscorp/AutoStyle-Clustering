class Gigasecond

  GIGASECOND = 10 ** 9

  def self.from(date_of_birth)
    (date_of_birth.to_time + GIGASECOND).to_date
  end

end
