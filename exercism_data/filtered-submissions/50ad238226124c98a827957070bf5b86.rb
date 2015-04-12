class Hamming

  def compute(first, second)
    firstLets = first.chars
    secondLets = second.chars
    difference = 0
    i = 0

    if firstLets.size <= secondLets.size
      firstLets.each do
        if firstLets[i] != secondLets[i]
          difference = difference + 1
        end
        i = i + 1
      end
    else
      secondLets.each do
        if firstLets[i] != secondLets[i]
          difference = difference + 1
        end
        i = i + 1
      end
    end


=begin
    firstLets.each do |i|
      if firstLets[i] != secondLets[i]
        difference = difference + 1
      end
    end
=end
    return difference
  end
end
