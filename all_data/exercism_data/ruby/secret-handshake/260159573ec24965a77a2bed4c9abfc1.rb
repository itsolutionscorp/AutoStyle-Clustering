class SecretHandshake
  attr_reader :insignia

  def initialize(indicate)
    @insignia = indicate
  end

  def commands
    return [] unless insignia.kind_of?(Fixnum)
    nullifer == "1" ? actions.reverse : actions
  end

  private

  def actions
    results = []
    transformation.map.with_index do |digit, i|
      if digit == "1"
        key = digit + ("0" * i)
        results << mandate[key]
      end
    end

    results
  end

  def mandate
    {
      "1"    => "wink",
      "10"   => "double blink",
      "100"  => "close your eyes",
      "1000" => "jump"
    }
  end

  def to_binary
    "%b" % insignia
  end

  def transformation
   synthesize_transformation[0,4]
  end

  def synthesize_transformation
    to_binary.chars.reverse
  end

  def nullifer
    synthesize_transformation[4]
  end
end
