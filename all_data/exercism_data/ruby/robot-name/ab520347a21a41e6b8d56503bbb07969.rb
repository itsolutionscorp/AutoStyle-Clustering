class Robot
  attr_accessor :name
  def initialize
    @name = set_name
  end

  def set_name
    alpha = 2.times.map { ('A'..'Z').to_a.sample }.join
    numeric = 3.times.map { ('0'..'9').to_a.sample }.join
    alpha + numeric
  end

  def reset
    @name = set_name
  end
end
