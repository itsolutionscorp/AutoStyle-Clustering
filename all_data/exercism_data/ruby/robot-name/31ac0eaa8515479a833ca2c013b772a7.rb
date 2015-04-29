class Robot
  LETTERS = ('A'..'Z').to_a

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
     2.times.map { LETTERS.sample }.join + rand(100..999).to_s
  end
end
