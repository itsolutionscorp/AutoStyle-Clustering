class Beer
  attr_reader :final_verse, :running_verse

  def initialize
  end

  def verse(count)
    decorate(count)
  end

  def sing(start, stop = 0)
    start.downto(stop).map do |n|
      verse(n)
    end.join("\n") + "\n"
  end

  private

  def decorate(count, final = false)
    case count
    when 0 then
      verse = [
        "No more bottles of beer on the wall, no more bottles of beer.",
        "Go to the store and buy some more, 99 bottles of beer on the wall.",
      ]
    else
      verse = [
        "%d %s of beer on the wall, %d %s of beer.",
        "Take %s down and pass it around, %s %s of beer on the wall.",
      ]
    end

    [sprintf(verse.first, count, pluralise(count), count, pluralise(count)),
      sprintf(verse.last, one_or_it(count),
              no_more_or_count(count), pluralise(count - 1)),
      "",
    ].join("\n")
  end

  def no_more_or_count(count)
    count == 1 ? 'no more' : count - 1
  end

  def one_or_it(count)
    count > 1 ? 'one' : 'it'
  end

  def pluralise(count)
    count == 1 ? 'bottle' : 'bottles'
  end
end
