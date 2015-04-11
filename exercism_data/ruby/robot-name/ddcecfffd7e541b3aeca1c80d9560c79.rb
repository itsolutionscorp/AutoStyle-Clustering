class Robot
  def initialize
    self._generate_name
  end

  def name
    return @name
  end

  def reset
    self._generate_name
  end

  def _generate_name
    alphabet = ('A'..'Z').to_a
    random = Random.new()
    @name = alphabet[random.rand(26)]
    @name += alphabet[random.rand(26)]
    while @name.length < 5
      @name += random.rand(10).to_s
    end
  end
end
