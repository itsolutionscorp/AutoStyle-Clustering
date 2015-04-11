module Gigasecond
  module_function
  
  def from(time)
    Time.at(time.to_i + 10**9).utc
  end  

end
