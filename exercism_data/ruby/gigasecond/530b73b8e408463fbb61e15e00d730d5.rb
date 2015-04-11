class Gigasecond
  require 'date'

  def self.from(dob)
    epoch = dob.to_time + 1e9
    Time.at(epoch).to_date
  end

end
