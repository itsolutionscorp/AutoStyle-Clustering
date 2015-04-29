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
    random_letter + random_letter + rand(100..1000).to_s
  end

  def random_letter
    rand(65..90).chr
  end

end
