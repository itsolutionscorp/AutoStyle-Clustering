class Grains
  def square(i)
    sq = i-1
    if sq < 1 
      p 1
    else 
      p 2 ** sq
    end
  end
  
  def total 
    range = (1..64).to_a
    p range.inject { |sum, i| sum += 2 ** (i-1) }
  end
end

# king = Grains.new(16)
# king.total
