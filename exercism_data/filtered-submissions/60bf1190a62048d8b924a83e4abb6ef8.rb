class Hamming

  def compute(inputone, inputtwo)
    strand1 = inputone.split("")
    strand2 = inputtwo.split("")

    @counter = 0
    strand1.zip(strand2).each do |strand1, strand2|
    if strand1 != strand2 then @counter +=1 end
    end

    return @counter

  end
end
