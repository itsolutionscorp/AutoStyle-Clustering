class Scale

  PITCHES = {
    sharp: %w(A A# B C C# D D# E F F# G G#),
    flat:  %w(A Bb B C Db D Eb E F Gb G Ab)
  }

  attr_reader :name

  def initialize(tonic, type, steps='mmmmmmmmmmmm')
    chars = tonic.chars
    @tonic =
      if chars.size == 1
        chars[0].upcase
      else
        chars[0].upcase + chars[1]
      end

    @kind =
      case tonic
      when *%(C G D A E B F# e b f# c# g# d#) then :sharp
      when *%w(F Bb Eb Ab Db Gb d g c f bb eb) then :flat
      else :sharp
      end

    @name = "#{@tonic} #{type}"
    @steps = steps
  end

  def pitches
    return @pitches if defined? @pitches

    pitches = PITCHES[@kind]

    pivot = pitches.index(@tonic)
    size = pitches.size

    @pitches =
      @steps[0..-2]
        .chars
        .lazy
        .map(&method(:length))
        .map { |i| pivot += i }
        .map { |i| pitches[i % size] }
        .force
        .unshift(@tonic)
  end

  private

  def length(step)
    case step
    when 'M' then 2
    when 'm' then 1
    when 'A' then 3
    end
  end

end
