class Beer

  def self.song(first=99)
    new.sing(first)
  end

  def sing(first, last=0)
    first.downto(last).collect { |beer_count| verse(beer_count) }.join("\n") + "\n"
  end

  def verse(beer_count)
    remaining = remaining(beer_count)
    action = action(beer_count)
    result = remaining(beer_count-1)
    "#{remaining.capitalize} on the wall, #{remaining}.\n#{action}, #{result} on the wall.\n"
  end

  private

  def remaining(beer_count)
    beer_count = 99 if beer_count < 0
    beer_count = "no more" if beer_count == 0

    "#{beer_count} #{container(beer_count)} of beer"
  end

  def action(beer_count)
    if beer_count == 0
      return "Go to the store and buy some more"
    end

    "Take #{pronoun(beer_count)} down and pass it around"
  end

  def container(beer_count)
    return "bottle" if beer_count == 1
    "bottles"
  end

  def pronoun(beer_count)
    return "it" if beer_count == 1
    "one"
  end
end
