class Clock
  attr_reader :total_minutes

  def self.at(hr, min = 0)
    new(hr, min)
  end

  def initialize(hr, min)
    @total_minutes = hr * 60 + min
  end

  def +(minutes)
    hr, min = minutes.divmod(60)
    @total_minutes += (min + hr * 60)
    self
  end

  def -(minutes)
    hr, min = minutes.divmod(60)
    @total_minutes -= (min + hr * 60)
    self
  end

  def to_s
    hr,min = @total_minutes.divmod(60)
    wrap_hr = hr % 24
    wrap_min = min % 60
    wrap_hr.to_s.rjust(2, "0") + ":" + wrap_min.to_s.rjust(2,"0")
  end

  def ==(other_clock)
    other_clock.total_minutes == @total_minutes
  end

  private

end
