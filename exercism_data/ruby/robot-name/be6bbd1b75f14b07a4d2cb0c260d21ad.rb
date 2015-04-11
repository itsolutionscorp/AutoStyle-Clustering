class Robot

  @name
  @@previousRobotNames = []

  def initialize
    name
  end

  def name
    if @name == "" || @name.nil?
      until @name != "" and !@@previousRobotNames.include?(@name) do
        @name = ""
        2.times{@name  << (65 + rand(25)).chr}
        3.times{@name  << (rand(9).to_s)}
      end

      @@previousRobotNames.push(@name)
    end

    @name
  end

  def reset
    @name = ""
  end
end

