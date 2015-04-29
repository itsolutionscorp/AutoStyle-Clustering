class Robot
  attr_reader :name

  class << self
    def find_name
      @old_machines = @old_machines || []
      loop do 
        name = (random_character(2) + random_integer(3)).join
        if(!@old_machines.include?(name))
          @old_machines << name
          return name
        end

      end 
    end

    private
    def random_character(number)
      [*'A'..'Z'].sample(number)
    end

    def random_integer(number)
      [*'0'..'9'].sample(number)
    end
  end

  def initialize
    reset
  end

  def reset
    @name = self.class.find_name
  end
 
end
