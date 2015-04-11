class Robot
attr_reader :name

  def initialize
    @name = nil
  end

  def name
    return @name if @name != nil
    @name = ((0..1).map { (65 + rand(26)).chr } + (0..2).map { (rand(0..9))}).join
  end

  def reset
    @name = nil
    name
  end
end
