class Gigasecond 
  
  def self.from(x)

    if x.is_a?(Date)
      x + 11574
    elsif x.is_a?(Time)
      Date.parse((x + (10**9)).to_s)
    end
  end
end
