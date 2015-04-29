class Robot
  attr_reader :name

  def initialize
    @name = name_init
  end

  def name_init
    name = ""
    name += ("a".."z").to_a.shuffle[0..1].join
    name += (1..999).to_a.shuffle[0].to_s
    name
  end

  def reset
    @name = name_init
  end
end
