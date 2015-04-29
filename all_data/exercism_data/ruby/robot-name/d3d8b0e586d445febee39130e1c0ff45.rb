class Robot

  attr_reader :name
  @@invalid_names = []

  def initialize
    @name = ('A'..'Z').to_a.sample(2).join + rand(100..999).to_s
    while !@@invalid_names.include?(@name)
      @@invalid_names.push(@name)
    end
  end

  def reset
    initialize
  end

end
