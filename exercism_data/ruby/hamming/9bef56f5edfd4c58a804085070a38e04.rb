class Hamming
  def self.compute(chain_1, chain_2)
    shortest = shortest(chain_1, chain_2)
    longest = shortest == chain_1 ? chain_2 : chain_1
    differences(shortest, longest).length
  end

private
  def self.shortest(str1, str2)
    str1.length > str2.length ? str2 : str1
  end

  def self.differences(shortest, longest)
     shortest.split('').reject
       .with_index { |genome, index| genome == longest[index] }
  end
end
