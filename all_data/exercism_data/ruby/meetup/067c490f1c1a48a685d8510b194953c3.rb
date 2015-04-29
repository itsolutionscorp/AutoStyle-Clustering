require 'date'

class Meetup

  POSITION_MAP = {
    "first"   => 0,
    "second"  => 1,
    "third"   => 2,
    "fourth"  => 3,
    "last"    => -1
  }

  def initialize(month, year)
    @month  = month
    @year   = year
  end

  private

  def method_missing(method)
    case method
    when /\A(\w+)teenth\z/
      find_teenth($1)
    when /\A(\w+)_(\w+)\z/
      find_position($1, $2)
    else
      super
    end
  end

  def find_teenth(abbr_wday)
    query = abbr_wday + "day?"
    date_range(13, 19).find { |date| date.send(query) }
  end

  def find_position(position_name, wday)
    position = POSITION_MAP[position_name]
    query = wday + "?"
    date_range(1, -1).select { |date| date.send(query) }[position]
  end

  def date_range(from, to)
    Date.new(@year, @month, from) .. Date.new(@year, @month, to)
  end
end
