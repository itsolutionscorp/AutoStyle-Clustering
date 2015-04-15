class Hamming

  def self.compute(str_1, str_2)
    array_1 = str_1.chars
    array_2 = str_2.chars
    count = 0

    array_1.each_with_index do |letter, index|
      if letter != array_2[index] && array_2[index] != nil
        count += 1
      end
    end

    count
  end

end
