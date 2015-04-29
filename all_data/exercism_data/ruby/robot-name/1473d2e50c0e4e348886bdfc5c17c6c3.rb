class Robot
  attr_accessor :name

  def initialize
    @name = make_name
  end

  def reset
    @name = make_name
  end

  private
  def make_name
    # a name is two Letters (A-Z) followed by 3 digits
    [*'A'..'Z'].sample(2).join + [*100..999].sample(1).join
  end
end
