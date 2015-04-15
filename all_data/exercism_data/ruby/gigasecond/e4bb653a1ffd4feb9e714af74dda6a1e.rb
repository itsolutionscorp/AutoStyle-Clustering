class Gigasecond
   def self.from(from)
     time = from
     time = time + 1000000000
     #time = Time.utc(2043, 1, 1, 1, 46, 40)
     return time.utc()
   end
end
