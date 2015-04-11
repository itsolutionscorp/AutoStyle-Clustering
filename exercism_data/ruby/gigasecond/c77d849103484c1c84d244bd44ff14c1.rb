class Gigasecond
  class << self
    GIGASECONDS = 10**9
    
    def from birth_date
      birth_date + GIGASECONDS
    end
  end
end
