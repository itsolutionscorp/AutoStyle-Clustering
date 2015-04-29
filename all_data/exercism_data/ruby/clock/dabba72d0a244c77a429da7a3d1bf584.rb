class Clock
  def self.to_s

    if (@min > 59)
      @hr += @min / 60
      @min = @min % 60
    end

    @hr = -1 + (@hr - 23) if (@hr > 23)

    @hr = (24 - 1).abs if @hr < 0

    hr = 
      (@hr > 9) ? "#{@hr.to_s}" : "0#{@hr.to_s}"

    min = 
      (@min > 9) ? "#{@min.to_s}" : "0#{@min.to_s}"

    return "#{hr}:#{min}"

  end

  attr_accessor :min, :hr

  def self.at(hr, min=0)
    @hr = hr
    @min = min
    return self
  end

  def self.+(num)
    @min += num
    return self
  end

  def self.-(num)
    if num > @min
      @hr -= 1
      num -= @min
      @min = (60 - num).abs
      @hr -= num / 60
    else 
      @min -= num
    end
    return self
  end

end



p Clock.at(8).to_s
