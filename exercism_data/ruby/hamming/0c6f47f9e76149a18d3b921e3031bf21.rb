class Hamming
  def self.compute(str_a, str_b)
    new(str_a, str_b).compute
  end

  def initialize(str_a, str_b)
    @str_a = trim str_a, to: str_b.length
    @str_b = trim str_b, to: str_a.length
  end

  def compute
    each_pair.count { |(a, b)| a != b }
  end

  private

  def each_pair
    @str_a.zip(@str_b)
  end

  def trim(string, to:)
    string.chars.first(to)
  end
end
