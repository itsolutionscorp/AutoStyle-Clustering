class Clock

  attr_reader :mins

  def initialize(mins)
    @mins = mins
  end

  def self.at(hours, mins = 0)
    Clock.new(hours * 60 + mins)
  end

  def formatted_hours
    hours = (@mins / 60)
    hours = hours + 24 if hours <= 0
    hours = hours - 24 if hours >= 24 
    hours < 10 ? "0#{hours}" : "#{hours}"
  end

  def formatted_minutes
    mins = (@mins % 60)
    mins < 10 ? "0#{mins}" : "#{mins}"
  end

  def to_s
    formatted_hours + ":" + formatted_minutes
  end

  def +(addl_mins)
    Clock.new(@mins += addl_mins)
  end
  
  def -(addl_mins)
    Clock.new(@mins -= addl_mins)
  end

  def ==(clock)
    @mins == clock.mins
  end
end
