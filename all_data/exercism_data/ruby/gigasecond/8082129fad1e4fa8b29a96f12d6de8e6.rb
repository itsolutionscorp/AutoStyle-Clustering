class Gigasecond
  require 'date'
  require 'time'

  # require 'pry'

  @number_of_seconds_to_add = 10**9

  def self.from(input_date)
    
    input_date_as_Time = input_date.to_time
    input_date_as_seconds_integer = input_date_as_Time.tv_sec
    gs_anniversary_as_seconds_integer = input_date_as_seconds_integer + @number_of_seconds_to_add
    
    gs_anniversary_as_Time = Time.at(gs_anniversary_as_seconds_integer)
    gs_anniversary_as_Date = gs_anniversary_as_Time.to_date

    # return gs_anniversary_as_Date
  end

end
