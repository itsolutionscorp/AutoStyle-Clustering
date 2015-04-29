class Robot
  attr_reader :name
  def initialize
    @name = create_name
  end

  def reset
    @name = create_name
  end

  private

  def create_name
    (('a'..'z').to_a.sample(2) << (1..9).to_a.sample(3).join.to_s).join
  end
end
