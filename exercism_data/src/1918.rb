def compute(adn1, adn2)
    (0...[adn1.size, adn2.size].min).count { |i| adn1[i] != adn2[i] }
  end
end