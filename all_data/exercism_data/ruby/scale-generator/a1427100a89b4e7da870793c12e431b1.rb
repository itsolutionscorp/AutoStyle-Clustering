class Scale
  def initialize(tonic, scale_name, pattern = nil)
    @tonic = tonic.capitalize
    @scale_name = scale_name
    @pattern = pattern
    @chromatic_scale = flat_keys.include?(tonic) ? flat_chromatic_scale : chromatic_scale
  end

  def name
    "#{@tonic} #{@scale_name}"
  end

  def pitches
    return reorder_chromatic_scale unless @pattern
    last_index = 0
    [].tap do |out|
      @pattern.chars.each do |char|
        out << reorder_chromatic_scale[last_index]
        last_index += intervals.index(char) + 1
      end
    end
  end

  private

  def reorder_chromatic_scale
    return @chromatic_scale if @tonic == 'C'
    index = @chromatic_scale.index(@tonic)
    @chromatic_scale[index..-1] + @chromatic_scale[0..index - 1]
  end

  def flat_keys
    %w(F Bb Eb Ab Db Gb d g c f bb eb)
  end

  def flat_chromatic_scale
    %w(C Db D Eb E F Gb G Ab A Bb B)
  end

  def chromatic_scale
    %w(C C# D D# E F F# G G# A A# B)
  end

  def intervals
    %w(m M A)
  end
end
