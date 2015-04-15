class Gigasecond

  def self.from(input)
    gigasecond = input.to_time.to_i + (10**9)
    Time.at(gigasecond).to_date
  end
  
end
