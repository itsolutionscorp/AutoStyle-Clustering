class Robot
  @@generator = Random.new
  @@nameList = []
  attr_reader :name

  def initialize
    @name = genName()
    @@nameList.push(@name)
  end

  def reset
    @@nameList.delete(@name)
    @name = genName()
    @@nameList.push(@name)
  end

  private

  def genName
    letters = ('A'..'Z').to_a + ('a'..'z').to_a
    name = (0..1).map{ letters.sample }.join + @@generator.rand(9).to_s + @@generator.rand(9).to_s + @@generator.rand(9).to_s

    while @@nameList.index(name)
      letters = ('A'..'Z').to_a + ('a'..'z').to_a
      name = (0..1).map{ letters.sample }.join + @@generator.rand(9).to_s + @@generator.rand(9).to_s + @@generator.rand(9).to_s
    end

    name
  end
end
