require 'date'
class Meetup
  @@wds = {
    :sunday => 0,
    :monday => 1,
    :tuesday => 2,
    :wednesday => 3,
    :thursday => 4,
    :friday => 5,
    :saturday => 6
  }
  def initialize (m, y)
    @m = m
    @y = y
    @date = Date.new(y, m, 1)
  end
  def day (wd, sd)
    @date += 1 while @date.wday != @@wds[wd]
    case sd
    when :first
      @date
    when :second
      @date + 7
    when :third
      @date + 14
    when :fourth
      @date + 21
    when :last
      (@date + 28).mon == @m ? @date + 28 : @date + 21
    when :teenth
      @date += 7 while @date.mday < 13
      @date
    end
  end
end
