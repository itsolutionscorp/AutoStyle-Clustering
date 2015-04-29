class Gigasecond
  
  def self.from(birth_date)
    date = birth_date.to_i + (10**9)
    Time.at(date)
  end
  
end
