require 'time'

module NBottles
  refine Numeric do
    def bottles
      "#{nonzero? || 'no more'} bottle#{'s' unless abs == 1}"
    end
  end
end

class BeerSong
  using NBottles

  def verse(n)
    <<-BEER.gsub(/^./, &:upcase).sub(/(?<=\W)\.\Z/, '')
#{n.bottles} of beer on the wall, #{n.bottles} of beer.
#{take_from(n) or get_some_more}.
    BEER
  end

  def verses(from, to)
    from.downto(to).map(&method(:verse)).push('').join("\n")
  end

  def sing
    verses(99, 0)
  end

  private

  def take_from(n)
    "take #{n == 1 ? 'it' : 'one'} down and pass it around, "\
    "#{n.pred.bottles} of beer on the wall" if n > 0
  end

  def get_some_more
    if beer_o_clock?
      "go to the store and buy some more, #{99.bottles} of beer on the wall"
    else
      "it's too late and your tests have broken, "\
      "still #{0.bottles} of beer :-("
    end
  end

  # Apply the appropriate licensing laws.
  def beer_o_clock?
    Time.now.between?(Time.parse('10:00'), Time.parse('22:00'))
  end
end
