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
    from = [1, from].max
    to   = [to, max_verse].min

    (from..to).each_with_object([]) do |num, lyrics|
      lyrics << verse(num)
    end.join("\n") + "\n"
  end

  def sing
    verses(1, max_verse)
  end

  def verse_string(num)
    verse_formats(num).split('/').map(&:to_sym)
  end
end

class FunSong < Song
  def initialize
    @max_verse = 3
  end

  def verse_formats(num)
    [
      'start/end',
      'start/fun/end',
      'start/end/end'
    ][num - 1]
  end

  def start
    'Happy fun song\n'
  end

  def end
    'But now it\'s over\n'
  end

  def fun
    'It\'s so fun\n'
  end
end

class FoodChainSong < Song
  def initialize
    @max_verse = 8
  end

  def verse_formats(num)
    case num
    when 1..2
      ['start/fly', 'start/spider/cumulative/fly'][num - 1]
    when 3..7
      'start/swallow/cumulative/fly'
    when 8
      'start/horse'
    end
  end

  def animals
    %w(fly spider bird cat dog goat cow horse)
  end

  def statement
    [
      '',
      'wriggled and jiggled and tickled inside her',
      'How absurd',
      'Imagine that,',
      'What a hog,',
      'Just opened her throat and',
      'I don\'t know how she',
      ''
    ]
  end

  def start
    "I know an old lady who swallowed a #{animals[index]}.\n"
  end

  def fly
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def spider
    "It wriggled and jiggled and tickled inside her.\n"
  end

  def swallow
    if current_verse < 6
      "#{statement[index]} to swallow a #{animals[index]}!\n"
    else
      "#{statement[index]} swallowed a #{animals[index]}!\n"
    end
  end

  def cumulative
    text = current_verse.downto(2).map do |i|
      "She swallowed the #{animals[i - 1]} to catch the #{animals[i - 2]}.\n"
    end.join
    text.gsub(/spider.$/, "spider that #{statement[1]}.")
  end

  def horse
    "She's dead, of course!\n"
  end
end
