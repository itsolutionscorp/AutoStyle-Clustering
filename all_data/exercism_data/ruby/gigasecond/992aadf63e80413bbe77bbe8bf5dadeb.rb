#Assumes user_time is a valid Time object, self.from's purpose is to 
#find the gigasecond anniversary from the time object user_time
class Gigasecond
  def self.from (user_time)
  	user_time + (10**9)
  end
end
