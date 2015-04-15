class Clock

  attr_reader :hh, :mm

  def initialize(hh, mm)
    @hh = hh
    @mm = mm
  end

  def self.at(hh, mm=0)
    Clock.new(hh, mm)
  end

  def to_s
    sprintf("%02d:%02d", hh, mm)
  end

  def + (minutes)
    new_time(minutes)
  end

  def - (minutes)
    new_time(-minutes)
  end

  def == (other)
    @hh == other.hh && @mm == other.mm
  end

  private

  def new_time(minutes)
    timechange = (@mm + minutes).divmod(60)
    Clock.new((@hh += timechange[0])%24, timechange[1])
  end

end
