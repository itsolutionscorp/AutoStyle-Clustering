class Robot
  attr_reader :name
  def initialize
    reset
  end

  def reset
    old_name = name
    while old_name == name
      @name = generate_name
    end
  end

  def generate_name
    @name = "#{alpha_prefix}#{numeric_suffix}"
  end

  def alpha_prefix(size=2)
    ("A".."Z").to_a.sample(size).join
  end
  
  def numeric_suffix(size=3)
    (0..9).to_a.sample(size).join
  end
end
