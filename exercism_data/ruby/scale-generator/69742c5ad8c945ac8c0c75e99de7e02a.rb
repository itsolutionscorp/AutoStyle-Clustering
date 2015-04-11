require 'set'

class ScalePresenter
  def initialize(scale)
    @scale = scale
  end

  def to_a
    tone_hash.each_with_object([]) do |(tone, names), ary|
      ary << name_for(tone, names)
    end
  end

  private

  attr_reader :scale

  def tone_hash
    @tone_hash ||= Hash[scale.tones.map {|t| [t, CircleOfFifths::NOTE_NAMES[t]] }]
  end

  def accidental_type
    accidentals.last
  end

  def accidental_count
    accidentals.first
  end

  def accidental_tones
    @accidental_tones ||= Set.new(
      { sharps: CircleOfFifths::SHARP_ORDER,
        flats: CircleOfFifths::FLAT_ORDER 
      }[accidental_type].take(accidental_count))
  end

  def accidentals
    @accidentals ||= CircleOfFifths.accidentals_for(scale.tonic, scale.mode)
  end

  def accidental_symbol
    { :sharps => "#", :flats => "b" }[accidental_type]
  end

  def name_for(tone, names)
    if accidental_tones.include?(tone)
      names.detect {|x| x[1] == accidental_symbol }
    else
      names.first # hopefully natural
    end
  end
end

class ChromaticScalePresenter < ScalePresenter

  def to_a
    scale.tones.map {|t| pick_name(CircleOfFifths::NOTE_NAMES[t]) }
  end

  private

  def pick_name(names)
    return names.first if names.first.size == 1
    return names.detect(-> { names.last }) {|n| n[1] == "#" } if accidental_type == :sharps
    return names.detect(-> { names.last }) {|n| n[1] == "b" } if accidental_type == :flats
  end
end

class Scale
  attr_reader :mode, :tonic

  def initialize(tonic, mode, pattern = nil)
    @tonic = tonic
    @mode = mode
    @mode_pattern = pattern
  end

  def name
    "#{tonic.upcase} #{mode}"
  end

  def pitches
    presenter.to_a
  end

  def tones
    generic_scale_tones.map {|x| (x + tonic_offset) % 12 }
  end

  private

  PRESENTERS = { chromatic: ChromaticScalePresenter }

  def presenter
    presenter_klass.new(self)
  end

  def _tonic
    tonic.capitalize
  end

  def presenter_klass
    PRESENTERS.fetch(mode, ScalePresenter)
  end

  def tonic_offset
    @tonic_offset = CircleOfFifths::NAMES_TO_TONES[_tonic]
  end

  def generic_scale_tones #with no offset
    mode_pattern.chars.each_with_object([0]) do |interval, ary|
      ary << ary.last + INTERVALS[interval]
    end.tap(&:pop) # why include the last note in the pattern if you don't want it in the result?
                   # I have no idea.
  end

  def mode_pattern
    @mode_pattern || MODES[mode]
  end

  MODES     = { :chromatic => "mmmmmmmmmmmm"  }
  INTERVALS = { "m" => 1, "M" => 2, "A" => 3 }
end

class CircleOfFifths
  SHARP_ORDER = (0..11).map {|x| ((x * 7) + 6) % 12 }
  FLAT_ORDER  = (0..11).map {|x| ((x * 5) - 2) % 12 }

  (0..11).map {|x| (x * 7) % 12 }.tap do |circle|
    SHARPS = circle.each_with_object({}).with_index do |(tone, sharps), index|
      sharps[tone] = index
    end

    FLATS = circle.reverse.each_with_object({}).with_index do |(tone, sharps), index|
      sharps[tone] = (index + 1) % 12
    end
  end

  NOTE_NAMES  = [
    %w[C B#  ],
    %w[C# Db ],
    %w[D     ],
    %w[D# Eb ],
    %w[E Fb  ],
    %w[F E#  ],
    %w[F# Gb ],
    %w[G     ],
    %w[G# Ab ],
    %w[A     ],
    %w[A# Bb ],
    %w[B Cb  ],
  ]

  TONAL_OFFSET = {
    major:  0,
    minor: -3
  }

  NAMES_TO_TONES = NOTE_NAMES.each_with_object({}).with_index do |(names, hash), index|
    names.each {|name| hash[name] = index }
  end

  def self.accidentals_for(note_name, mode = :major)
    tone = (NAMES_TO_TONES[note_name.capitalize] + MODE_OFFSET[mode]) % 12
    if note_name[1] == '#'
      new(tone).all_accidentals.first
    elsif note_name[1] == 'b'
      new(tone).all_accidentals.last
    else
      new(tone).all_accidentals.min_by {|count, _| count }
    end
  end

  def initialize(tone_value)
    @tone_value = tone_value
  end

  def all_accidentals
    [[as_major_sharp, :sharps], [as_major_flat, :flats]]
  end

  private

  attr_reader :tone_value

  MODE_OFFSET = Hash.new(0).merge({
    major: 0,
    dorian: -1,
    lydian: -5,
    mixolydian: -7,
    phrygian: -3,
    locrian: 1,
    minor: 3,
    octatonic: -3,
    'harmonic_minor' => 3 # why isn't this one a symbol? Who knows.
  })

  def as_major_sharp
    SHARPS[tone_value]
  end

  def as_major_flat
    FLATS[tone_value]
  end
end
