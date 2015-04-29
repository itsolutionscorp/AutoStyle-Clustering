class Gigasecond

  def self.from(x)
    #convert from date to seconds and store as variable
    sec = x.to_time.tv_sec

    #add a gigasecond to it
    sec += 10**9

    #convert back into date
    Time.at(sec)
  end
end
