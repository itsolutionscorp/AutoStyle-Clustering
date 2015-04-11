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
      random_letters + random_numbers
    end

    def random_numbers
      rand(100..999).to_s
    end

    def random_letters
      (:A..:Z).to_a.sample(2).join
    end
end
