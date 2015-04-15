class Song
  attr_reader :current_verse, :max_verse

  def initialize
    fail NotImplementedError, 'Must use subclass and assign @max_verse'
  end

  def verse_format
    fail NotImplementedError, 'Override this method.'
  end

  def index
    current_verse - 1
  end

  def verse(num)
    verse_string(num).map do |line|
      @current_verse = num
      method(line).call
    end.join
  end

  def verses(from, to)
    from = [0, from].max
    to   = [to, max_verse].min

    (to..from).each_with_object([]) do |num, lyrics|
      lyrics << verse(num)
    end.reverse.join("\n") + "\n"
  end

  def sing
    verses(max_verse, 0)
  end

  def verse_string(num)
    verse_formats(num).split('/').map(&:to_sym)
  end
end

class BeerSong < Song
  def initialize
    @max_verse = 99
  end

  def verse_formats(num)
    if num == 0
      'line1/end_line'
    else
      'line1/take_one'
    end
  end

  def line1
    wall.gsub(/^n/, 'N') + how_many
  end

  def take_one
    "Take #{current_verse == 1 ? 'it' : 'one'} down and pass it around, #{wall(".\n", true)}"
  end

  def end_line
    "Go to the store and buy some more, #{wall(".\n", true)}"
  end

  def how_many(append = ".\n", second_line = false)
    number = second_line ? current_verse - 1 : current_verse
    number = 99 if number < 0
    number = 'no more' if number.zero?
    bottle = number == 1 ? 'bottle' : 'bottles'
    "#{number} #{bottle} of beer#{append}"
  end

  def wall(append = ', ', second_line = false)
    how_many(" on the wall#{append}", second_line)
  end
end
