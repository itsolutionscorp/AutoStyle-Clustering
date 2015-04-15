require 'set'
class Robot

  ROBOT_NAMES = ::Set.new
  
  attr_reader :name

  def initialize
    set_name
  end

  def reset
    set_name
  end

  private

    def set_name
      @name = new_name
      while ROBOT_NAMES.include?(@name)
        @name = new_name
      end
    end

    def new_name
      (randomize('A'..'Z', 2) + randomize(0..9, 3)).join('')
    end

    def randomize(range, count)
      range.to_a.shuffle[0,count]
    end
end
