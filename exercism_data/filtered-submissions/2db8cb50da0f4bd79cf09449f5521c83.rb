module Hamming
  def compute(initial, current)
    distance = 0
    [initial.length, current.length].min.times do |i|
      distance += 1 if initial[i] != current[i]
    end
    distance
  end
end
