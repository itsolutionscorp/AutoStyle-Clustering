class Beer
  def sing(start_count, end_count = 0)
    (end_count..start_count).map do |count|
      verse(count) + "\n"
    end.reverse.join
  end

  def verse(bottle_count)
    case bottle_count
      when 0 then NoBottle.new
      when 1 then SingleBottle.new
      else MultipleBottle.new(bottle_count)
    end.verse
  end
end

class Bottle
  def verse
    "#{bottle.capitalize} of beer on the wall, #{bottle} of beer.\n#{phrase}, #{bottle_on_wall} of beer on the wall.\n"
  end

  private

  def bottle
    pluralize(count)
  end

  def bottle_on_wall
    pluralize(count_on_wall)
  end

  def pluralize(count)
    "#{count} #{count == 1 ? 'bottle' : 'bottles'}"
  end
end

class NoBottle < Bottle
  def count
    'no more'
  end

  def count_on_wall
    99
  end

  def phrase
    'Go to the store and buy some more'
  end
end

class SingleBottle < Bottle
  def count
    1
  end

  def count_on_wall
    'no more'
  end

  def phrase
    'Take it down and pass it around'
  end
end

class MultipleBottle < Bottle
  attr_reader :count

  def initialize(count)
    @count = count
  end

  def count_on_wall
    count - 1
  end

  def phrase
    'Take one down and pass it around'
  end
end
