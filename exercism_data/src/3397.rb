module Hamming
  def compute(string1, string2)
    [string1, string2].min_by(&:length).length.times.count do |i|
      string1[i] != string2[i]
    end
  end
end
