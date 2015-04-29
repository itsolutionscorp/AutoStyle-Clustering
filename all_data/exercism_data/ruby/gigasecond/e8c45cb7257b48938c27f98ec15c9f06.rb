class Gigasecond

  ONE_GIGASECOND = 1000000000

  def self.from(date)
    Time.at(date.to_time + ONE_GIGASECOND).to_date
  end

end
