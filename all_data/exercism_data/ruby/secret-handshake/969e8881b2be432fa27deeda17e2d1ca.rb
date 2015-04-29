class SecretHandshake
  attr_reader :marker

  def initialize(index)
    @marker = index
  end

  def commands
    return [] unless marker.kind_of?(Fixnum)

    negator == "1" ? actions.reverse : actions
  end

  private

  def actions
    results = []

    switches.map.with_index do |digit, i|
      if digit == "1"
        key = digit + ("0" * i)
        results << known_commands[key]
      end
    end

    results
  end

  def known_commands
    {
      "1"    => "wink",
      "10"   => "double blink",
      "100"  => "close your eyes",
      "1000" => "jump"
    }
  end

  def to_binary
    "%b" % marker
  end

  def switches
    make_switches[0,4]
  end

  def make_switches
    to_binary.chars.reverse
  end

  def negator
    make_switches[4]
  end
end
