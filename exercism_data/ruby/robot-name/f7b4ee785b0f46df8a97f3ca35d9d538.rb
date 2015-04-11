class Robot

  attr_reader :name

  def initialize(name = 'ab123')
    @name = random_letter + random_letter + rand(1000).to_s
  end

  def name

  end

  def reset
    @name = ''
  end

  private

  def random_letter
    (rand(26) + 65).chr
  end

end
