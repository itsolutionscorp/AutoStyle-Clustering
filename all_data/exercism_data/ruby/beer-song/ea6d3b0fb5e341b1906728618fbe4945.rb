class BeerSong
  def verse(n)
    next_n = n > 0 ? n - 1 : 99
    "#{state(n).capitalize} on the wall, #{state(n)}.\n#{action(n)}, #{state(next_n)} on the wall.\n"
  end

  def verses(starting, ending)
    (ending..starting).to_a.reverse.map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end

  private

  def state(n)
    case
      when n > 1
        "#{n} bottles of beer"
      when n === 1
        "1 bottle of beer"
      when n === 0
        "no more bottles of beer"
      end
  end

  def action(n)
    case
      when n > 1
        "Take one down and pass it around"
      when n === 1
        "Take it down and pass it around"
      when n === 0
        "Go to the store and buy some more"
      end
  end
end
