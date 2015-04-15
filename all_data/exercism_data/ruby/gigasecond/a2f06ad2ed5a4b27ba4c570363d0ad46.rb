class Gigasecond
  def self.from(bday)
    t1 = bday + (10**9)
    if t1.dst?
      t1 = t1 - 3600
    end
    return t1
  end
end
