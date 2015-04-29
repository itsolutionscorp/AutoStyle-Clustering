class Robot
  attr_reader :name

  def initialize
    reset
  end

  def new_name
    prefix + suffix
  end

  def prefix
    2.times.map { (('A'..'Z').to_a).sample }.join
  end

  def suffix
    4.times.map { (('0'..'9').to_a).sample }.join
  end

  def reset
    @name = new_name
  end
end
