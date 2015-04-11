class Gigasecond
    
  def self.from(date)
    gs = 10**9 / 60 / 60 / 24
    date + gs
  end

end
