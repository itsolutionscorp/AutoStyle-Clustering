class Gigasecond
  require 'date'

  def self.from(dob)
    (dob.to_time + 1e9).to_date
  end

end
