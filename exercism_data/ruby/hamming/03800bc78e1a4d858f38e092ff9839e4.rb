class Hamming
  class << self
    def compute(first, second)
      counter = 0
      first.chars.each_with_index do |char, index| 
        if char != second[index] && second[index]
          counter += 1 
        end 
      end
      counter
    end
  end
end
