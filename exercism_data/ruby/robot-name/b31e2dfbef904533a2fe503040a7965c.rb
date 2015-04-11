class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

    def generate_name
      letters = (:A..:Z).to_a.sample(2).join
      numbers = (1..9).to_a.sample(3).join
      letters + numbers
    end
end
