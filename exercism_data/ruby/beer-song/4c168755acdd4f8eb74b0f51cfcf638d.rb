class Integer
  def one?
    self == 1
  end
end

class BeerSong
  class Verse
    LAST_VERSE = <<-'VERSE'.gsub(/^\s+/, '').freeze
    No more bottles of beer on the wall, no more bottles of beer.
    Go to the store and buy some more, 99 bottles of beer on the wall.
    VERSE

    REGULAR_VERSE = <<-'VERSE'.gsub(/^\s+/, '').freeze
    %{count} %{noun} of beer on the wall, %{count} %{noun} of beer.
    Take %{ref} down and pass it around, %{next_count} %{next_noun} of beer on the wall.
    VERSE

    attr_reader :bottle_count

    def initialize(bottle_count)
      @bottle_count = bottle_count
    end

    def to_s
      return LAST_VERSE if bottle_count.zero?
      REGULAR_VERSE % substitutions
    end

    def substitutions
      next_verse = self.class.new(bottle_count - 1)
      {
        count: count,
        noun:  noun,
        count: count,
        ref:   ref,
        next_count: next_verse.count,
        next_noun: next_verse.noun
      }
    end

    def noun
      bottle_count.one? ? "bottle" : "bottles"
    end

    def count
      bottle_count.zero? ? "no more" : bottle_count.to_s
    end

    def ref
      bottle_count.one? ? "it" : "one"
    end
  end

  def verse(bottles)
    Verse.new(bottles).to_s
  end

  def verses(from, to)
    (to..from).map { |n| verse(n) }.reverse.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end
end
