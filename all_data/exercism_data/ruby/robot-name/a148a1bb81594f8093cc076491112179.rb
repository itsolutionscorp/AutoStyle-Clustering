class Robot
  def initialize
    self.generate_name
  end

  def name
    return @name
  end

  def reset
    self.generate_name
  end

  def generate_name
    alphabet = ('a'..'z').to_a + ('A'..'Z').to_a
    random = Random.new()
    @name = alphabet[random.rand(26)] + alphabet[random.rand(26)]
    while @name.length < 5
      @name += random.rand(10).to_s
    end
  end
end
