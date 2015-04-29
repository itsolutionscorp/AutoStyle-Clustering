class Gigasecond

  GIGASECOND = 10**9

  def self.from(birth_date)

    seconds_since_birth = birth_date.to_time.to_i + GIGASECOND
    Time.at(seconds_since_birth).to_date

  end  

end
