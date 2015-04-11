class Hamming
  def self.compute(chain_one, chain_two)
    count = 0

    common_length(chain_one, chain_two).times do |i|
      count += 1 if chain_one[i] != chain_two[i]
    end
    count
  end

  private
  def self.common_length(chain_one, chain_two)
    [chain_one.length, chain_two.length].min
  end
end
