class Gigasecond

  def self.from(birth_date)
    (birth_date.to_time + 1000000000).to_date
  end

end
