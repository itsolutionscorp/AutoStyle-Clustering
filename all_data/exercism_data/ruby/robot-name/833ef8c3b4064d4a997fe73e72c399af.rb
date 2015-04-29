class Robot
  attr_reader :name

  def initialize
    @name=generate_random_callsign
  end

  def reset
    initialize
  end

private
  def generate_random_callsign
    letter+letter+digits
  end

  def letter
    Array('A'..'Z')[rand(0..25)]
  end

  def digits
    rand(100...1000).to_s
  end
end
