class Scale
  def initialize(key, mode, intervals = nil)
    intervals ||= "m" * 12
    @key = key
    @mode = mode
    @intervals = intervals
  end

  def name
    "#{@key.upcase} #{@mode}"
  end

  def pitches
    deserialize(serialize, use_sharps: use_sharps?)
  end

  def serialize
    previous_tone = tone(@key)
    interval_types = @intervals
    interval_types.chars.each.with_object([]) do |type, tones|
      tones << previous_tone
      interval = case type
                 when "m" then 1
                 when "M" then 2
                 when "A" then 3
                 end
      previous_tone = (previous_tone + interval) % 12
    end
  end

  TONES = {
    "A" => 0,
    "B" => 2,
    "C" => 3,
    "D" => 5,
    "E" => 7,
    "F" => 8,
    "G" => 10,
  }

  NOTES = {0=>"A", 2=>"B", 3=>"C", 5=>"D", 7=>"E", 8=>"F", 10=>"G"}

  def deserialize(tones, use_sharps:)
    tones.map { |tone|
      note = NOTES[tone]
      unless note
        if use_sharps
          tone -= 1
          accidental = "#"
        else
          tone += 1
          accidental = "b"
        end
        tone %= 12
        note = NOTES[tone] + accidental
      end
      note
    }
  end

  def tone(note)
    main_note = note[0].upcase
    accidental = note[1]
    interval = case accidental
               when "#" then 1
               when "b" then -1
               else 0
               end
    TONES[main_note] + interval
  end

  private

  def use_sharps?
    %w[C G D A E B F a e b f# c# g# d#].member?(@key) &&
      satisfies_inconsistent_chromatic_scale_rules?
  end

  def satisfies_inconsistent_chromatic_scale_rules?
    (@mode != :chromatic) ||
      (@mode == :chromatic && @key == "C")
  end
end
