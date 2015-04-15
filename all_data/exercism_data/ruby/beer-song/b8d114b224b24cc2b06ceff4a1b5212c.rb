class Beer
  attr_accessor :bottle_count

  def sing(first, last=0)
    first.downto(last).inject("") do |memo, num|
      memo << verse(num) + "\n"
    end
  end

  def verse(num)
    @bottle_count = num
    [line1, line2, action, remaining_bottles].join()
  end

private
  def line1
    pluralize_bottles(bottle_count).capitalize + " of beer on the wall, "
  end

  def line2
    pluralize_bottles(bottle_count) + " of beer.\n"
  end

  def action
    if bottle_count == 0
      "Go to the store and buy some more, "
    else
      "Take #{humanize_bottles} down and pass it around, "
    end
  end

  def remaining_bottles
    reset_bottles if bottle_count == 0
    "#{pluralize_bottles(bottle_count-1)} of beer on the wall.\n"
  end

  def reset_bottles
    @bottle_count = 100
  end

  def humanize_bottles
    bottle_count == 1 ? 'it' : 'one'
  end

  def pluralize_bottles(count)
    if count == 0
      "no more bottles"
    elsif count == 1
      "1 bottle"
    else
      "#{count} bottles"
    end
  end


end
