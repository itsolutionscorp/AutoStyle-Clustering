class Gigasecond
  def self.from(date)
    if date.class == Date
      time = self.to_time(date)
    else
      time = date
    end

    gigasecond_time = time + 10**9
    
    #convert time to date
    Date.new(gigasecond_time.year, gigasecond_time.month, gigasecond_time.day)
  end

  def self.to_time(date)
    year = date.year
    month = date.month
    day = date.day

    time = Time.mktime(year, month, day)
  end
end
# 
#   class Date
#   def g_second(year,month,day)
#     gs= Time.mktime(year,month,day)
#     puts later_time = gs + 10**9
#   end
# end
# 
# foo = Date.new
# foo.g_second(2011,4,25)
