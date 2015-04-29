class Robot

  def initialize
    @letters = ('a'..'z').to_a
    @digits = (0..9).to_a
  end

  def name
    @name ||=
    @letters.sample +
    @letters.sample +
    @digits.sample.to_s +
    @digits.sample.to_s +
    @digits.sample.to_s
  end

  def reset
    remove_instance_variable(:@name)
  end

end
