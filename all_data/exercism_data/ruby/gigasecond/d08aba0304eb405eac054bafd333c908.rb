class Gigasecond
  def self.from(param)
    if param.instance_of?(Date) 
      d = param + Rational(10**9, 24*60*60) 
    elsif param.instance_of?(Time)
      t = param + 10**9
      d = Date.parse(t.to_s)
    end
    return Date.parse(d.strftime("%Y-%m-%d 00:00:00"))
  end  
end
