class Robot
  attr_reader :name
  @@used_names = []

  def initialize
    reset
  end

  def reset
    begin
      @name = generate_name
    end while @@used_names.include?(@name)
    @@used_names << @name
  end

  def generate_name
    ((0..1).map { (65 + rand(26)).chr }.join) + ((0..2).map { rand(10) }.join)
  end

  def self.used_names
    @@used_names
  end
end
