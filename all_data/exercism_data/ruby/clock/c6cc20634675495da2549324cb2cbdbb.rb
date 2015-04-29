class Clock
  def self.at(hours, minutes=0)
    self.new(hours, minutes)
  end

  def initialize(hours,minutes)
    @hours = hours
    @minutes = minutes
  end

  def ==(other)
    self.to_s == other.to_s
  end

  def +(minutes)
    additional_hours, @minutes = (@minutes + minutes).divmod(60)
    @hours = (@hours + additional_hours) % 24
    self
  end

  def -(minutes)
    self.+(-minutes)
  end

  def to_s
    hours + ':' + minutes
  end

  private
    def hours
      @hours < 10 ? "0#{@hours}" : "#{@hours}"
    end

    def minutes
      @minutes < 10 ? "0#{@minutes}" : "#{@minutes}"
    end
end
