module GuyCountingBottles
  def outta_bottles?
    @outta_bottles ||= ->(bottle_count) { bottle_count.zero? }
  end

  def last_bottle?
    @last_bottle ||= ->(bottle_count) { bottle_count == 1 }
  end
end

class Beer

  include GuyCountingBottles

  def initialize
    @bottles = WallBottles.new
  end

  def verse bottles_on_the_wall
    bottles.count = bottles_on_the_wall
    look_at_wall(bottles) +
      take_bottle(bottles) +
      sum_up(bottles.drink)
  end

  def sing bottles_at_start, bottles_at_end=0
    bottle_list(bottles_at_start, bottles_at_end).map do |bottles|
      verse bottles
    end.join("\n") << "\n"
  end

  private

  def bottles
    @bottles
  end

  def bottle_list(bottles_at_start, bottles_at_end)
    (bottles_at_end..bottles_at_start).to_a.reverse
  end

  def look_at_wall(bottles)
    wall = "#{bottles} of beer on the wall, #{bottles} of beer.\n"
    wall.capitalize
  end

  def take_bottle(bottles)
    case bottles
    when outta_bottles?
      "Go to the store and buy some more, "
    when last_bottle?
      "Take it down and pass it around, "
    else
      "Take one down and pass it around, "
    end
  end

  def sum_up(bottles)
    "#{bottles} of beer on the wall.\n"
  end

end

class WallBottles
  include GuyCountingBottles

  def count= bottle_count
    @bottles = bottle_count
  end

  def drink
    @bottles = zero? ? 99 : bottles - 1
    self
  end

  def to_s
    case bottles
    when outta_bottles?
      "no more bottles"
    when last_bottle?
      "1 bottle"
    else
      "#{bottles} bottles"
    end
  end

  def zero?
    bottles.zero?
  end

  def == num
    bottles == num
  end

  private

  def bottles
    @bottles
  end
end
