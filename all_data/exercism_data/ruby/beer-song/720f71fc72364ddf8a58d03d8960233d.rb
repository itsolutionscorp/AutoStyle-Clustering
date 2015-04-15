class BeerSong
  MAX_COUNT = 99

  def verse(count)
    Verse.new(count, MAX_COUNT).verse
  end

  def verses(start, stop)
    (stop..start).map { |count| verse(count) + "\n" }.reverse.join
  end

  def sing
    verses(MAX_COUNT, 0)
  end
end

class Verse
  attr_reader :count, :max_count

  def initialize(count, max_count)
    @count = count
    @max_count = max_count
  end

  def verse
    [current_wall, reaction].map { |sentence| sentence + "\n" }.join
  end

  private

  def current_wall
    sentence(bottles_on_wall, bottle_count)
  end

  def reaction
    empty? ? buy_new_beer : take_down
  end

  def buy_new_beer
    reset!
    sentence("go to the store and buy some more", bottles_on_wall)
  end

  def take_down
    sentence("take #{last? ? 'it' : 'one'} down and pass it around", BottleCount.new(next_count).on_wall)
  end

  def bottles_on_wall
    bottle_count.on_wall
  end

  def bottle_count
    BottleCount.new(count)
  end

  def sentence(*args)
    args[0].capitalize!
    args.join(', ') + '.'
  end

  def next_count
    count - 1
  end

  def empty?
    count == 0
  end

  def last?
    count == 1
  end

  def reset!
    @count = @max_count
  end
end

class BottleCount
  attr_reader :count

  def initialize(count)
    @count = count
  end

  def to_s
    pluralize(count, 'bottle', 'bottles') + ' of beer'
  end

  def on_wall
    "#{to_s} on the wall"
  end

  private

  def pluralize(count, singluar, plural = nil)
    if count == 0
      "no more #{plural}"
    else
      "#{count} #{count.abs == 1 ? singluar : plural}"
    end
  end
end
