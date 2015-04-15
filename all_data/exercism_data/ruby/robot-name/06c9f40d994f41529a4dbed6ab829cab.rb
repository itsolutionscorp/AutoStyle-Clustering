class Robot
  def initialize
    @name = create_random_name
    @all_names = []
  end

  def create_random_name
    [*'A'..'Z'].sample(2).join + 3.times.collect{rand(10).to_s}.join
  end

  def create_uniq_name
    while @all_names.include?(@name = create_random_name)
      @name = create_random_name
    end
    @name
  end

  def name
    @name || create_uniq_name
  end

  def reset
    @all_names << @name
    @name = nil
  end
end
