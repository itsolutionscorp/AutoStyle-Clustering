class Gigasecond

  def self.from from_date
    gs = 10**9/60/60/24
    d = from_date + gs
  end 

end
