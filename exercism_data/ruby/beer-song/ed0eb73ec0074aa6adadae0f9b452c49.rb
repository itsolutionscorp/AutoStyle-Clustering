class Beer

  def verse bottles_on_the_wall
    "#{look_at_wall(bottles_on_the_wall)}#{take_bottle(bottles_on_the_wall)}#{sum_up(bottles_on_the_wall - 1)}"
  end

  def sing bottles_at_start, bottles_at_end=0
    bottle_list(bottles_at_start, bottles_at_end).map do |bottles|
      verse bottles
    end.join("\n") << "\n"
  end

  private

  def bottle_list(bottles_at_start, bottles_at_end)
    (bottles_at_end..bottles_at_start).to_a.reverse
  end

  def look_at_wall(bottles)
    "#{anglicized_bottle_count(bottles)} of beer on the wall, #{anglicized_bottle_count(bottles)} of beer.\n".capitalize
  end

  def take_bottle(bottles)
    if bottles.zero?
      "Go to the store and buy some more, "
    elsif bottles == 1
      "Take it down and pass it around, "
    else
      "Take one down and pass it around, "
    end
  end

  def sum_up(bottles)
    bottles = 99 if bottles < 0
    "#{anglicized_bottle_count(bottles)} of beer on the wall.\n"
  end

  def anglicized_bottle_count(count)
    if count == 0
      "no more bottles"
    elsif count == 1
      "1 bottle"
    else
      "#{count} bottles"
    end
  end
end
