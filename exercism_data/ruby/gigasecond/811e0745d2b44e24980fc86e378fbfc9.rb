class Gigasecond
  def self.from(birth)
  	(birth.to_time + 10**9).to_date
  end
end
