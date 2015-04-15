class Scale
  attr_reader :key, :scale, :intervals

  CHROMATIC = 'mmmmmmmmmmmm'

  def initialize(key, scale, intervals=CHROMATIC)
    @key   = key
    @scale = scale
    @intervals = intervals
  end

  def name
    "#{key.capitalize} #{scale}"
  end

  def pitches
    return build_chromatic if scale == :chromatic
    build_scale
  end

  KEYS_WITH_FLATS = %w(F Bb Eb Ab Db Gb d g c f bb eb)

  def notes
    if KEYS_WITH_FLATS.include?(key)
      %w(C Db D Eb E F Gb G Ab A Bb B)
    else
      %w(C C# D D# E F F# G G# A A# B)
    end
  end

  def build_chromatic
    offset = notes.index(key.capitalize)
    offset.zero? ? notes : notes.rotate(offset)
  end

  def build_scale
    chromatic_notes = build_chromatic
    steps.reduce([]) do |ary, step|
      ary << chromatic_notes[step]
    end
  end

  INTERVALS = { 'm' => 1, 'M' => 2, 'A' => 3 }

  # 'MMmMMMm' => [0, 2, 4, 5, 7, 9, 11] => positions in chromatic scale (12 notes)
  def steps
    intervals.chars[0...-1].reduce([0]) do |ary, step|
      ary << ( ary.last + INTERVALS[step] )
    end
  end

end
