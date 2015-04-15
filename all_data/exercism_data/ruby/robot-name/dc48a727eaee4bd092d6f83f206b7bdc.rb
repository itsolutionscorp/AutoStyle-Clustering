class Robot
  def initialize
    @name = "BX#{rand(100..1000)}"
  end

  def name
    if @name
      @name
    else
      initialize
    end
  end

  def reset
    @name = nil
  end
end
