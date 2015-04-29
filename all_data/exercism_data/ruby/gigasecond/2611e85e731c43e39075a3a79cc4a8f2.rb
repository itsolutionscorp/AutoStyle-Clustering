class Gigasecond
    #defining a gigasecond variable
    GIGASECOND = 10**9
    #defining how many seconds we have in a day
    SECONDS_IN_A_DAY = 60 * 60 * 24
    #Calculating how many days we do have within a gigasecond
    DAYS_TO_SUM = (GIGASECOND)/(SECONDS_IN_A_DAY)
  def self.from(birthDay)
    #Summing the days in a gigasecond to the birthday. 
    #We know that it is an Date method format so the sum of days is available  
    birthDay + DAYS_TO_SUM
  end
end
