class Robot
  attr_reader :name
  
  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private
  def generate_name
    "#{prefix}#{suffix}"
  end

  def prefix
    ('A'..'Z').to_a.sample(2).join('')
  end

  def suffix
    rand(100..999)
  end
end
