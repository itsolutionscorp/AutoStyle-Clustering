GIGASECOND = 1000000000

class Gigasecond
  def self.from(date_object)
    # turn specific date object int0 seconds
    # add Gigasecond
    end_time = (date_object.to_time.to_i) + GIGASECOND  
    #turn end time back into date object 
    DateTime.strptime(end_time.to_s, '%s').to_date
  end
end
