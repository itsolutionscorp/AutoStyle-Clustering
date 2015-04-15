class Gigasecond
  
  attr_reader :birthday
  
  GIGASECOND = 10**9
  
  def initialize(birthday)
    @birthday = birthday.to_time
  end
  
  def date
    (birthday + GIGASECOND).to_date
  end
  
end
