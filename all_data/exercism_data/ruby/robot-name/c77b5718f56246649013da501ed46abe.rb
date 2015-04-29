class Robot
  attr_reader :name

  def initialize
    @name = name_finder
  end

  def reset
    @name = name_finder
  end

  def name_finder
    [*('A'..'Z'),*('a'..'z')].sample(2).join + rand(1000).to_s.rjust(3, "0")
  end
end
