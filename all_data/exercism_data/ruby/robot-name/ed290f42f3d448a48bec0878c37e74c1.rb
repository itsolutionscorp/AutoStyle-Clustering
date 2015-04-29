class Robot
  attr_reader :name
  def initialize
    @name = generate
  end

  @@used_names ||= []

  def generate
    letters = ('A'..'Z').to_a + ('a'..'z').to_a
    name = letters.sample(2).join + (0..9).to_a.sample(3).join

    if used?(name)
      generate
    else
      @@used_names << name
      name
    end
  end

  def used?(name)
    @@used_names.include?(name)
  end

  def reset
    @name = generate
  end
end
