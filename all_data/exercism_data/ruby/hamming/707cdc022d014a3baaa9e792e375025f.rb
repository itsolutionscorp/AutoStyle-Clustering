Hamming = Struct.new(:a, :b) do
  def self.compute(*args)
    new(*args).compute
  end

  def compute
    raise ArgumentError.new("Arguments must be equal length") if a.size != b.size

    a.chars.each_with_index.inject(0) do |sum, element|
      char = element.first
      parallel_index = element.last
      sum = sum + 1 if b[parallel_index] != char
      sum
    end
  end
end
