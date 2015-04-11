class Naming
  def generate
    name
  end

  private

  def name
    "#{random_Letters}#{random_numbers}"
  end

  def random_Letters
    (0..1).map { ('A'..'Z').to_a[rand(26)] }.join
  end

  def random_numbers
    (0..2).map { (0..9).to_a[rand(10)] }.join
  end
end

class Robot
  def initialize naming = Naming.new
    @naming = naming
  end

  def name
    @name ||= @naming.generate
  end

  def reset
    @name = nil
  end
end
