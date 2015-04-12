def compute(first, second)
    return 0 if first == second
    if second.length > first.length
      self.compute(second, first)
    end
    hamz = 0
    first.chars.each_with_index do |character, index|
      break if second[index].nil?
      unless first[index].eql? second[index]
        hamz += 1
      end
    end
    return hamz
  end