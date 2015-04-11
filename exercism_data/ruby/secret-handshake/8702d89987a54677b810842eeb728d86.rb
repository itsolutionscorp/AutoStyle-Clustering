class SecretHandshake
  attr_reader :sequence

  def initialize(number)
    @sequence = if number.is_a? Fixnum
                  raise ArgumentError unless (1..31).include?(number)
                  number.to_s(2).chars.map(&:to_i).reverse
                else
                  []
                end
  end

  def gestures
    [
      "wink",
      "double blink",
      "close your eyes",
      "jump",
      :reverse
    ]
  end

  def events
    @events ||= (
      Array.new.tap do |result|
        sequence.each_with_index do |value, index|
          result << gestures[index] if value == 1
        end
      end
    )
  end

  def commands
    events.last == :reverse ? events[0..-2].reverse : events
  end

end
