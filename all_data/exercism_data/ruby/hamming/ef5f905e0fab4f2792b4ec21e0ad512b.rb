class Hamming
  def self.compute(first, second)
    distance = 0
    stacked = first.split("").zip(second.split(""))

    stacked.each do |stack|
      if stack[0].nil? or stack[1].nil?
        break
      end

      distance += 1 if stack[0] != stack[1]
    end

    distance
  end
end
