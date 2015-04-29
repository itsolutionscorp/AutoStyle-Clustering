def compute(string1, string2)
    min_length = [string1, string2].min_by(&:length).length
    min_length.times.count do |i|
      string1[i] != string2[i]
    end
  end
end