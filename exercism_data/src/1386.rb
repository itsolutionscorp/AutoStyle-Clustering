def compute(a, b)
    diff = 0
    shorther, longer = [a, b].sort_by(&:length)
    shorther.split(//).each_with_index do |letter, index|
      diff += 1 if letter != longer[index]
    end
    diff
  end