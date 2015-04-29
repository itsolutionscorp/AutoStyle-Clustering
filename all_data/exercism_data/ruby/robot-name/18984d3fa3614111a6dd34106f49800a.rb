class Robot
  class << self
    attr_writer :robot_names

    def robot_names
      @robot_names ||= []
    end
  end

  def name
    @name ||= generate_unique_name
  end

  def reset
    @name = nil
  end

  private

  def generate_unique_name
    loop do
      name = generate_random_name

      unless self.class.robot_names.include?(name)
        self.class.robot_names << name
        return name
      end
    end
  end

  def generate_random_name
    "#{random_letters}#{random_numbers}"
  end

  def random_letters
    ('A'..'Z').to_a.sample(2).join
  end

  def random_numbers
    (0..9).to_a.sample(3).join
  end
end
