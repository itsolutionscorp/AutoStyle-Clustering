def compute(string, other_string)
    string.length.times.inject(0) do |acc, i|
      acc + (string[i] == other_string[i] ? 0 : 1)
    end
  end