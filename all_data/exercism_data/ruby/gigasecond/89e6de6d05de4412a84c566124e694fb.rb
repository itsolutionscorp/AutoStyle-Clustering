class Gigasecond

  TOTALSECONDS = 1000000000
  
  def self.from(birthday_date)
    Time.at(birthday_date.to_i + TOTALSECONDS)
  end

end
