require 'lfsr/fast'

class Robot
  def self.reset_name_sequence
    limit    = 26 * 26 * 1000 # 676000 names
    letters  = [*'A'..'Z']

    @@name_sequence = Enumerator.new do |y|
      i = LFSR::Fast::Size20.new(limit - 1)
      limit.times do
        letter_digits, num_digits = i.next_i.divmod(1000)
        l1, l2 = letter_digits.divmod(26)
        y.yield "%s%s%03i" % [letters[l1], letters[l2], num_digits]
      end
    end
  end

  reset_name_sequence

  attr_reader :name

  def initialize()
    reset
  end

  def reset
    begin
      @name = @@name_sequence.next
    rescue StopIteration
      raise 'run out of names!'
    end
  end
end
