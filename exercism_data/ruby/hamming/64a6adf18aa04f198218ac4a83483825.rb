module Hamming
  extend self

  def compute(string_1, string_2)
    (0..string_1.length).reduce(0) do |distance, i|
      distance + (string_1[i] != string_2[i] ? 1 : 0)
    end
  end
end
