class NameManager
  @assigned_names=[]
  class << self
    attr_reader :assigned_names

    def add(name)
      @assigned_names << name
    end

    def include?(name)
      @assigned_names.include?(name)
    end
  end
end

class RobotNames
  class << self
    private
    def characters
      ('a'..'z').to_a+('A'..'Z').to_a
    end

    def digits
      ('0'..'9').to_a
    end

    def random(arr)
      arr[rand(arr.size)]
    end

    public
    def generate
      name=''
      2.times { name << random(characters) }
      3.times { name << random(digits) }
      name
    end

    def generate_managed
      loop do
        name=generate
        unless NameManager::include?(name)
          NameManager::add(name)
          return name
        end
      end
    end

  end
end

class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    self.name=RobotNames.generate_managed
  end
end
