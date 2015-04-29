class Robot
  attr_reader :name

  def initialize
    self.reset
  end

  def reset
    set_name
  end

  private

  def set_name
    @name = (('A'..'Z').to_a.sample(2) << (100..999).to_a.sample).join('')
  end

end
