class Robot
  def initialize
    @name = name_generate
  end

  def name
    @name.nil? ? initialize : @name
  end

  def reset
    @name = nil
  end

  private

  def name_generate
    [*'AA'..'ZZ'].sample + [*(0..9)].sample(3).join
  end
end
