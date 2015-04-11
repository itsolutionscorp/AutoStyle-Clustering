class Robot
  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    letters = ('A'..'Z').to_a.shuffle[0,2].join
    numbers = rand(999).to_s.rjust(3, "0")
    "#{letters}#{numbers}"
  end
end
