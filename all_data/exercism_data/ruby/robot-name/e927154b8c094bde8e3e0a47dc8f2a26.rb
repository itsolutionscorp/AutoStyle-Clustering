class Robot

  @@robot_names = []

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = nil
    generate_name while name_is_not_valid?
    @@robot_names << @name
  end

  private

  def name_is_not_valid?
    @name.nil? || @@robot_names.include?(@name)
  end

  def generate_name
    @name = (('A'..'Z').to_a.sample(2) + (0..9).to_a.sample(3)).join
  end
end
