class Scale
  SHARP_FLAT_MAP = {
    'A#' => 'Bb',
    'C#' => 'Db',
    'D#' => 'Eb',
    'F#' => 'Gb',
    'G#' => 'Ab'
  }

  CHROMATIC_SCALE = %w(A A# B C C# D D# E F F# G G#)
  FLAT_CHROMATIC_SCALE = CHROMATIC_SCALE.map do |pitch|
    SHARP_FLAT_MAP.fetch(pitch, pitch)
  end

  FLAT_KEYS = %w(F Bb Eb Ab Db Gb d g c f bb eb)
  ASCENDING_INTERVALS = %w(m M A)

  def initialize(tonic, scale_name, intervals = 'mmmmmmmmmmmm')
    @tonic = tonic.capitalize
    @scale_name = scale_name
    @intervals = intervals
    @scale = FLAT_KEYS.include?(tonic) ? FLAT_CHROMATIC_SCALE : CHROMATIC_SCALE
  end

  def name
    "#{tonic} #{scale_name}"
  end

  def pitches
    last_index = 0
    intervals.each_char.with_object([]) do |interval, keys|
      keys << ordered_scale[last_index]
      last_index += ASCENDING_INTERVALS.index(interval) + 1
    end
  end

  private

  attr_reader :tonic, :scale_name, :intervals, :scale

  def ordered_scale
    @ordered_scale ||= scale.rotate(scale.index(tonic))
  end
end
