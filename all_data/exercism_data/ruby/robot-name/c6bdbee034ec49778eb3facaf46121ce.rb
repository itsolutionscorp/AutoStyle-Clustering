class Robot

  class << self
    def reset
      @@potential_names = ('AA000'..'ZZ999').to_a.shuffle
    end

    def generate_name
      raise "no names left to choose!" if @@potential_names.empty?
      @@potential_names.pop
    end
  end

  reset

  def name
    @name ||= Robot.generate_name
  end

  def reset
    @name = nil
  end

end
