class Gigasecond
  def self.from(dob)
    return Time.at(dob.to_time.to_i + 10**9).to_date
  end
end
