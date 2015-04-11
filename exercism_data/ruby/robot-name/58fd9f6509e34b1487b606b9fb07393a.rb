class Robot
  attr_accessor :name
  ALPHA = ('A'..'Z').to_a
  NUMER = ('0'..'9').to_a

  def initialize
    @name = create_name
  end

  def create_name
    name = ''
    2.times do
      name += ALPHA.sample
    end
    3.times do
      name += NUMER.sample
    end
    return name
  end

  def reset
    @name = create_name
  end

end
