class Robot
  ALPHA_CHARS = [*(65..90), *(97..122)].map { |n| n.chr }

  attr_reader :name

  def initialize
    apply_factory_settings
  end

  def reset
    apply_factory_settings
  end

  def apply_factory_settings
    @name = generate_name
  end

  def generate_name
    "#{random_alpha_char}#{random_alpha_char}#{rand 1000}"
  end

  def random_alpha_char
    Robot::ALPHA_CHARS[rand(ALPHA_CHARS.size)]
  end
end
