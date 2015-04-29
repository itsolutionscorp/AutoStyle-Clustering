class Robot

  def initialize
    @name
  end

  def generate
   (('A'..'Z').to_a.sample(2) + (1..9).to_a.sample(3)).join
  end

  def name
    @name ||= generate
  end

  def reset
    @name = generate
  end

end
