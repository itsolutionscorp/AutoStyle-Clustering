class Gigasecond
  GIGASECOND = 1_000_000_000
  
  def self.from(date_of_birth)
    date_of_birth + GIGASECOND
  end
end
