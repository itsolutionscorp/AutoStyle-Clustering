class Robot
  attr_reader :name
  def initialize
    assign_name
  end

  def assign_name
    @name = (0...2).map { (65 + rand(26)).chr }.join
    3.times do
      @name << rand(10).to_s
    end
  end

  def reset
    assign_name
  end
end
