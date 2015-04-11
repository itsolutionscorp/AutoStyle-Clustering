module Gigasecond

  def self.from(birth_date)
    Time.at(birth_date.to_time.to_i + 10**9).to_date
  end

end
