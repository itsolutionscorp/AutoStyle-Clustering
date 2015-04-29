class Robot
  def initialize
    @name = set_name
  end
  def name
    @name
  end
  def reset
    @name = set_name
  end

  private

  def set_name
    ("A".."Z").to_a.sample(2).join + (100...999).to_a.sample.to_s
  end
end
