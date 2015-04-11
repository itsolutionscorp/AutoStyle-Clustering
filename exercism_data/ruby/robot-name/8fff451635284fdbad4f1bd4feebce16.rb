class Robot
  def name
    @name ||= create_name
  end

  def reset
    @name = create_name
  end

  private

  def create_name
    letters << numbers
  end

  def letters
    ('A'..'Z').to_a.shuffle[0,2].join
  end

  def numbers
    (0..9).to_a.shuffle[0,3].join
  end
end
