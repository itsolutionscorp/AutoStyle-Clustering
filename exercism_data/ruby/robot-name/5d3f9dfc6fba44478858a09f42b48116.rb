class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = new_name
  end

  private
    @@ROBOT_ID = 100

    def new_name
      chars = ""
      2.times{ chars << (65+rand(25)).chr }
      @name = chars + (@@ROBOT_ID += 1).to_s
    end
end
