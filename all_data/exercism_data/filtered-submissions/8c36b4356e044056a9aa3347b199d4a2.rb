def compute(arg1, arg2)
    differences = 0
    (0...arg1.length).each do |index|
      differences += 1 unless arg1[index] == arg2[index]
    end
    differences
  end