class Robot
  attr_accessor :name

  def initialize
    @name = name_init
  end

  def name_init
    name = ""
    name += ("a".."z").to_a.sample(2).join
    name += (100..999).to_a.sample.to_s
    name
  end

  def reset
    self.name = name_init
  end
end
