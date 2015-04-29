require 'date'
require 'time'

class Gigasecond

  Gigasecond=10**9
  def self.from(my_date)
  number_of_days= (((Gigasecond/60.0)/60.0)/24.0)

  my_date+= number_of_days
  Date.strptime(my_date.to_s, '%Y-%m-%d')

  #Below is v1, drop everything to seconds, add and convert
  #Date.strptime( my_date+= number_of_days, '%Y-%m-%d')
  #total_seconds=(my_date.to_time.to_i + Gigasecond).to_s  #sum of date converted to seconds plus gigaseconds
  #my_date= DateTime.strptime(total_seconds,'%s').to_s #Convert to date using seconds (need time to create this)
 # Date.strptime(my_date.to_s, '%Y-%m-%d')  #Format Date from full time

  #Below is v2, which is far more concise, not nearly as readible
  #Date.strptime(DateTime.strptime((my_date.to_time.to_i + Gigasecond).to_s,'%s').to_s, '%Y-%m-%d')

  end #End from
end #Class
