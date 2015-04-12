class Hamming
  def compute(original, replicated)
    (0..[original.length, replicated.length].min-1).inject 0 do |s,i|
      original[i] == replicated[i] ? s : s += 1
    end
  end
end
