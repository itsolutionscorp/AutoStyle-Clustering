class RobotNames
  class << self
    private
    CHARACTERS=[*'a'..'z',*'A'..'Z']
    DIGITS=[*'0'..'9']

    public
    def generate
      (
        2.times.map {CHARACTERS.sample}+
        3.times.map {DIGITS.sample}
      ).join
    end
  end
end

module UniqueArray
  class << self
    def included(base)
      base.class_eval do
        @unique_values=[]
        extend ClassMethods
      end
    end
  end

  module ClassMethods
    private
    attr_accessor :unique_values
    def include?(value)
      unique_values.include?(value)
    end

    def add(value)
      unless include?(value)
        unique_values << value
        value
      end
    end
  end
end

class ManagedRobotNames < RobotNames
  private
  include UniqueArray
  DEFAULT_MAX=100

  class << self
    public
    def generate(max_retries=nil)
      (max_retries||DEFAULT_MAX).times do
        name=add(super()) and return name
      end
      raise "Can't find unique value in #{max_retries||DEFAULT_MAX} attempts."
    end
  end
end

class Robot
  private
  attr_writer :name
  def initialize
    reset
  end

  public
  attr_reader :name
  def reset
    self.name=ManagedRobotNames.generate
  end
end
