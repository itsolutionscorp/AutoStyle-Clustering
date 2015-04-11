class Grains
  def square(i)
    sq = i-1
    2 ** sq
  end
  
  def total 
    range = (1..64).to_a
    range.inject { |sum, i| sum += square(i) }
  end
end
