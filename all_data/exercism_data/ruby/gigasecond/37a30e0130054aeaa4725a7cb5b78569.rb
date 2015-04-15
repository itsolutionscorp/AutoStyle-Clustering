class Gigasecond
  
  attr_reader :birthday
  
  def initialize(birthday)
    @birthday = birthday.to_time.to_i
  end
  
  def date
    Time.at(birthday + 10**9).to_date
  end
  
end
