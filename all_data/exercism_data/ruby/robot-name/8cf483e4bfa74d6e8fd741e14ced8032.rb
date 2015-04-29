class Robot

  attr_reader :name

  def initialize(name = default_value)
    @name = random_letter + random_letter + rand(1000).to_s
  end

  def default_value
    'ab123'
  end

  def reset
    @name = ''
  end

  private

  def random_letter
    rand(65..90).chr
  end

end
