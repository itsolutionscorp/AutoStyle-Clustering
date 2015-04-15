class Scale

  TONES_AS_SHARPS = %w[C C# D D# E F F# G G# A A# B]
  TONES_AS_FLATS = %w[C Db D Eb E F Gb G Ab A Bb B]

  NOTES_THAT_USE_SHARP_TONES = %w[C G D A E B F# e b f# c# g# d# a]
  NOTES_THAT_USE_FLAT_TONES = %w[F Bb Eb Ab Db Gb d g c f bb eb]

  def initialize(tonic, type, interval_pattern = "mmmmmmmmmmmm")
    @tonic = tonic
    @type = type
    @interval_pattern = interval_pattern.chars
  end

  def name
    "#{@tonic.upcase} #{@type.to_s}"
  end

  def pitches
    proper_notes = scale.rotate(offset)
    finished_pitches = []
    finished_pitches << proper_notes.shift

    @interval_pattern.each do |interval|
      if interval == 'm'
        finished_pitches << proper_notes.shift
      elsif interval == 'A'
        proper_notes.shift
        proper_notes.shift
        finished_pitches << proper_notes.shift
      else
        proper_notes.shift
        finished_pitches << proper_notes.shift
      end
    end
    finished_pitches.compact
  end

  private

  def offset
    scale.index(@tonic.capitalize)
  end

  def scale
    if NOTES_THAT_USE_SHARP_TONES.include?(@tonic)
      TONES_AS_SHARPS
    elsif NOTES_THAT_USE_FLAT_TONES.include?(@tonic)
      TONES_AS_FLATS
    end
  end

end
