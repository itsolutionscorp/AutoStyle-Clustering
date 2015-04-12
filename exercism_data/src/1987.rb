module Hamming
  extend self

  def compute(adn1, adn2)
    [adn1.size, adn2.size].min.times.count { |i| adn1[i] != adn2[i] }
  end
end
