class Robot
  @@used_names = []

  def name
    @name ||= generate_name
    @@used_names << @name unless name_exists? @name
    @name
  end

  def reset
    @name = nil
  end

  private

    def generate_name
      name = [('A'..'Z').to_a.sample(2), (0..9).to_a.sample(3)].join
      generate_name if name_exists? name
      name
    end

    def name_exists?(name)
      @@used_names.include? name
    end
end
