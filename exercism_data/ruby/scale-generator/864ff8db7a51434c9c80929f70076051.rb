require 'pry'
class Scale
  attr_reader :pitches

  CHROMATIC = %w[A A# B C C# D D# E F F# G G#]
  CHROMATIC_FLAT = %w[A Bb B C Db D Eb E F Gb G Ab]
  FLAT_MAJOR_TONICS = %w[F Bb Eb Ab Db Gb]
  FLAT_MINOR_TONICS = %w[d g c f bb eb]
  STEPS = {"m" => 1, "M" => 2, "A" => 3}

  def initialize(tonic, scale, steps=:all)
    @tonic = tonic
    @scale = scale
    @steps = steps
    @pitches = generate_scale()
  end

  def name
    "#{@tonic.upcase} #{@scale.to_s}"
  end

  private

  def generate_scale
    scale = []

    if flat_minor?() || flat_major?()
      scale = CHROMATIC_FLAT.rotate(CHROMATIC_FLAT.index(@tonic.capitalize))
    else
      scale = CHROMATIC.rotate(CHROMATIC.index(@tonic.capitalize))
    end

    return scale if @steps == :all
    filter_pitches(scale)
  end

  def filter_pitches(list)
    convert_steps_to_indexes().map {|index| list[index]}
  end

  def convert_steps_to_indexes
    @steps.chars.each_with_object([]).with_index do |(step, sums), i|
      if i == 0
        sums << 0
        sums << STEPS[step]
      else
        sums << sums[i] + STEPS[step] unless i == @steps.size - 1
      end
    end
  end

  def flat_minor?
    FLAT_MINOR_TONICS.include?(@tonic)
  end

  def flat_major?
    FLAT_MAJOR_TONICS.include?(@tonic)
  end
end
