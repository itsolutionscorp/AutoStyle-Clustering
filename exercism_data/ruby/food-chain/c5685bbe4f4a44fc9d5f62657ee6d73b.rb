class FoodChainSong
  def initialize
    @animals = [
      { type: 'fly', action: '' },
      { type: 'spider', action: 'It wriggled and jiggled and tickled inside her.' },
      { type: 'bird', action: 'How absurd to swallow a bird!' },
      { type: 'cat', action: 'Imagine that, to swallow a cat!' },
      { type: 'dog', action: 'What a hog, to swallow a dog!' },
      { type: 'goat', action: 'Just opened her throat and swallowed a goat!' },
      { type: 'cow', action: 'I don\'t know how she swallowed a cow!' },
      { type: 'horse', action: 'She\'s dead, of course!' }
    ]
    @animals.each_index do |i|
      animal = @animals[i]
      animal[:start] = "I know an old lady who swallowed a #{animal[:type]}."
      case i
      when 0
        animal[:reason] = "I don't know why she swallowed the #{animal[:type]}. Perhaps she'll die."
      when 2
        animal[:reason] = "She swallowed the #{animal[:type]} to catch the #{@animals[i - 1][:type]} that wriggled and jiggled and tickled inside her."
      else
        animal[:reason] = "She swallowed the #{animal[:type]} to catch the #{@animals[i - 1][:type]}."
      end
    end
  end

  def verse(num)
    verse_animals = @animals.take(num)
    if num < @animals.length
      reasons = (0..(verse_animals.length - 1)).to_a.reverse.map do |i|
        verse_animals[i][:reason]
      end
    else
      reasons = []
    end
    lines = [verse_animals.last[:start], verse_animals.last[:action]] + reasons
    lines.select { |line| !line.empty? }.join("\n") + "\n"
  end

  def verses(first, last)
    (first..last).to_a.map { |i| verse(i) }.join("\n") + "\n"
  end

  def sing
    verses(1, @animals.length)
  end
end
