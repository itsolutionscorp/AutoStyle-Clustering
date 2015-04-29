class Robot

  def name
    @name ||= generate
  end

  def reset
    @name = nil
    name
  end

  private

  def generate
    name = ''
    2.times { name << random_letter }
    3.times { name << Random.rand(9).to_s }
    name
  end

  def random_letter
    Random.rand(2) == 0 ? alphabet.sample : alphabet.sample.upcase
  end

  def alphabet
    @alphabet ||= ('a'..'z').to_a
  end

end
