def compute(a, b)
    length = [a,b].min_by {|strand| strand.length}.length
    differences = 0
    length.times do |index|
      differences += 1 if a[index] != b[index]
    end
    differences
  end