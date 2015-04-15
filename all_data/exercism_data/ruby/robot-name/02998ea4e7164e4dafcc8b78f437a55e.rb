class Robot

  attr_accessor :name

  def initialize
    @name ||= creates_name
  end

  def random_letters
    ('A'..'Z').to_a.shuffle[0,2].join
  end

  def random_numbers
    rand(100..999).to_s
  end

  def creates_name
    random_letters + random_numbers
  end

  def reset
    @name = creates_name
  end

end
