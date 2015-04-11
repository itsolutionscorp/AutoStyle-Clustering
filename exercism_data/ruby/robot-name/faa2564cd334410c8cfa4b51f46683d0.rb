class Robot
  attr_reader :name

  def initialize
    @collision_detection = []
    assign_name
  end

  def assign_name
    letters = (0...2).map { (65 + rand(26)).chr }.join
    numbers = rand(999).to_s.rjust(3, '0')
    @name = letters + numbers
    # stop collisions
    assign_name if @collision_detection.include? @name
    @collision_detection << @name
  end
  alias_method :reset, :assign_name

end
