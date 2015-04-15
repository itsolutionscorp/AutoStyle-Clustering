require 'time'

class Gigasecond
 def self.from time
 	sec = time.tv_sec #Convert time to seconds.
 	gs = 10 ** 9 #value of giga seconds
 	gb = sec + gs # approximate time in sec for your gigasecond birthday
 	Time.at(gb).utc # Time at gb.
 end
end
