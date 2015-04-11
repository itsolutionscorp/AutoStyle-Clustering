class Beer
  attr_reader :num_bottles

  def verse(num_bottles)
    @num_bottles = num_bottles

    lines = []
    lines << on_the_wall
    lines << pass_it_around
    lines.join("\n") + "\n"
  end

  def sing(starting, ending = 0)
    starting.downto(ending).each_with_object([]) do |num, memo|
      memo << verse(num)
    end.join("\n") + "\n"
  end

private
  def on_the_wall
    "#{x_bottles(num_bottles)} on the wall, #{x_bottles(num_bottles)}.".capitalize
  end

  def pass_it_around
    (no_more_bottles? ? go_to_the_store : take_one_down) + whatever_is_left
  end

  def whatever_is_left
    " #{x_bottles(next_bottle)} on the wall."
  end

  def take_one_down
    "Take #{num_bottles == 1 ? 'it' : 'one'} down and pass it around,"
  end

  def go_to_the_store
    "Go to the store and buy some more,"
  end

  def x_bottles(num)
    case num
    when 1; "1 bottle of beer"
    when 0; "no more bottles of beer"
    else
      "#{num} bottles of beer"
    end
  end

  def next_bottle
    if no_more_bottles?
      99
    else
      num_bottles - 1
    end
  end

  def no_more_bottles?
    num_bottles == 0
  end
end
