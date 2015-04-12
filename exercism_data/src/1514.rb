def compute(a, b)
    mutations = a.chars.zip(b.chars).select do |left, right|
      !right.nil? && left != right
    end
    mutations.length
  end