class Gigasecond
  def self.from(birth)
  	birth = birth.to_time.to_i
  	now = Time.now.to_i
  	age = (birth - now).abs
  	gs_bday = Time.at(now + (10**9 - age)).to_date
  end
end
