class Gigasecond
  Gsdays = 10**9 / 86400
  
  def initialize(dob)
    @dob = dob
  end
  
  def date
    @dob + Gsdays
  end
end
