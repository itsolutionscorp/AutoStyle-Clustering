class Robot
  attr_accessor :name
  def initialize
    @name = set_name
  end

  def set_name
    ('AA000'..'ZZ999').to_a.sample
  end

  def reset
    @name = set_name
  end
end
