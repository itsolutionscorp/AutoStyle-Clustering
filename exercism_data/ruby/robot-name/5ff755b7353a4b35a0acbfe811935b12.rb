class Robot
  def self.reset_name_sequence(range = 'AA000'..'ZZ999')
    @@names = range.to_a.shuffle
  end

  reset_name_sequence

  attr_reader :name

  def initialize()
    raise 'no name available' if @@names.empty?
    reset
  end

  def reset
    @name = @@names.pop
  end
end
