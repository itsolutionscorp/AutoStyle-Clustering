class Hamming

  def compute(first, second)
    hamz = 0
    first.chars.each_with_index do |character, index|
      break if second[index].nil?
      unless first[index].eql? second[index]
        hamz += 1
      end
    end
    return hamz
  end

end
