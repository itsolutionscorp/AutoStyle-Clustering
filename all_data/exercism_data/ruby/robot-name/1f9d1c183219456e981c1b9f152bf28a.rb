class Robot
  @@names = []

  def name
    @name ||= generate_name  
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    ('A'..'Z').to_a.sample + ('A'..'Z').to_a.sample + (100..999).to_a.sample.to_s
  end
end
