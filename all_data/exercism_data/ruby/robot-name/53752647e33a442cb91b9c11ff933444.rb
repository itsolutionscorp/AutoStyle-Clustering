class Robot
  attr_reader :name

  NAMES = Array.new

  def initialize
    set_name
  end

  def reset
    set_name
  end

  def set_name
    @name = [*'A'..'Z'].sample(2).join + [*0..9].sample(3).join
    NAMES.include?(@name) ? set_name : NAMES.push(@name)
  end

end
