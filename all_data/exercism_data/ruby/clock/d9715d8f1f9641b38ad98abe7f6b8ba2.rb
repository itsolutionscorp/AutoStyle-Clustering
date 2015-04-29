class Clock
  attr_reader :hr, :min

  def self.at(hr,min = 0)
    new(hr,min)
  end

  def initialize(hr,min)
    @hr   = hr
    @min  = min
  end

  def to_s
    sprintf("%02d:%02d", hr, min)
  end

  def +(min)
    update_time(self.min + min)
    self
  end

  def -(min)
    update_time(self.min - min)
    self
  end

  def ==(clock)
    hr == clock.hr && min == clock.min
  end

  private

  def update_time(delta_min)
    while delta_min >= 60
      @hr += 1
      delta_min -= 60
    end

    while delta_min <= 0
      @hr -= 1
      delta_min += 60
    end

    @min = delta_min
    @hr  = hr % 24
  end

end
