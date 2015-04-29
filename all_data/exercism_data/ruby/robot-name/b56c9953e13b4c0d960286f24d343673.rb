class Robot

  @@names = []

  def self.names
    @@names
  end

  attr_accessor :name

  def initialize
    assign_unique_name(@name)
  end

  def reset
    assign_unique_name(@name)
  end

  def assign_unique_name(current_name)
    new_name = generate_name
    until current_name != new_name
      new_name = generate_name
    end
    @name = new_name
    @@names.delete(current_name)
    @@names << @name
  end

  def generate_name
    (0...2).map { (65 + rand(26)).chr }.join + rand(100..999).to_s
  end

end
