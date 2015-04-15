class Scale
  attr_reader :tonic, :name, :intervals

  def initialize(tonic, type = :chromatic, interval_key = 'mmmmmmmmmmm')
    @tonic = tonic
    @name = "#{tonic.upcase} #{type}"
    @intervals = intervals_from(interval_key).take_while { |i| i < 12 }
  end

  def pitches
    scale.rotate(scale.index tonic.capitalize).values_at(*intervals)
  end

  def scale
    sharp? ? %w(C C# D D# E F F# G G# A A# B) : %w(C Db D Eb E F Gb G Ab A Bb B)
  end

  private

  def sharp?
    %w(A B C D E F# G a b c# d# e f# g#).include?(tonic)
  end

  def intervals_from(string)
    string.each_char.with_object([0]) do |c, intervals|
      intervals << intervals.last + { 'm' => 1, 'M' => 2, 'A' => 3 }.fetch(c)
    end
  end
end
