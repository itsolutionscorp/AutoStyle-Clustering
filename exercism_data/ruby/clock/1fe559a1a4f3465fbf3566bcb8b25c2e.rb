class Clock
  attr_reader :total_mins

  def initialize(total_mins)
    @total_mins = total_mins % (24 * 60)
    @hours = @total_mins / 60
    @mins = @total_mins % 60
  end

  def self.at(input_hours, input_mins=0)
    new(input_hours * 60 + input_mins)
  end

  def to_s
    "%02d:%02d" % [@hours, @mins]
  end

  def +(input_mins)
    self.class.new(@total_mins + input_mins)
  end

  def -(input_mins)
    self.+(-input_mins)
  end

  def ==(clock)
    @total_mins == clock.total_mins
  end

end
