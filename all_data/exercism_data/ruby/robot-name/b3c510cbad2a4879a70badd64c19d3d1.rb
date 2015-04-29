class Robot

  def initialize
    @name = nil
  end

  def name
    if @name == nil
      @name = ([*('A'..'Z')].sample(2) + [*('1'..'9')].sample(3)).join
    end
  end

  def reset
    @name = nil
  end

end
