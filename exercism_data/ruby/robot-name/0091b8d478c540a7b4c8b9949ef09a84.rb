require 'set'

class Robot
  @@used_names = Set.new [nil]
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    generate_name
    @@used_names << self.name
  end

  private
    def generate_name
      while @@used_names.include?(name)
        letters = (0..1).map { (65 + rand(26)).chr }.join
        numbers = rand(1000).to_s.rjust(3, '0')
        self.name = letters + numbers
      end
    end
end
