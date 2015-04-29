class Scale
  attr_reader :letter_name, :scale_type, :pattern
  PITCHES = { sharp: %w(C C# D D# E F F# G G# A A# B),
              flat: %w(C Db D Eb E F Gb G Ab A Bb B) }

  FLAT_KEYS = %w(F Bb Eb Ab Db Gb d g c f bb eb)
  SHARP_KEYS = %w(G D A E B F e b f# c# g# d#)

  def initialize(letter_name, scale_type, pattern = 'mmmmmmmmmmmm')
    @letter_name = letter_name
    @scale_type = scale_type
    @pattern = pattern
  end

  def name
    "#{letter_name[0].upcase}#{letter_name[1]} #{scale_type}"
  end

  def pitches
    pitch_subset_from_pattern(pitch_set_for_key)
  end

  private

  def pitch_set_for_key
    pitch_set = (FLAT_KEYS.include?(letter_name) ? PITCHES[:flat] : PITCHES[:sharp]).dup
    pitch_set.rotate! until pitch_set.map(&:downcase).first == letter_name.downcase
    pitch_set
  end

  def pitch_subset_from_pattern(pitch_set)
    p = pattern.dup
    { 'm' => '.', 'M' => '.!', 'A' => '.!!' }.each { |k, v| p.gsub!(k, v) }
    p.chars.zip(pitch_set).keep_if { |yn, _l| yn == '.' }.map { |_yn, l| l }
  end
end
