class Gigasecond
  One_gigasecond = 1e+9
  private_constant :One_gigasecond
  
  def self.from(birth_date)
    birth_date + One_gigasecond
  end  
end
