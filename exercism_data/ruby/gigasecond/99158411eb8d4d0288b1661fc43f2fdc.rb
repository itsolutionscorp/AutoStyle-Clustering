class Gigasecond
  def self.from start
    start + (10**9)/(24*60*60)  #start + gigasecond / number-seconds-day
  end
end
