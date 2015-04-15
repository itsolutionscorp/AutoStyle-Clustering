class Robots
  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    name = ('A'..'Z').to_a.sample(2).join('') + rand(100..999).to_s
  end
end
