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
    (ending..starting).each_with_object([]) do |num, memo|
      memo << verse(num)
    end.reverse.join("\n") + "\n"
  end

  def on_the_wall
    "#{x_bottles(num_bottles).capitalize} on the wall, #{x_bottles(num_bottles)}."
  end

  def pass_it_around
    if no_more_bottles?
      "Go to the store and buy some more,"
    else
      "Take #{num_bottles == 1 ? 'it' : 'one'} down and pass it around,"
    end + " #{x_bottles(next_bottle)} on the wall."
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
