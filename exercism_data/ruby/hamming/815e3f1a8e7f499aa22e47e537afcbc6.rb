class Hamming

  def self.compute(first, second)
    0.upto(first.size - 1).inject(0) do |count, index|
      first[index] != second[index] ? count += 1 : count
    end
  end
end

# Version 1:

  # first.chars.each_with_index do |char, index|
  #   if first[index] != second[index]
  #     count += 1
  #   end
  # end

# Version 2:

  #   0.upto(first.size - 1).each do |index|
  #     if first[index] != second[index]
  #       count += 1
  #     end
  #   end
  #   count
  # end
