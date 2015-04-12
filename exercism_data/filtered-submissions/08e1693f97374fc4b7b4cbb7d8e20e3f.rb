class Hamming
  def compute(str1, str2)
    str1.split('').each.with_index.reduce(0) do |diff, (v,i)|
      diff += 1 if v != str2[i] && str2[i]
      diff
    end
  end
end
