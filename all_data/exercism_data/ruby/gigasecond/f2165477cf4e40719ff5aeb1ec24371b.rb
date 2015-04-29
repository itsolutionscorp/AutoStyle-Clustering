class Gigasecond
#Class is designed to return a Date object increased by one
#gigasecond to the calling method
  class << self
    def from ( user_time )
	  user_time + (10**9)
    end
  end
end
