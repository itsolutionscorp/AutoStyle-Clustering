class Hamming
  def self.compute(first_input, second_input)
    distance = 0

    first_input.chars.zip(second_input.chars).count do |couple|
      distance += 1 if couple.first != couple.last unless couple.last == nil
    end

    distance
  end
end
