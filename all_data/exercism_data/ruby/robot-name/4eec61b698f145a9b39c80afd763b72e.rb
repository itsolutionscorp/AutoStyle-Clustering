class Robot

  def name
    @name ||= create_name
  end

  def reset
    @name = create_name
  end

  private

  def random_letters
    ('A'..'Z').to_a.shuffle[0,2].join
  end

  def random_numbers
    rand(100..999).to_s
  end

  def create_name
    random_letters + random_numbers
  end

end
