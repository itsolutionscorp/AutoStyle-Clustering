class Hamming


  def self.compute(one, two)
    hamming = 0
    Hamming.shortest(one, two).times do |var|
      if one[var] != two[var]
        hamming += 1
      end
      var += 1
    end
    hamming
  end

  def self.shortest(one, two)
    if one.length > two.length
      two.length
    else
      one.length
    end
  end



end
