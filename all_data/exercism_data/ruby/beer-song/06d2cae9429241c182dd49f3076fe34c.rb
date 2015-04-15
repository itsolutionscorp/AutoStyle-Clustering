class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(upper_bound, lower_bound)
    upper_bound.downto(lower_bound).map {|n| verse(n)}.join("\n") + "\n"
  end

  def verse(bottle_count)
    "#{count(bottle_count)} #{container(bottle_count)} of beer on the wall, ".capitalize +
    "#{count(bottle_count)} #{container(bottle_count)} of beer.\n" +
    "#{action(bottle_count)}, " +
    "#{count(bottle_count - 1)} #{container(bottle_count - 1)} of beer on the wall.\n"
  end

  private

  def count(bottle_count)
    case bottle_count
    when -1
      99
    when 0
      'no more'
    else
      bottle_count
    end
  end

  def container(bottle_count)
    case bottle_count
    when 1
      'bottle'
    else
      'bottles'
    end
  end

  def action(bottle_count)
    case bottle_count
    when 0
      "Go to the store and buy some more"
    else
      "Take #{pronoun(bottle_count)} down and pass it around"
    end
  end

  def pronoun(bottle_count)
    case bottle_count
    when 1
      'it'
    else
      'one'
    end
  end
end
