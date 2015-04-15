class Robot
  attr_accessor :name, :used_names

  def initialize
    @name = random_name
  end

  def random_name
    new_name = ''

    new_name << ('A'..'Z').to_a.shuffle[0, 2].join

    3.times { new_name << rand(9).to_s }

    self.name = new_name
  end

  def reset
    random_name
  end
end
