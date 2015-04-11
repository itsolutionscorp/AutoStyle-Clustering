class Gigasecond

  def self.from(date_of_birth)
    (date_of_birth.to_time + 1000000000).to_date
  end

end
