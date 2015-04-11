class Scale
  SHARPS = %w{A A# B C C# D D# E F F# G G#}
  FLATS = %w{A Bb B C Db D Eb E F Gb G Ab}
  INT_TO_STEP = { 'm' => 1, 'M' => 2, 'A' => 3 }
  FLAT_KEYS = %w(F Bb Eb Ab Db Gb d g c f bb eb)

  def initialize(c, type, intervals="")
    @c = c.capitalize
    @type = type
    @intervals = intervals
    @steps = intervals.chop.each_char.map { |c| INT_TO_STEP[c] } unless intervals.empty?
    @scales = FLAT_KEYS.include?(c) ? FLATS : SHARPS
  end

  def name
    "#{@c.upcase} #{@type}"
  end

  def pitches
    return chromatic if @intervals.empty?
    i = @scales.index(@c)
    @steps.each_with_object([@c]) do |s, res|
      i = (i + s) % @scales.size
      res << @scales[i]
    end
  end

  private
  def chromatic
    i = @scales.index(@c)
    @scales[i..-1] + @scales[0..i - 1]
  end
end
