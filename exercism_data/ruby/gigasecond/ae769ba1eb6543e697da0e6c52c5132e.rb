class Gigasecond  
  def initialize(birthday)
    @initdate=(birthday.to_time)+10**9
  end
  
  def date   
    @initdate.to_date
  end
end
