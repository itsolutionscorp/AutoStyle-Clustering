class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = new_name
  end

  private

  def new_name
    (prefix + suffix).join
  end

  def prefix
    2.times.map { ('A'..'Z').to_a.sample }
  end

  def suffix
    4.times.map { ('0'..'9').to_a.sample }
  end
end
