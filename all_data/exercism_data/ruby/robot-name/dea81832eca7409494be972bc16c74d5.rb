class Robot
  def initialize
    @name = "BX#{rand(100..1000)}"
  end

  def name
    @name ? @name : initialize
  end

  def reset
    @name = nil
  end
end
