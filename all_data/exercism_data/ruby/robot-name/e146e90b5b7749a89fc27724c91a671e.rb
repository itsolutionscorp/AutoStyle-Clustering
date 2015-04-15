class Robot
  USED_NAMES = []
  ALBHABETS = ( 'A'..'Z' ).to_a
  def initialize
    @name = nil
  end

  def name
    @name ||= assign_name
  end

  def reset
    self.name = nil
  end

  private
 def name=(value)
    @name = value
  end

  def assign_name
    try_name = generate_name
    while USED_NAMES.include? try_name
      try_name = generate_name
    end
    USED_NAMES << try_name
    try_name
  end

  def generate_name
    rand_name = 2.times.map{ALBHABETS[rand(ALBHABETS.length)]}.join
    rand_name << rand.to_s[2..4]
  end
end
