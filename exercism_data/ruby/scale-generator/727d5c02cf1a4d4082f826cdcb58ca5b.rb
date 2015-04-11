class Scale

  SHARPS = %w(A A# B C C# D D# E F F# G G#)
  FLATS =  %w(A Bb B C Db D Eb E F Gb G Ab)
  PITCHES = { SHARPS => %w(C a G D A E B F# e b f# c# g# d#),
              FLATS => %w(F Bb Eb Ab Db Gb d g c f bb eb)
            }

  STEPS = { 'm' => 1, 'M' => 2, 'A' => 3 }

  def initialize(note, name, intervals = "m"*12)
    @note, @name = note, name.to_s.gsub("_", " ")
    @steps = steps_from(intervals)
    @pitches = pitches_of(@note)
    @start_idx = @pitches.index(@note)
  end
  
  def name
    "#{@note.capitalize} #{@name}"
  end
  
  def pitches
    @steps.inject([@note.capitalize]) do |scale, step|
      scale + next_note(scale, step)
    end
  end
  
private

  def steps_from(intervals)
    intervals.chars[0..-2].map { |step| STEPS[step] }
  end
  
  def pitches_of(note)
    PITCHES.select { |pitch, notes| notes.include?(note) }.keys[0]
  end
  
  def next_note(scale, step)
    [@pitches[(@pitches.index(scale[-1]) + step) % @pitches.length]]
  end
end
