class Gigasecond
  def self.from(birthDay)
    #defining a gigasecond variable
    gigasecond = 10**9
    #defining how many seconds we have in a day
    seconds_in_a_day = 60 * 60 * 24
    #Calculating how many days we do have within a gigasecond
    days_to_sum = (gigasecond)/(seconds_in_a_day)
    #Summing the days in a gigasecond to the birthday. 
    #We know that it is an Date method format so the sum of days is available
    birthDay + days_to_sum
  end
end
