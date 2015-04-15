class Robot

  class << self
    def new
      @robot_name = rando_string
      self
    end

    def reset
      new
    end

    def name
      @robot_name
    end

    def rando_string
      numeric = (0..1).map {('A'..'Z').to_a[rand(26)]}.join + (0..2).map {rand(10)}.join
    end
  end

end
