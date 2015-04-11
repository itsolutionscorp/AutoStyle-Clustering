class Robot
  attr_reader :name

  # Yes, I know.. Class variables are evil!
  # But, they work nicely for the given problem.
  # Also, using a string to store the generated values is safe in this case, due
  # to the pattern of the generated codes. Moreover, strings are faster to scan
  # than arrays. Here is a benchmark for 20000 generated codes:
  #
  # user           system      total        real
  # string         1.800000   0.120000   1.920000 (  1.928526)
  # array         12.950000   0.020000  12.970000 ( 12.967805)
  # array_second  13.410000   0.010000  13.420000 ( 13.429392)
  # string_second  1.760000   0.100000   1.860000 (  1.861949)
  #
  @@generated = ""

  def initialize
    self.reset
  end

  def reset
    name  = ('a'..'z').to_a.shuffle.first
    name += ('a'..'z').to_a.shuffle.first
    name += (  0..9  ).to_a.shuffle.first.to_s
    name += (  0..9  ).to_a.shuffle.first.to_s
    name += (  0..9  ).to_a.shuffle.first.to_s
    name  = name.upcase

    if @@generated.include? name
      self.reset
    else
      @name = name
      @@generated += name
    end
  end
end
