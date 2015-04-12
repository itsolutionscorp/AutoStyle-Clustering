class Hamming
  def compute(sqc1, sqc2)
    if sqc1.length != sqc2.length
      raise ArgumentError.new('Seqences have to have the same lenght')  
    end  
    counter = 0
    arry1 = sqc1.chars.to_a
    arry2 = sqc2.chars.to_a
    arry1.each_with_index do |letter, index|
      if
        letter == arry2[index]
        counter += 0
      else
        counter += 1
      end
    end
    counter
  end
end
