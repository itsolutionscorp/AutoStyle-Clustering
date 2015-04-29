#Gigasecond test, calculates 1 gigasecond after input time
#I feel I must be missing something.

class Gigasecond
  def self.from(input_time)
    #Return  time 10 billion seconds after input_time
    return input_time + 10**9
  end
end
